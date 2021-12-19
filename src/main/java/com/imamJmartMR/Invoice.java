package com.imamJmartMR;

import java.util.Calendar;
import java.util.Date;

/**
 * Store details about user order, including status and rating of a product.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

public abstract class Invoice extends Serializable
{
    public int buyerId;
    public int complaintId;
    public final Date date;
    public int productId;
    public Rating rating;

    /**
     * Invoice details
     * @param buyerId buyer id
     * @param productId product id
     */
    protected Invoice(int buyerId, int productId){
        this.buyerId = buyerId;
        this.complaintId = -1;
        this.productId = productId;
        this.date = Calendar.getInstance().getTime();
        this.rating = Rating.NONE;
    }

    /**
     * Get total pay of the product
     * @param product selected product
     * @return total of the product price
     */
    public abstract double getTotalPay (Product product);

    /**
     * Status for the invoice
     */
    public static enum Status{
        WAITING_CONFIRMATION,
        CANCELLED,
        ON_PROGRESS,
        ON_DELIVERY,
        DELIVERED,
        COMPLAINT,
        FINISHED,
        FAILED
    }

    /**
     * Rating for each finished order
     */
    public static enum Rating{
        NONE,
        BAD,
        NEUTRAL,
        GOOD
    }
}
