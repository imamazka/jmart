package imamJmartMR;

public class Payment extends Transaction implements FileParser
{
    public int productId;
    public ShipmentDuration shipmentDuration;
    
    Payment(int id, int buyerId, Product product, ShipmentDuration shipmentDuration){
        super(id, buyerId, product.storeId);
    }
    Payment(int id, int buyerId, int storeId, int productId, ShipmentDuration shipmentDuration){
        super(buyerId, storeId, productId);
    }
    
    public boolean validate(){
        return false;
    }
    
    public Transaction perform(){
        return null;
    }
    
    @Override
    public boolean read(String Content){
        return false;
    }
}
