package com.imamJmartMR;

/**
 * Pairing two generics parameter.
 * @param <T>
 * @param <U>
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */
public class Pair<T, U> {

    public T first;
    public U second;

    public void Pair() {

    }

    public void Pair(T first, U second){
        this.first = first;
        this.second = second;
    }
}
