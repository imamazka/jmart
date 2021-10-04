package imamJmartMR;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Shipment implements FileParser
{
    public String address;
    public int shipmentCost;
    public Duration duration;
    public String receipt;
    
    public Shipment(String address, int shipmentCost, Duration duration, String receipt){
        
    }
    
    @Override
    public boolean read(String Content){
        return false;
    }
    
    public class Duration
    {
        public final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("EE MMMMM dd yyyy");
        public final Duration INSTANT = new Duration(1 << 0);
        public final Duration SAME_DAY = new Duration(1 << 1);
        public final Duration NEXT_DAY = new Duration(1 << 2);
        public final Duration REGULER = new Duration(1 << 3);
        public final Duration KARGO = new Duration(1 << 4);
        
        private final byte bit;
        
        private Duration(int bit){
            this.bit = (byte)bit;
        }
        
        public String getEstimatedArrival(Date reference) {
        	return null;
        }
    }
    
    public class MultiDuration
    {
        public byte bit;
        
        public MultiDuration(Duration... args){
            int flags = 0;
            
            for (int i = 0; i < args.length; ++i)
                flags |= args[i].bit;
                
            bit = (byte)flags;
        }
        
        public boolean isDuration(Duration reference){ 
            return (bit & reference.bit) != 0; 
        }
    }
}
