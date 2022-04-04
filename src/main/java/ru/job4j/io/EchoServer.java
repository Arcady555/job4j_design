package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static ru.job4j.io.UsageLog4j.LOG;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    System.out.println(str);
                    if (str.contains("?msg=Exit")) {
                        server.close();
                    } else {
                        String[] lineMassage = str.split("msg=");
                        String[] massage = lineMassage[1].split(" ");
                        out.write((massage[0] + "\n").getBytes());
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("The problem at Input-Output", e);
        }
    }
}