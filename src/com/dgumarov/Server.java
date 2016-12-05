package com.dgumarov;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    static Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {
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
                    writer.write(line);
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

    }

}