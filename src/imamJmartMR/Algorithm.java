package imamJmartMR;
import java.lang.*;
import java.util.*;

public class Algorithm {

    private Algorithm(){}

    // ###################### COLLECT ######################
    public static <T> List<T> collect(T[] array, T value){
        return null;
    }
    public static <T> List<T> collect(Iterable<T> iterable, T value){
        return null;
    }
    public static <T> List<T> collect(Iterator<T> iterator, T value){
        return null;
    }
    public static <T> List<T> collect(T[] array, Predicate<T> pred){
        return null;
    }
    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred){
        return null;
    }
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred){
        return null;
    }

    // ###################### COUNT ######################
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

    // ###################### EXISTS ######################
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

    // ###################### FIND ######################
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

    // ###################### MAX ######################
    public static <T extends Comparable> T max(T first, T second) {
        if((first.compareTo(second)) == -1)
            return second;
        else
            return first;
    }
    public static <T extends Comparable> T max(T[] array){
        T max = array[0];
        for (int i = 1; i < array.length; i++)
            if (array[i].compareTo(max) == 1)
                max = array[i];
        return max;
    }
    public static <T extends Comparable> T max(Iterable<T> iterable){
        return null;
    }
    public static <T extends Comparable> T max(Iterator<T> iterator){
        T max = iterator.next();
        while(iterator.hasNext()) {
            T val = iterator.next();

            if(val.compareTo(max) == 1)
                max = val;
        }
        return max;
    }
    public static <T extends Comparable> T max(T first, T second, Comparator<? super T> comparator){
        return null;
    }
    public static <T extends Comparable> T max(T[] array, Comparator<? super T> comparator){
        return null;
    }
    public static <T extends Comparable> T max(Iterable<T> iterable, Comparator<? super T> comparator){
        return null;
    }
    public static <T extends Comparable> T max(Iterator<T> iterator, Comparator<? super T> comparator){
        return null;
    }


    // ###################### MIN ######################
    public static <T extends Comparable> T min(T first, T second) {
        if((first.compareTo(second)) == -1)
            return first;
        else
            return second;
    }
    public static <T extends Comparable> T min(T[] array){
        T min = array[0];
        for (int i = 1; i < array.length; i++)
            if (array[i].compareTo(min) == -1)
                min = array[i];
        return min;
    }
    public static <T extends Comparable> T min(Iterable<T> iterable){
        return null;
    }
    public static <T extends Comparable> T min(Iterator<T> iterator){
        T min = iterator.next();
        while(iterator.hasNext()) {
            T val = iterator.next();

            if(val.compareTo(min) == -1)
                min = val;
        }
        return min;
    }
    public static <T extends Comparable> T min(T first, T second, Comparator<? super T> comparator){
        return null;
    }
    public static <T extends Comparable> T min(T[] array, Comparator<? super T> comparator){
        return null;
    }
    public static <T extends Comparable> T min(Iterable<T> iterable, Comparator<? super T> comparator){
        return null;
    }
    public static <T extends Comparable> T min(Iterator<T> iterator, Comparator<? super T> comparator){
        return null;
    }

    public static <T extends Comparable> List<T> paginate (T[] array, int page, int pageSize, Predicate<T> pred) {

        List<T> paginated = new ArrayList<T>();

        if (pred.equals(true)) {

            if(pageSize <= 0 || page <= 0) {
                return paginated;
            }

            int index = (page - 1) * pageSize;
            return null;
        }

        return Collections.emptyList();
    }
    public static <T extends Comparable> List<T> paginate (Iterable<T> iterable, int page, int pageSize, Predicate<T> pred) {
        return null;
    }
    public static <T extends Comparable> List<T> paginate (Iterator<T> iterator, int page, int pageSize, Predicate<T> pred) {
        return null;
    }
}
