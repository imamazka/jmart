package imamJmartMR;

public class PriceTag {
    
    public final double COMMISION_MULTIPLIER = 0.05;
    public final double BOTTOM_PRICE = 20000.0;
    public final double BOTTOM_FEE = 1000.0;
    public double discount, price;
    
    public PriceTag(double price){
        this.price = price;
        this.discount = 0.0;
    }
    
    public PriceTag(double price, double discount){
        this.price = price;
        this.discount = discount;
    }
    
    public double getAdjustedPrice(){
        return getDiscountedPrice() + getAdminFee();
    }
    
    public double getAdminFee(){
        if(price < BOTTOM_PRICE){
            return BOTTOM_FEE;
        }
        else{
            return price - (price * COMMISION_MULTIPLIER);
        }
    }
    
    private double getDiscountedPrice(){
        if(discount > 100.0){
            discount = 100.0;
        }
        
        if(discount == 100.0){
            return 0.0;
        }
        else{
            return price - (price * discount);
        }
    }
}
