package com.imamJmartMR;
import java.util.Calendar;
import java.util.Date;
import java.text.*;

public class Complaint extends Serializable
{
    public final Date date;
    public String desc;
    
    public Complaint(int id, String desc){
        this.desc = desc;
        this.date = Calendar.getInstance().getTime();
    }

    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("Complaint{date=%s, desc='%s'}", dateFormat.format(date), desc);
    }
}
