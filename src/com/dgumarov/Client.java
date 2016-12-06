package com.dgumarov;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dinar on 06.12.2016.
 */
public class Client {
    static Logger logger = Logger.getLogger(Client.class.getName());

    public static void main(String[] args) throws InterruptedException {
        try (Socket socket = new Socket("localhost", 8888);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {

            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNext())
            {
                String request = scanner.nextLine();
                writer.write(String.valueOf(request));
                writer.newLine();
                writer.flush();

                if (request.equals("exit"))
                    break;

                String response = reader.readLine();
                System.out.println(response);

                /*while ((response = reader.readLine()) != null)
                    System.out.println(response);*/
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
