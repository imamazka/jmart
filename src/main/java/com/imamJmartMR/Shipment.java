package com.imamJmartMR;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Details of shipment for an ordered products.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

public class Shipment
{
    public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("EE MMMMM dd yyyy");
    public static final Plan INSTANT = new Plan((byte)(1 << 0));
    public static final Plan SAME_DAY = new Plan((byte)(1 << 1));
    public static final Plan NEXT_DAY = new Plan((byte)(1 << 2));
    public static final Plan REGULER = new Plan((byte)(1 << 3));
    public static final Plan KARGO = new Plan((byte)(1 << 4));
    public String address;
    public int cost;
    public byte plan;
    public String receipt;

    /**
     * Shipment details
     * @param address shipment address
     * @param cost shipment cost
     * @param plan shipment plan
     * @param receipt shipment receipt
     */
    public Shipment(String address, int cost, byte plan, String receipt){
        this.address = address;
        this.cost = cost;
        this.plan = plan;
        this.receipt = receipt;
    }

    /**
     * Get estimated arrival date
     * @param reference date reference
     * @return estimated arrival date
     */
    public String getEstimatedArrival(Date reference){
        Calendar temp = Calendar.getInstance();
        if(this.plan == 1<<0|| this.plan == 1<<1){
            return ESTIMATION_FORMAT.format(reference.getTime());
        }
        else if(this.plan == 1<<2){
            temp.setTime(reference);
            temp.add(Calendar.DATE,1);
            return ESTIMATION_FORMAT.format(temp);
        }
        else if(this.plan == 1<<3){
            temp.setTime(reference);
            temp.add(Calendar.DATE,2);
            return ESTIMATION_FORMAT.format(temp);
        }
        else{
            temp.setTime(reference);
            temp.add(Calendar.DATE,5);
            return ESTIMATION_FORMAT.format(temp);
        }
    }

    public boolean isDuration(Plan reference){
        if((reference.bit & this.plan) != 0){
            return true;
        }
        return false;

    }

    public boolean isDuration(byte object,Plan reference){
        if((reference.bit & object) != 0){
            return true;
        }
        return false;
    }

    /**
     * Shipment plan
     */
    public static class Plan
    {
        public final byte bit;
        
        private Plan(byte bit){
            this.bit = bit;
        }
    }
}
