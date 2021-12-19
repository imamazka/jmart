package com.imamJmartMR;

/**
 * This coupon class can be used by user for applying a coupon when ordering a product.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

public class Coupon extends Serializable
{
    public final int code;
    public final double cut;
    public final double minimum;
    public final String name;
    public final Type type;
    private boolean used;

    /**
     * Type of the coupon
     */
    public enum Type{
        DISCOUNT,
        REBATE
    }

    /**
     * Constructor for the coupon
     * @param name coupon name
     * @param code coupon code
     * @param type coupon type
     * @param cut coupon price cut
     * @param minimum coupon minimum shopping amount
     */
    public Coupon(String name, int code, Type type, double cut, double minimum){
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        this.used = false;
    }

    /**
     * Apply the coupon
     * @param priceTag price of the product
     * @return condition of the apply
     */
    public double apply(Treasury priceTag){
        
        used = true;
        double adjustedPrice = priceTag.getAdjustedPrice();
        switch (type)
        {
            case REBATE:
                if (adjustedPrice >= cut) return 0.0;
                return adjustedPrice - cut;
            case DISCOUNT:
                if (cut >= 100.0) return 0.0;
                return adjustedPrice - adjustedPrice * cut;
        }
        return 0.0;
    }

    /**
     * Check if the coupon can be applied
     * @param priceTag price of the product
     * @return condition of the checked
     */
    public boolean canApply(Treasury priceTag){

        if (priceTag.getAdjustedPrice() >= minimum && !used)
            return true;
        else
            return false;
    }

    /**
     * Get condition of the coupon
     * @return condition of the coupon
     */
    public boolean isUsed(){
        return used;
    }
}
