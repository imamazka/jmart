package com.imamJmartMR;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Store user order details, and record the status of order.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

public class Payment extends Invoice
{
    public ArrayList<Record> history = new ArrayList<>();
    public Shipment shipment;
    public int productCount;

    /**
     * Constructor for payment
     * @param buyerId buyer id
     * @param productId product id
     * @param productCount amount of product
     * @param shipment selected shipment
     */
    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    /**
     * Get payment total
     * @param product selected product
     * @return total of the payment
     */
    public double getTotalPay(Product product) {
        return product.price;
    }

    /**
     * Record of the payment
     */
    public static class Record
    {
        public final Date date;
        public String message;
        public Status status;

        public Record (Status status, String message) {
            this.date = Calendar.getInstance().getTime();
            this.status = status;
            this.message = message;
        }
    }
}
