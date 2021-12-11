package com.example.raspgui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSocket {

    public static void main(String[] args) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket sock = null;
                try {
                    sock = new Socket("192.168.1.25", 1024);
                    System.out.println("Connection established");
                BufferedReader dIS = new BufferedReader(new InputStreamReader(sock.getInputStream()));

                String humid = dIS.readLine();
                String temp = dIS.readLine();

                System.out.println("Humid" + humid);
                System.out.println("Temp" + temp);

                double str1 = Double.parseDouble(humid);
                double str2 = Double.parseDouble(temp);

                } catch (IOException e) {
                e.printStackTrace();
                }
            }
        }).start();
    }
}
