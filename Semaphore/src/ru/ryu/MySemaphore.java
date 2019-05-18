package ru.ryu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MySemaphore {
    private int maxThread, currentThread;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    MySemaphore(int permits) {
        this.maxThread = permits;
        currentThread = 0;
    }

    void acquire(){
        try{
            lock.lock();
            while (currentThread >= maxThread){
                condition.await();
            }
            currentThread++;
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void release(){
        lock.lock();
        try {
            currentThread--;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
