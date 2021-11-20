package com.imamJmartMR;

import java.util.ArrayList;
import java.util.Date;

public class Payment extends Invoice
{
    public ArrayList<Record> history = new ArrayList<Record>();
    public Shipment shipment;
    public int productCount;
    
    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    public double getTotalPay(Product product) {
        return 0.0;
    }

    public static class Record
    {
        public final Date date = new Date();
        public String message;
        public String status;

        public Record (Status status, String message) {
            date.getTime();
            this.message = message;
        }
    }
}
