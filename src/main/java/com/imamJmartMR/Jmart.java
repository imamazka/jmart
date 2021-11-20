package com.imamJmartMR;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import com.google.gson.*;
import com.imamJmartMR.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jmart {

    public static long DELIVERED_LIMIT_MS = 1000;
    public static long ON_DELIVERY_LIMIT_MS = 2000;
    public static long ON_PROGRESS_LIMIT_MS = 3000;
    public static long WAITING_CONF_LIMIT_MS = 4000;

    public static void main(String[] args) {

        JsonDBEngine.Run(Jmart.class);
        SpringApplication.run(Jmart.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
    }

    public static boolean paymentTimekeeper (Payment payment) {

        //ObjectPoolThread<Payment> my = new ObjectPoolThread<Payment>(payment);
        return false;
    }

    public static List<Product> filterByAccountId (List<Product> list, int accountId, int page, int pageSize) {
        List<Product> filtered = new ArrayList<>();
        for (Product find : list) {
            if (find.id == accountId) {
                filtered.add(find);
            }
        }
        filtered = paginate(filtered, page, pageSize, null);
        return filtered;
    }

    public static List<Product> filterByCategory(List<Product> list, ProductCategory category) {
        List<Product> filtered = new ArrayList<>();
        for (Product search : list) {
            if (search.category.equals(category)) {
                filtered.add(search);
            }
        }
        return filtered;
    }

    public static List<Product> filterByName(List<Product> list, String search, int page, int pageSize) {
        List<Product> filtered = new ArrayList<>();
        for (Product find : list) {
            if (find.name.equals(search)) {
                filtered.add(find);
            }
        }
        filtered = paginate(filtered, page, pageSize, null);
        return filtered;
    }

    public static List<Product> filterByPrice(List<Product> list, double minPrice, double maxPrice) {

        List<Product> filtered = new ArrayList<>();

        if (minPrice == 0.0) {
            for (Product search : list) {
                if (search.price <= maxPrice) {
                    filtered.add(search);
                }
            }
            return filtered;
        }
        else if (maxPrice == 0.0) {
            for (Product search : list) {
                if (search.price >= minPrice) {
                    filtered.add(search);
                }
            }
            return filtered;
        }
        else {
            return null;
        }
    }

    private static List<Product> paginate (List<Product> list, int page, int pageSize, Predicate<Product> pred) {

        if (pred.equals(true)) {

            if(pageSize <= 0 || page <= 0) {
                throw new IllegalArgumentException("invalid page size: " + pageSize);
            }

            int index = (page - 1) * pageSize;
            return list.subList(index, Math.min(index + pageSize, list.size()));
        }

        return Collections.emptyList();
    }

    public static List<Product> read (String filepath) {

        Gson gson = new Gson();
        List<Product> temp = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
