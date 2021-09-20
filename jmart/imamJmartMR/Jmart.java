package imamJmartMR;

public class Jmart{
    
    public static void main(String[] args){
        
    }
    
    public Product createProduct(){
        return new Product("Imam", 100, false, new PriceTag(1000), ProductCategory.BOOK);
    }
    
    public Coupon createCoupon(){
        return new Coupon("Azka", 1, Type.DISCOUNT, 0.5, 100);
    }
    
    /*public ShipmentDuration createShipmentDuration(){
        return null;
    }*/
}
