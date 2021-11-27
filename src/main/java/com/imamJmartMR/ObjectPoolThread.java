package com.imamJmartMR;

import java.util.Vector;
import java.util.function.Function;

public class ObjectPoolThread<T> extends Thread {

    private boolean exitSignal;
    private Vector<T> objectPool;
    private Function<T,Boolean> routine;

    public ObjectPoolThread (String name, Function<T,Boolean> routine) {
        this.routine = routine;
        Thread t = new Thread(name);
        t.start();
    }

    public ObjectPoolThread (Function<T,Boolean> routine) {
        this.routine = routine;
        Thread t = new Thread();
        t.start();
    }

    public synchronized void add (T object) {
        objectPool.add(object);
    }

    public synchronized void exit () {
        Thread.currentThread().interrupt();
    }

    @Override
    public void run () {
        exitSignal = false;
        int i = 0;
        try {
            while (objectPool.isEmpty() == false) {
                if (exitSignal == true)
                    exit();
                if (routine.apply(objectPool.get(i)) == true)
                    i++;
            }
            if (objectPool.isEmpty())
                Thread.currentThread().wait();
        } catch (InterruptedException e) {e.printStackTrace();}
    }

    public int size () {
        return objectPool.size();
    }
}
