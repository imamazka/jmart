package com.imamJmartMR.controller;

import com.imamJmartMR.*;
import com.imamJmartMR.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product>  {

    public @JsonAutowired(value = Product.class, filepath = "E:\\Imam Azka\\Semester 3\\Java\\Jmart\\src\\main\\resources\\ProductList.json") static JsonTable<Product> productTable;

    @Override
    public JsonTable<Product> getJsonTable () {
        return productTable;
    }

    @PostMapping("/create")
    Product create (@RequestParam int accountId, @RequestParam String name, @RequestParam int weight, @RequestParam boolean conditionUsed, @RequestParam double price, @RequestParam double discount, @RequestParam ProductCategory category,@RequestParam byte shipmentPlans) {
        if (productTable == null)
            return null;
        for (Product get : productTable) {
            if (get.accountId == accountId)
                return null;
            else {
                Product temp = new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
                productTable.add(temp);
                return temp;
            }
        }
        return null;
    }

    @GetMapping("/{id}/store")
    List<Product> getProductByStore (@PathVariable int id, @RequestParam int page, @RequestParam int pageSize) {
        if (productTable == null)
            return null;
        List<Product> temp = new ArrayList<Product>();

        for (Product get : productTable) {
            if (get.accountId == id) {
                temp.add(get);
            }
        }
        int index = (page - 1) * pageSize;
        return temp.subList(index, Math.min(index + pageSize, temp.size()));
    }

    @GetMapping("/getFiltered")
    List<Product> getProductFiltered (
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "1") int pageSize,
            @RequestParam(defaultValue = "-1") int accountId,
            @RequestParam(defaultValue = "null") String search,
            @RequestParam(defaultValue = "0") int minPrice,
            @RequestParam(defaultValue = "0") int maxPrice,
            @RequestParam(defaultValue = "null") ProductCategory category)
    {
        if (productTable == null)
            return null;

        List<Product> temp = new ArrayList<Product>();
        int fromIndex = (page - 1) * pageSize;

        if (page > 0 && pageSize > 0)
            return productTable.subList(fromIndex, Math.min(fromIndex + pageSize, productTable.size()));
        else if (accountId != 0) {
            for (Product get : productTable) {
                if (get.accountId == accountId)
                    temp.add(get);
            }
        }
        else if (search != null) {
            for (Product get : productTable) {
                if (get.name.equals(search))
                    temp.add(get);
            }
        }
        else if (minPrice != 0) {
            for (Product get : productTable) {
                if (get.price >= minPrice)
                    temp.add(get);
            }
        }
        else if (maxPrice != 0) {
            for (Product get : productTable) {
                if (get.price <= maxPrice)
                    temp.add(get);
            }
        }
        else if (category != null) {
            for (Product get : productTable) {
                if (get.category == category)
                    temp.add(get);
            }
        }

        return temp;
    }
}
