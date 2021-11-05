package imamJmartMR;
import java.lang.*;

public class Recognizable implements Comparable<Recognizable>
{
    public final int id;
    
    protected Recognizable() {
        this.id = 0;
    }

    public boolean equals(Object other) {
        
        if(other instanceof Recognizable) {
            Recognizable r = (Recognizable)other;
            return r.id == id ? true:false;
        }
        return false;
    }
    
    public boolean equals(Recognizable other){
        return id == other.id ? true:false;
    }

    @Override
    public int compareTo(Recognizable other) {
        return ((Comparable)this.id).compareTo(other.id);
    }

    public static <T extends Recognizable> int setClosingId(Class<T> clazz) {
        if(clazz.isAssignableFrom(Recognizable.class)){
            return 0;
        }
        else{
            return 1;
        }
    }

    public static <T extends Recognizable> int getClosingId(Class<T> clazz, int id) {
        if(clazz.isAssignableFrom(Recognizable.class)){
            return 0;
        }
        else{
            return 1;
        }
    }
}
