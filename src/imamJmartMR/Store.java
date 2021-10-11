package imamJmartMR;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Store extends Recognizable
{
	public static final String REGEX_PHONE = "^\\\\d{9,12}$";
	public static final String REGEX_NAME = "^[A-Z](?!.*(\\s)\\1).{4,20}$";
    String name, address, phoneNumber;
    double balance;

    public Store(int accountId, String name, String address, String phoneNumber, double balance){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public String toString(){
        return "name: PT Madju Merdeka\naddress: Jl. Kukusan\nphoneNumber: 628777xxxx";
    }

    public boolean validate() {
    	Pattern pattern = Pattern.compile(REGEX_NAME);
        Matcher matcher = pattern.matcher(name);
        Pattern pattern2 = Pattern.compile(REGEX_PHONE);
        Matcher matcher2 = pattern2.matcher(phoneNumber);
        return matcher.find() && matcher2.find();
    }
}
