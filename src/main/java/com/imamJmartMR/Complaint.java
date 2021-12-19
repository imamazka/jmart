package com.imamJmartMR;

import java.util.Calendar;
import java.util.Date;
import java.text.*;

/**
 * Handle a complaint from user.
 * Store the description for the complaint, and the date of the complaint.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

public class Complaint extends Serializable
{
    public final Date date;
    public String desc;

    /**
     * Constructor for complaint
     * @param id id of the order
     * @param desc description of complaint
     */
    public Complaint(int id, String desc){
        this.desc = desc;
        this.date = Calendar.getInstance().getTime();
    }

    /**
     * Get the complaint details to string
     * @return complaint details
     */
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("Complaint{date=%s, desc='%s'}", dateFormat.format(date), desc);
    }
}
