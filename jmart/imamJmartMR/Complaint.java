package imamJmartMR;

public class Complaint extends Recognizable implements FileParser
{
    public String date;
    public String desc;
    
    public Complaint(int id, String desc){
        super(id);
        this.date = "September";
    }
    
    @Override
    public boolean read(String Content){
        return false;
    }
}
