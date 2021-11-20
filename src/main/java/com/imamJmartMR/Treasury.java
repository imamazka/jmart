package com.imamJmartMR;

public class Treasury {
    
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000.0;
    public static double discount, price;
    
    public static double getAdjustedPrice(){
        return getDiscountedPrice() + getAdminFee();
    }
    
    public static double getAdminFee(){
        double discountedPrice = getDiscountedPrice();
        if (discountedPrice < BOTTOM_PRICE)
            return BOTTOM_FEE;
        return COMMISSION_MULTIPLIER * discountedPrice;
    }
    
    private static double getDiscountedPrice(){
        if (discount >= 100.0) return 0.0;
        double cut = price * discount / 100.0;
        return price - cut; 
    }
}
