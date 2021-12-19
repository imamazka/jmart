package com.imamJmartMR.controller;

import com.imamJmartMR.Account;
import com.imamJmartMR.JsonTable;
import com.imamJmartMR.Payment;
import com.imamJmartMR.Store;
import com.imamJmartMR.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller for Account data type.
 * Handle incoming HTTP request and send response back to the caller.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {

    public static final String REGEX_EMAIL = "^\\w+([\\.&`~-]?\\w+)*@\\w+([\\.-]?\\w+)+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d][^-\\s]{7,}$";
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
    public @JsonAutowired(value = Account.class, filepath = "E:\\Imam Azka\\Semester 3\\Java\\Jmart\\src\\main\\resources\\AccountList.json") static JsonTable<Account> accountTable;

    @Override
    public JsonTable<Account> getJsonTable () {
        return accountTable;
    }

    /**
     * Login to existing account
     * @param email user email used for login
     * @param password entered password for login
     * @return account of success logged user
     */
    @PostMapping("/login")
    Account login (@RequestParam String email, @RequestParam String password) {
        if (accountTable == null)
            return null;
        int i = 0;
        try{
            String generatedPassword;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < bytes.length; j++)
                sb.append(Integer.toString((bytes[j] & 0xff) + 0x100, 16).substring(1));
            generatedPassword = sb.toString();
            for (Account temp : accountTable) {
                if (temp.email.equals(email) && temp.password.equals(generatedPassword)) {
                    return accountTable.get(i);
                }
                i++;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Register a new account
     * @param name new user name that will registered
     * @param email new user email
     * @param password new user password
     * @return new registered account details
     */
    @PostMapping("/register")
    Account register (@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        if (accountTable == null || accountTable.isEmpty())
            return null;
        if (!name.isBlank()) {
            Matcher matcher = REGEX_PATTERN_EMAIL.matcher(email);
            Matcher matcher2 = REGEX_PATTERN_PASSWORD.matcher(password);
            if (matcher.find() && matcher2.find()) {
                for (Account temp : accountTable) {
                    if(temp.email.equals(email)) {
                        return null;
                    }
                }
                try{
                    String generatedPassword;
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(password.getBytes());
                    byte[] bytes = md.digest();
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < bytes.length; i++)
                        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    generatedPassword = sb.toString();
                    Account add = new Account(name, email, generatedPassword, 0.0);
                    accountTable.add(add);
                    return accountTable.lastElement();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Register new store
     * @param id user id that will register a store
     * @param name name of the new store
     * @param address address of the new store
     * @param phoneNumber phone number of the new store
     * @return new registered store details
     */
    @PostMapping("/{id}/registerStore")
    Store registerStore (@PathVariable int id, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber) {
        if (accountTable == null || accountTable.isEmpty())
            return null;
        int i = 0;
        for (Account temp : accountTable) {
            if(temp.id == id && temp.store == null) {
                accountTable.get(i).store = new Store(id, name, address, phoneNumber, 0.0);
                return accountTable.get(i).store;
            }
            i++;
        }
        return null;
    }

    /**
     * Top-up a user balance
     * @param id user id
     * @param balance balance of the top-up
     * @return condition of the top-up
     */
    @PostMapping("/{id}/topUp")
    boolean topUp (@PathVariable int id, @RequestParam double balance) {
        if (accountTable == null || accountTable.isEmpty())
            return false;
        int i = 0;
        for (Account temp : accountTable) {
            if(temp.id == id) {
                accountTable.get(i).balance += balance;
                return true;
            }
            i++;
        }
        return false;
    }
}