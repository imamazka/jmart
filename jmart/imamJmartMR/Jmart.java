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
            return 0;
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
            return num;
        }
    }

    public int getOriginalPrice(int discountedPrice, float discountPercentage){
        
    }
}
