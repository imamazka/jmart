package imamJmartMR;

public class Product{
    
    private static int idCounter = 0;
    int id;
    String name;
    int weight;
    boolean conditionUsed;
    PriceTag priceTag;
    ProductCategory category;
    ProductRating rating;
    
    public Product(String name, int weight, boolean conditionUsed, PriceTag priceTag, ProductCategory category){
        this.id = idCounter;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        this.rating = new ProductRating();
        this.id = idCounter;
        idCounter++;
    }
}
