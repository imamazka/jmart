package imamJmartMR;
import java.lang.*;
import java.util.*;

public class Algorithm {

    private Algorithm(){}

    public static <T> int count(T[] array, T value) {
        return 0;
    }
    public static <T> int count(Iterable<T> iterable, T value) {
        return 0;
    }
    public static <T> int count(Iterator<T> iterator, T value) {
        return 0;
    }
    public static <T> int count(T[] array, Predicate<T> pred) {
        return 0;
    }
    public static <T> int count(Iterable<T> iterable, Predicate<T> pred) {
        return 0;
    }
    public static <T> int count(Iterator<T> iterator, Predicate<T> pred) {
        return 0;
    }


    public static <T> boolean exists(T[] array, T value)  {
        for(T t : array){
            if(t.equals(value))
                return true;
        }
        return false;
    }
    public static <T> boolean exists(Iterable<T> iterable, T value) {
        for(T t : iterable){
            if(t.equals(value))
                return true;
        }
        return false;
    }
    public static <T> boolean exists(Iterator<T> iterator, T value) {
        while(iterator.hasNext()){
            if(iterator.next().equals(value))
                return true;
        }
        return false;
    }
    public static <T> boolean exists(T[] array, Predicate<T> pred) {
        for(T t : array){
            if(t.equals(pred))
                return true;
        }
        return false;
    }
    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
        for(T t : iterable){
            if(t.equals(pred))
                return true;
        }
        return false;
    }
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
        while(iterator.hasNext()){
            if(iterator.next().equals(pred))
                return true;
        }
        return false;
    }


    public static <T> T find(T[] array, T value) {
        for(T t : array){
            if(t.equals(value))
                return t;
        }
        return null;
    }
    public static <T> T find(Iterable<T> iterable, T value) {
        for(T t : iterable){
            if(t.equals(value))
                return t;
        }
        return null;
    }
    public static <T> T find(Iterator<T> iterator, T value) {
        T t;
        while(iterator.hasNext()){
            t = iterator.next();
            if(t.equals(value))
                return t;
        }
        return null;
    }
    public static <T> T find(T[] array, Predicate<T> pred) {
        for(T t : array){
            if(t.equals(pred))
                return t;
        }
        return null;
    }
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
        for(T t : iterable){
            if(t.equals(pred))
                return t;
        }
        return null;
    }
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
        T t;
        while(iterator.hasNext()){
            t = iterator.next();
            if(t.equals(pred))
                return t;
        }
        return null;
    }


    public static <T extends Comparable> T max(T first, T second) {

        if((first.compareTo(second)) == -1)
            return second;
        else
            return first;
    }
    public static <T extends Comparable> T min(T first, T second) {

        if((first.compareTo(second)) == -1)
            return first;
        else
            return second;
    }
}
