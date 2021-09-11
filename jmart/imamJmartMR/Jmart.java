package imamJmartMR;

public class Jmart
{
    public static void main(String args[]){
        
    }
    
    public int getPromo(){
        return 0;
    }
    
    public String getCostumer(){
        return "oop";
    }
    
    public float getDiscountPercentage(int before, int after){
        if (before<after){
            return 0.0f;
        }
        else{
            return (before-after)/10;
        }
        
    }
    
    public int getDiscountedPrice(int price, float discountPercentage){
        if (discountPercentage > 100){
            return price;
        }
        else{
            float num = price - (price * discountPercentage);
            return (int)num;
        }
    }

    public int getOriginalPrice(int discountedPrice, float discountPercentage){
        if (discountPercentage>100){
            return 0;
        }
        else{
            float num = discountedPrice + (discountPercentage * discountedPrice);
            return (int)num;
        }
    }
    
    public float getCommissionMultiplier(){
        return 0.05f;
    }
    
    public int getAdjustedPrice(int price){
        if(price<=0){
            return 0;
        }
        else{
            return price + 5;
        }
    }
    
    public int getAdminFee(int price){
        if(price<=0){
            return 0;
        }
        else{
            float num = price * 0.05f;
            return (int)num;
        }
    }
}
