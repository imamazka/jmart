package imamJmartMR;
import java.util.Calendar;
import java.util.Date;
import java.text.*;

public class Complaint extends Recognizable implements FileParser
{
    public Date date;
    public String desc;
    
    public Complaint(int id, String desc){
        super(id);
        this.date = Calendar.getInstance().getTime();
    }
    
    @Override
    public boolean read(String Content){
        return false;
    }
    
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("Complaint{date=%s, desc='%s'}", dateFormat.format(date), desc);
    }
}
