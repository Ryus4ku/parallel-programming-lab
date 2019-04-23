package ru.ryu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try(Socket client = new Socket("127.0.0.1", 9000);
            PrintWriter output = new PrintWriter(client.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()))){
            String response;
            int request = 1;
            output.println(request);
            System.out.println("Отправил сообщение на сервер: " + request);
            while ((response = input.readLine()) != null) {
                System.out.println("Ответ от сервера: " + response);
                request = Integer.valueOf(response);

                if (request >= 10) {
                    break;
                }

                request++;
                output.println(request);
                System.out.println("Отправил сообщение на сервер: " + request);
                Thread.sleep(2000);
            }
            System.out.println("Отклчение...");
        } catch (Throwable cause) {
            System.out.println("Ошибка подключения: " + cause.getMessage());
        }
    }
}
