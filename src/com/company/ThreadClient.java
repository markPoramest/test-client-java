package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ThreadClient {

    static void runThreadClient(PrintWriter out, String text) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            out.println(text);
        });
        t1.start();
    }

    public static void main(String[] args) throws IOException {
        final String HOST = "127.0.0.1";
        final int PORT = 4040;

        System.out.println("Client started.");

        try (
                Socket socket = new Socket(HOST, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner in = new Scanner(socket.getInputStream());
                Scanner s = new Scanner(System.in);

        ) {
            runThreadClient(out, "Run thread client 1");
            runThreadClient(out, "Run thread client 2");
            Thread.sleep(1000);

            while (true) {
                System.out.println("Echoed from server: " + in.nextLine());
            }
        } catch (InterruptedException e) {
            System.out.println("Client interrupted.");
        }
    }

}