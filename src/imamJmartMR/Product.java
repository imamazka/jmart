package imamJmartMR;

public class Product extends Recognizable
{
    
    public int storeId;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag priceTag;
    public ProductCategory category;
    public ProductRating rating;
    public Shipment.MultiDuration multiDuration;
    
    public Product(int id, int storeId, String name, int weight, boolean conditionUsed, PriceTag priceTag, ProductCategory category, Shipment.MultiDuration multiDuration){
        this.storeId = storeId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        this.multiDuration = multiDuration;
    }

    public String toString(){
        return "Name: Harry Potter\nWeight: 1\nconditionUsed: false\npriceTag = 21000.0\ncategory: BOOK\nrating: 0\nstoreId: 1";}
}
