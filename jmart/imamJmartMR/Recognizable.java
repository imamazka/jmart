package imamJmartMR;

public class Recognizable
{
    public final int id;
    
    protected Recognizable(int id) {
        this.id = id;
    }
    
    public boolean equals(Object obj) {
        
        if(obj instanceof Recognizable) {
            Recognizable r = (Recognizable)obj;
            return r.id == id ? true:false;
        }
        return false;
    }
    
    public boolean equals(Recognizable rec){
        return id == rec.id ? true:false;
    }
}
