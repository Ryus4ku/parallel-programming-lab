package ru.ryu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9000);
        try(Socket client = server.accept();
            PrintWriter output = new PrintWriter(client.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
            System.out.println("Новое подключение с " + client.getRemoteSocketAddress().toString());
            String inputLine;
            while ((inputLine = input.readLine()) != null) {
                System.out.println("Получил от клиента: " + inputLine);
                output.println(Integer.valueOf(inputLine) + 1);
                System.out.println("Отправил клиенту: " + (Integer.valueOf(inputLine) + 1));
            }

            System.out.println("Подключение разорвано");
        } catch (Throwable cause) {
            System.out.println("Ошибка подключения: " + cause.getMessage());
        }
    }
}
