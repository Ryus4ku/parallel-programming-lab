package ru.ryu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        /**
         * Для управления доступом к ресурсу семафор использует счетчик, представляющий количество разрешений.
         * Если значение счетчика больше нуля, то поток получает доступ к ресурсу, при этом счетчик уменьшается на единицу.
         * После окончания работы с ресурсом поток освобождает семафор, и счетчик увеличивается на единицу.
         * Если же счетчик равен нулю, то поток блокируется и ждет, пока не получит разрешение от семафора.
         * @param permits - кол-во допустимых разрешений для доступа к ресурсу.
         * @param fair: true, то разрешения будут предоставляться ожидающим потокам в том порядке, в каком они запрашивали доступ;
         *              false, то разрешения будут предоставляться в неопределенном порядке.
         */
        MySemaphore sem = new MySemaphore(2);
        for (int i = 1; i < 6; i++) {
            new Philosopher(sem, i).start();
        }
        /*List<Thread> threads = new ArrayList<>();
        for (int j = 0; j < 4; j++) {
            threads.add(new Thread(() -> {
                for (int i = 1; i < 6; i++) {
                    try {
                        Philosopher p = new Philosopher(i);
                        while(p.getNum() < 3) {
                            //Запрашиваем у семафора разрешение на выполнение
                            sem.acquire();
                            System.out.println ("Философ " + p.getId()+" садится за стол");
                            p.setNum(p.getNum() + 1);
                            System.out.println ("Философ " + p.getId()+" выходит из-за стола");
                            sem.release();
                        }
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }

        for (Thread t : threads) {
            t.start();
        }*/
    }
}