package imamJmartMR;
import java.lang.*;
import java.util.HashMap;
import java.util.Map;

public class Serializable implements Comparable<Serializable>
{
    public int id;
    private static Map<Class<?>, Integer> mapCounter = new HashMap();
    
    protected Serializable() {
        Integer count = mapCounter.get(getClass());
        if (count == null) {
            count = 0;
        }
        else {
            count += 1;
        }
        mapCounter.put(getClass(), count);
        this.id = count;
    }

    public boolean equals(Object other) {
        
        if(other instanceof Serializable) {
            Serializable r = (Serializable)other;
            return r.id == id ? true:false;
        }
        return false;
    }
    
    public boolean equals(Serializable other){
        return id == other.id ? true:false;
    }

    @Override
    public int compareTo(Serializable other) {
        return ((Comparable)this.id).compareTo(other.id);
    }

    public <T extends Serializable> Integer getClosingId(Class<T> clazz) {
        return this.id;
    }

    public <T extends Serializable> Integer setClosingId(Class<T> clazz, int id) {
        this.id = id;
        return null;
    }
}
