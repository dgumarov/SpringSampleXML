package com.dgumarov;

import com.dgumarov.model.Product;
import com.dgumarov.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    static Logger logger = Logger.getLogger(Server.class.getName());
    static ProductService productService;

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        productService = applicationContext.getBean("productService", ProductService.class);

        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("Seller connected.");
                new Worker(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Worker extends Thread {
        Socket socket;

        public Worker(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ) {

                String line;
                while ((line = reader.readLine()) != null) {
                    logger.log(Level.INFO, "Line received: {0}", line);
                    if (line.equals("exit"))
                        break;
                    String responce = processCommand(line);

                    if (responce == null)
                        responce = "No such command found";

                    writer.write(responce);
                    writer.newLine();
                    writer.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                logger.info("Seller disconnected.");
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private String processCommand(String command)
        {
            String result = null;

            if (command.startsWith("show all"))
                result = showAll();

            if (command.startsWith("search"))
                result = search(command.substring("search".length()+1));

            if (command.startsWith("sell"))
                result = sell(command.substring("sell".length()+1));

            return result;
        }

        private String showAll()
        {
            List<Product> products = productService.findAll();
            StringBuilder sb = new StringBuilder();

            products.forEach(product -> sb.append(product));

            return sb.toString();
        }

        private String search(String keyword)
        {
            List<Product> products = productService.findByName(keyword);
            StringBuilder sb = new StringBuilder();

            products.forEach(product -> sb.append(product));

            return sb.length() != 0 ? sb.toString() : "No products found for '"+keyword+"'";
        }

        private String sell(String inputParams)
        {
            String[] params = inputParams.split(" ");
            int id = 0;
            int count = 0;

            if (params.length >= 2)
            {
                try {
                    id = Integer.valueOf(params[0]);
                    count = Integer.valueOf(params[1]);

                    productService.sell(id, count);
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                    return "Incorrect id or quantity.";
                }
                catch (IllegalArgumentException ia)
                {
                    return "The quantity exceeds available product in store";
                }
            }

            return "Sold " + count + " items with id=" + id;
        }

    }

}