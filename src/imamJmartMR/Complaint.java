package imamJmartMR;
import java.util.Calendar;
import java.util.Date;
import java.text.*;

public class Complaint extends Recognizable
{
    public Date date;
    public String desc;
    
    public Complaint(int id, String desc){
        this.date = Calendar.getInstance().getTime();
    }

    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("Complaint{date=%s, desc='%s'}", dateFormat.format(date), desc);
    }
}
