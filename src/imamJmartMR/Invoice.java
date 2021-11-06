package imamJmartMR;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

public class Invoice extends Serializable
{
    public int buyerId;
    public int complaintId;
    public final Date date;
    public ArrayList<Record> history;
    public int productId;
    public Rating rating;
    public Status status;
    
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
    
    protected Invoice(int buyerId, int productId){
        this.buyerId = buyerId;
        this.productId = productId;
        this.date = Calendar.getInstance().getTime();
        this.rating = Rating.NONE;
        this.status = Status.WAITING_CONFIRMATION;
    }
    
    public class Record
    {
        public Status status;
        public Date date;
        public String message;
    }
}
