package com.imamJmartMR;

public class Coupon extends Serializable
{
    public final int code;
    public final double cut;
    public final double minimum;
    public final String name;
    public final Type type;
    private boolean used;
    
    public enum Type{
        DISCOUNT,
        REBATE
    }
    
    public Coupon(String name, int code, Type type, double cut, double minimum){
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        this.used = false;
    }

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

    public boolean canApply(Treasury priceTag){

        if(priceTag.getAdjustedPrice() >= minimum && used == false){
            return true;
        }

        else{
            return false;
        }
    }


    public boolean isUsed(){
        return used;
    }
}
