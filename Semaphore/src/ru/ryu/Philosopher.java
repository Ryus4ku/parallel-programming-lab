package ru.ryu;

import java.util.concurrent.Semaphore;

/**
 * Класс философа
 */
class Philosopher extends Thread {
    // семафор, ограничивающий число философов
    Semaphore sem;
    MySemaphore mySemaphore;
    // кол-во приемов пищи
    int num = 0;
    // условный номер философа
    int id;
    // в качестве параметров конструктора передаем идентификатор философа и семафор
    Philosopher(MySemaphore sem, int id) {
        /*this.sem=sem;*/
        mySemaphore = sem;
        this.id=id;
    }

    public void run() {
        try {
            // пока количество приемов пищи не достигнет 3
            while(num<3) {
                //Запрашиваем у семафора разрешение на выполнение
                mySemaphore.acquire();
                System.out.println ("Философ " + id+" садится за стол");
                // философ ест
                sleep(500);
                num++;

                System.out.println ("Философ " + id+" выходит из-за стола");
                mySemaphore.release();
            }
        } catch(InterruptedException e) {
            System.out.println ("у философа " + id + " проблемы со здоровьем");
        }
    }
}
