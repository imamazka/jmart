package com.imamJmartMR.controller;

import com.imamJmartMR.Account;
import com.imamJmartMR.JsonTable;
import com.imamJmartMR.Store;
import com.imamJmartMR.dbjson.JsonAutowired;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {

    public static final String REGEX_EMAIL = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";;
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
    public @JsonAutowired(value = Account.class, filepath = "E:\\Imam Azka\\Semester 3\\Java\\Jmart\\src\\main\\java\\com\\imamJmartMR\\json.txt") static JsonTable<Account> accountTable;

    @Override
    public JsonTable<Account> getJsonTable () {
        return null;
    }

    @PostMapping("/login")
    Account login (String email, String password) {
        return null;
    }

    @PostMapping("/register")
    Account register (String name, String email, String password) {
        return null;
    }

    @PostMapping("/{id}/registerStore")
    Store registerStore (int id, String name, String address, String phoneNumber) {
        return null;
    }

    @PostMapping("/{id}/topUp")
    boolean topUp (int id, double balance) {
        return false;
    }
}
