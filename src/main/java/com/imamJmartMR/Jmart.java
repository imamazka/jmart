package com.imamJmartMR;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.imamJmartMR.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jmart {

    public static long DELIVERED_LIMIT_MS = 1000;
    public static long ON_DELIVERY_LIMIT_MS = 1000;
    public static long ON_PROGRESS_LIMIT_MS = 1000;
    public static long WAITING_CONF_LIMIT_MS = 1000;

    public static void main(String[] args) {

        JsonDBEngine.Run(Jmart.class);
        SpringApplication.run(Jmart.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));


        /*
        try {
            JsonTable<Payment> table = new JsonTable<>(Payment.class, "E:\\Imam Azka\\Semester 3\\Java\\Jmart\\randomPaymentList.json");
            ObjectPoolThread<Payment> paymentPool = new ObjectPoolThread<Payment>("Thread-PP", Jmart::paymentTimekeeper);
            table.forEach(payment -> paymentPool.add(payment));
            paymentPool.start();
            while (paymentPool.size() != 0);
            paymentPool.exit();
            while (paymentPool.isAlive());
            System.out.println("Thread exited successfully");
            Gson gson = new Gson();
            table.forEach(payment -> {
                String history = gson.toJson(payment.history);
                System.out.println(history);
            });
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
         */
    }

    public static boolean paymentTimekeeper (Payment payment) {
        Function<Payment,Boolean> pass = new Function<Payment, Boolean>() {
            @Override
            public Boolean apply(Payment payment) {
                return null;
            }
        };
        long start = System.currentTimeMillis();
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;

        switch (payment.history.get(payment.history.size() - 1).status) {
            case WAITING_CONFIRMATION :
                if (elapsed > WAITING_CONF_LIMIT_MS) {
                    payment.history.get(payment.history.size() - 1).status = Invoice.Status.FAILED;
                    payment.history.get(payment.history.size() - 1).message = "failed";
                }
                break;
            case ON_PROGRESS :
                if (elapsed > ON_PROGRESS_LIMIT_MS) {
                    payment.history.get(payment.history.size() - 1).status = Invoice.Status.FAILED;
                    payment.history.get(payment.history.size() - 1).message = "failed";
                }
                break;
            case ON_DELIVERY :
                if (elapsed > ON_DELIVERY_LIMIT_MS) {
                    payment.history.get(payment.history.size() - 1).status = Invoice.Status.FAILED;
                    payment.history.get(payment.history.size() - 1).message = "failed";
                }
                break;
            case FINISHED :
                if (elapsed > DELIVERED_LIMIT_MS) {
                    payment.history.get(payment.history.size() - 1).status = Invoice.Status.FAILED;
                    payment.history.get(payment.history.size() - 1).message = "failed";
                }
                break;
        }
        return true;
    }

    /*
    public static List<Product> filterByAccountId (List<Product> list, int accountId, int page, int pageSize) {

        List<Product> filtered = new ArrayList<>();
        for (Product find : list) {
            if (find.id == accountId) {
                filtered.add(find);
            }
        }
        Predicate<Product> pred = new Predicate<Product>() {
            @Override
            public boolean predicate(Product arg) {
                return true;
            }
        };
        List<Product> paginated = paginate(filtered, page, pageSize, pred);
        return paginated;
    }

    public static List<Product> filterByCategory(List<Product> list, ProductCategory category) {

        List<Product> filtered = new ArrayList<>();
        for (Product search : list) {
            if (search.category.equals(category))
                filtered.add(search);
        }
        return filtered;
    }

    public static List<Product> filterByName(List<Product> list, String search, int page, int pageSize) {

        List<Product> filtered = new ArrayList<>();
        for (Product get : list) {
            if (get.name.equalsIgnoreCase(search))
                filtered.add(get);
        }
        Predicate<Product> pred = new Predicate<Product>() {
            @Override
            public boolean predicate(Product arg) {
                return true;
            }
        };
        List<Product> paginated = paginate(filtered, page, pageSize, pred);
        return paginated;
    }

    public static List<Product> filterByPrice(List<Product> list, double minPrice, double maxPrice) {

        List<Product> filtered = new ArrayList<Product>();

        if (minPrice == 0.0) {
            for (Product search : list) {
                if (search.price <= maxPrice)
                    filtered.add(search);
            }
        }
        else if (maxPrice == 0.0) {
            for (Product search : list) {
                if (search.price >= minPrice)
                    filtered.add(search);
            }

        }
        return filtered;
    }

    private static List<Product> paginate (List<Product> list, int page, int pageSize, Predicate<Product> pred) {

        List<Product> paginated = new ArrayList<Product>();
        paginated = Algorithm.paginate(list, page, pageSize, pred);
        return paginated;
    }

    public static List<Product> read (String filepath) throws FileNotFoundException {

        List<Product> temp = new ArrayList<Product>();
        final JsonReader reader = new JsonReader(new FileReader(filepath));

        int accountId = 0;
        String category = null;
        boolean conditionUsed = false;
        double discount = 0.0;
        String name = null;
        double price = 0.0;
        int shipmentPlans = 0;
        int weight = 0;
        int id = 0;

        try {
            reader.beginArray();
            while(reader.hasNext()) {
                reader.beginObject();
                while (reader.hasNext()) {
                    String read = reader.nextName();
                    if (read.equals("accountId")) {
                        accountId = reader.nextInt();
                    } else if (read.equals("category")) {
                        category = reader.nextString();
                    } else if (read.equals("conditionUsed")) {
                        conditionUsed = reader.nextBoolean();
                    } else if (read.equals("discount")) {
                        discount = reader.nextDouble();
                    } else if (read.equals("name")) {
                        name = reader.nextString();
                    } else if (read.equals("price")) {
                        price = reader.nextDouble();
                    } else if (read.equals("shipmentPlans")) {
                        shipmentPlans = reader.nextInt();
                    } else if (read.equals("weight")) {
                        weight = reader.nextInt();
                    } else if (read.equals("id")) {
                        id = reader.nextInt();
                    } else {
                        reader.skipValue();
                    }
                }
                reader.endObject();
                ProductCategory categoryEnum = ProductCategory.valueOf(category);
                temp.add(id, new Product(accountId, name, weight, conditionUsed, price, discount, categoryEnum, (byte)shipmentPlans));
            }
            reader.endArray();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

     */
}
