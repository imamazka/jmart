package imamJmartMR;

public class PriceTag {
    
    static double COMMISION_MULTIPLIER = 0.05;
    static double BOTTOM_PRICE = 20000.0;
    static double BOTTOM_FEE = 1000.0;
    double discount, price;
    
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
