package com.imamJmartMR;

/**
 * Used to top up a user phone number.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

public class PhoneTopUp extends Invoice{

    public String phoneNumber;
    public String status;

    /**
     * Top-up a phone number
     * @param buyerId buyer id
     * @param productId product id
     * @param phoneNumber phone number
     */
    public PhoneTopUp (int buyerId, int productId, String phoneNumber) {
        super(buyerId, productId);
        this.phoneNumber = phoneNumber;
        this.status = "Succeed";
    }

    /**
     * Get total pay
     * @param product selected product
     * @return total pay
     */
    @Override
    public double getTotalPay (Product product) {
        return product.price;
    }
}
