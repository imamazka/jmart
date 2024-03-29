package com.imamJmartMR;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class used to store the details about user account.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

public class Account extends Serializable
{
    public static final String REGEX_EMAIL = "^\\w+([\\.&`~-]?\\w+)*@\\w+([\\.-]?\\w+)+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d][^-\\s]{7,}$";
    public double balance;
    public String name;
    public String email;
    public String password;
    public Store store;

    /**
     * Constructor for the account details
     * @param name account name
     * @param email account email
     * @param password account password
     * @param balance account balance
     */
    public Account(String name, String email, String password, double balance){
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    /**
     * Validate if the inputted email and password meet the requirement
     * @return Condition of the validation
     */
    public boolean validate(){
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(email);
        Pattern pattern2 = Pattern.compile(REGEX_PASSWORD);
        Matcher matcher2 = pattern2.matcher(password);
        return matcher.find() && matcher2.find();
    }
}
