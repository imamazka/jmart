package com.imamJmartMR;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

public abstract class Invoice extends Serializable
{
    public int buyerId;
    public int complaintId;
    public final Date date;
    public int productId;
    public Rating rating;

    protected Invoice(int buyerId, int productId){
        this.buyerId = buyerId;
        this.productId = productId;
        this.date = Calendar.getInstance().getTime();
        this.rating = Rating.NONE;
    }

    public abstract double getTotalPay (Product product);

    public static enum Status{
        WAITING_CONFIRMATION,
        CANCELLED,
        ON_PROGRESS,
        ON_DELIVERY,
        COMPLAINT,
        FINISHED,
        FAILED
    }

    public static enum Rating{
        NONE,
        BAD,
        NEUTRAL,
        GOOD
    }
}
