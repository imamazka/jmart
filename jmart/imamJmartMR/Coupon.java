package imamJmartMR;

public class Coupon
{
    public static String name;
    public static int code;
    public static double cut;
    public static Type type;
    public static double minimum;
    private boolean used;
    
    public Coupon(String name, int code, Type type, double cut, double minimum){
        
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
        
        this.used = true;
        return priceTag.getAdjustedPrice() - cut;
        
    }
}
