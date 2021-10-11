package imamJmartMR;
import java.util.Date;
import java.util.ArrayList;

public class Invoice extends Recognizable
{
    public String date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public Status status;
    public ArrayList<Record> history;
    
    public enum Status{
        WAITING_CONFIRMATION,
        CANCELLED,
        ON_PROGRESS,
        ON_DELIVERY,
        COMPLAINT,
        FINISHED,
        FAILED
    }
    
    public enum Rating{
        NONE,
        BAD,
        NEUTRAL,
        GOOD
    }
    
    protected Invoice(int id, int buyerId, int productId){
        this.buyerId = buyerId;
        this.productId = productId;
        this.date = "September";
        this.rating = Rating.NONE;
        this.status = Status.WAITING_CONFIRMATION;
    }

    public class Payment extends Invoice
    {
        public Shipment shipment;
        public int productCount;
        
        public Payment(int id, int buyerId, int productId, int productCount, Shipment shipment){
            super(id, buyerId, productId);
            this.productCount = productCount;
            this.shipment = shipment;
        }
    }
    
    public class Record
    {
        public Status status;
        public Date date;
        public String message;
    }
}
