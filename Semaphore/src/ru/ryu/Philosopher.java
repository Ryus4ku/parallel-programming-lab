package ru.ryu;

import java.util.concurrent.Semaphore;

/**
 * Класс философа
 */
class Philosopher extends Thread {
    // семафор, ограничивающий число философов
    Semaphore sem;
    // кол-во приемов пищи
    int num = 0;
    // условный номер философа
    int id;
    // в качестве параметров конструктора передаем идентификатор философа и семафор
    Philosopher(Semaphore sem, int id) {
        this.sem=sem;
        this.id=id;
    }

    public void run() {
        try {
            // пока количество приемов пищи не достигнет 3
            while(num<3) {
                //Запрашиваем у семафора разрешение на выполнение
                sem.acquire();
                System.out.println ("Философ " + id+" садится за стол");
                // философ ест
                sleep(500);
                num++;

                System.out.println ("Философ " + id+" выходит из-за стола");
                sem.release();

                // философ гуляет
                sleep(500);
            }
        } catch(InterruptedException e) {
            System.out.println ("у философа " + id + " проблемы со здоровьем");
        }
    }
}
