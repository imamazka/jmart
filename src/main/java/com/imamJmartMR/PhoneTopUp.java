package com.imamJmartMR;

/**
 * Used to top up a user phone number.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

public class PhoneTopUp extends Invoice{

    public String phoneNumber;
    public String status;

    public PhoneTopUp (int buyerId, int productId, String phoneNumber) {
        super(buyerId, productId);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public double getTotalPay (Product product) {
        return product.price;
    }
}
