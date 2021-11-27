package com.imamJmartMR.controller;

import com.imamJmartMR.Account;
import com.imamJmartMR.JsonTable;
import com.imamJmartMR.Store;
import com.imamJmartMR.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {

    public static final String REGEX_EMAIL = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
    public @JsonAutowired(value = Account.class, filepath = "\\imamJmartMR\\randomAccountList.json") static JsonTable<Account> accountTable;

    @Override
    public JsonTable<Account> getJsonTable () {
        return accountTable;
    }

    @PostMapping("/login")
    @ResponseBody
    Account login (@RequestParam String email, String password) {
        try{
            String generatedPassword = null;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++)
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            generatedPassword = sb.toString();
            for (Account temp : accountTable) {
                if(temp.email == email && temp.password == generatedPassword) {
                    return temp;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/register")
    @ResponseBody
    Account register (String name, String email, String password) {
        if (!name.isBlank()) {
            Matcher matcher = REGEX_PATTERN_EMAIL.matcher(email);
            Matcher matcher2 = REGEX_PATTERN_PASSWORD.matcher(password);
            if (matcher.find() && matcher2.find() ==  true) {
                for (Account temp : accountTable) {
                    if(temp.email == email) {
                        return null;
                    }
                }
                try{
                    String generatedPassword = null;
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(password.getBytes());
                    byte[] bytes = md.digest();
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < bytes.length; i++)
                        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    generatedPassword = sb.toString();
                    accountTable.add(new Account(name, email, generatedPassword, 0.0));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @PostMapping("/{id}/registerStore")
    @ResponseBody
    Store registerStore (int id, String name, String address, String phoneNumber) {
        for (Account temp : accountTable) {
            if(temp.name == name && temp.store == null) {
                temp.store = new Store(id, name, address, phoneNumber, 0.0);
                return temp.store;
            }
        }
        return null;
    }

    @PostMapping("/{id}/topUp")
    @ResponseBody
    boolean topUp (int id, double balance) {
        for (Account temp : accountTable) {
            if(temp.id == id) {
                temp.balance += balance;
                return true;
            }
        }
        return false;
    }
}
