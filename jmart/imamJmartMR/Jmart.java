package imamJmartMR;

public class Jmart
{
    public static void main(String args[]){
        
    }
    
    public static int getPromo(){
        return 0;
    }
    
    public static String getCostumer(){
        return "oop";
    }
    
    public static float getDiscountPercentage(int before, int after){
        if (before<after){
            return 0.0f;
        }
        else{
            return (before-after)/10;
        }
        
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage){
        if (discountPercentage > 100){
            return 0;
        }
        else{
            float num = price - (price * (discountPercentage/100));
            return (int)num;
        }
    }

    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        if (discountPercentage>100){
            return 0;
        }
        else{
            float num = (discountedPrice * 100)/(100 - discountPercentage);
            return (int)num;
        }
    }
    
    public static float getCommissionMultiplier(){
        return 0.05f;
    }
    
    public static int getAdjustedPrice(int price){
        if(price<=0){
            return 0;
        }
        else{
            float num = price + (0.05f * price);
            return (int)num;
        }
    }
    
    public static int getAdminFee(int price){
        if(price<=0){
            return 0;
        }
        else{
            float num = price * 0.05f;
            return (int)num;
        }
    }
}
