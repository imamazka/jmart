package imamJmartMR;

public class Jmart{
    
    public static void main(String args[]){
        
    }
    
    public Product create(){
        Product prod = new Product("Imam Azka", 60, false, new PriceTag(1000), ProductCategory.BOOK);
        return prod;
    }
}
