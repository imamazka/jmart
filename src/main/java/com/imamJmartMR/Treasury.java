package com.imamJmartMR;

/**
 * Handle the cost calculation of an order.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

public class Treasury {
    
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000.0;
    public static double discount, price;

    /**
     * Treasury constructor
     * @param price price of payment
     * @param discount discount for payment
     */
    public Treasury(double price, double discount) {
        this.price = price;
        this.discount = discount;
    }

    /**
     * Get adjusted price
     * @return adjusted price
     */
    public static double getAdjustedPrice(){
        return getDiscountedPrice() + getAdminFee();
    }

    /**
     * Count admin fee
     * @return price with admin fee
     */
    public static double getAdminFee(){
        double discountedPrice = getDiscountedPrice();
        if (discountedPrice < BOTTOM_PRICE)
            return BOTTOM_FEE;
        return COMMISSION_MULTIPLIER * discountedPrice;
    }

    /**
     * Count price with discount
     * @return price with discount
     */
    private static double getDiscountedPrice(){
        if (discount >= 100.0) return 0.0;
        double cut = price * discount / 100.0;
        return price - cut; 
    }
}
