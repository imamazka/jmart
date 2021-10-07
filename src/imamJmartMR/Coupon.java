package imamJmartMR;

public class Coupon extends Recognizable implements FileParser
{
    public int id;
    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private boolean used;
    
    public enum Type{
        DISCOUNT,
        REBATE
    }
    
    public Coupon(int id, String name, int code, Type type, double cut, double minimum){
        super(id);
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        this.used = false;
    }
    
    public boolean isUsed(){
        return used;
    }
    
    public boolean canApply(PriceTag priceTag){
        
        if(priceTag.getAdjustedPrice() >= minimum && used == false){
           return true; 
        }
        
        else{
            return false;
        }
    }
    
    public double apply(PriceTag priceTag){
        
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
    
    @Override
    public boolean read(String Content){
        return false;
    }
}
