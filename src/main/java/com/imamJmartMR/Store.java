package com.imamJmartMR;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Details of a Jmart store.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

public class Store
{
	public static final String REGEX_PHONE = "^\\\\d{9,12}$";
	public static final String REGEX_NAME = "^[A-Z](?!.*(\\s)\\1).{4,20}$";
    public String name;
    public String address;
    public String phoneNumber;
    public double balance;

    /**
     * Constructor for new store
     * @param accountId store owner id
     * @param name store name
     * @param address store address
     * @param phoneNumber store phone number
     * @param balance store balance
     */
    public Store(int accountId, String name, String address, String phoneNumber, double balance){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    /**
     * Get store details
     * @return store details in string
     */
    public String toString(){
        return String.format("name: %s\naddress: %s\nphoneNumber: %s", name, address, phoneNumber);
    }

    /**
     * Check if the inputted details meet the requirement
     * @return validation condition
     */
    public boolean validate() {
    	Pattern pattern = Pattern.compile(REGEX_NAME);
        Matcher matcher = pattern.matcher(name);
        Pattern pattern2 = Pattern.compile(REGEX_PHONE);
        Matcher matcher2 = pattern2.matcher(phoneNumber);
        return matcher.find() && matcher2.find();
    }
}
