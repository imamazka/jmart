package com.imamJmartMR;

import java.util.Vector;
import java.util.function.Function;

public class ObjectPoolThread<T> extends Thread {

    private boolean exitSignal;
    private Vector<T> objectPool = new Vector<>();
    private Function<T,Boolean> routine;

    public ObjectPoolThread (String name, Function<T,Boolean> routine) {
        super(name);
        this.routine = routine;
    }

    public ObjectPoolThread (Function<T,Boolean> routine) {
        super();
        this.routine = routine;
    }

    public synchronized void add (T object) {
        objectPool.add(object);
    }

    public synchronized void exit () {
        Thread.currentThread().interrupt();
    }

    @Override
    public void run () {
        try {
            for (int i = 0; i < size(); i++) {
                if (!routine.apply(objectPool.get(i))) {
                    add(objectPool.get(i));
                }
                if (exitSignal) {
                    exit();
                    break;
                }
                objectPool.remove(i);
            }
            if (objectPool.isEmpty())
                synchronized (this) {Thread.currentThread().wait();}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int size () {
        return objectPool.size();
    }
}
