package com.imamJmartMR.controller;

import com.imamJmartMR.*;
import com.imamJmartMR.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product>  {

    public @JsonAutowired(value = Product.class, filepath = "E:\\Imam Azka\\Semester 3\\Java\\Jmart\\src\\main\\resources\\ProductList.json") static JsonTable<Product> productTable;

    @Override
    public JsonTable<Product> getJsonTable () {
        if (productTable == null || productTable.isEmpty())
            return null;
        return productTable;
    }

    @PostMapping("/create")
    Product create (@RequestParam int accountId, @RequestParam String name, @RequestParam int weight, @RequestParam boolean conditionUsed, @RequestParam double price, @RequestParam double discount, @RequestParam ProductCategory category,@RequestParam byte shipmentPlans) {
        if (productTable == null || productTable.isEmpty())
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
        if (productTable == null || productTable.isEmpty())
            return null;
        List<Product> temp = new ArrayList<>();

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
            @RequestParam(value="page", defaultValue = "1") int page,
            @RequestParam(value="pageSize", defaultValue = "1") int pageSize,
            @RequestParam(value="accountId", defaultValue = "-1") int accountId,
            @RequestParam(value="search", defaultValue = "") String search,
            @RequestParam(value="minPrice", defaultValue = "0.0") double minPrice,
            @RequestParam(value="maxPrice", defaultValue = "0.0") double maxPrice,
            @RequestParam(value="category", defaultValue = "") ProductCategory category)
    {
        if (productTable == null || productTable.isEmpty())
            return null;

        if (page <= 0)
            page = 1;
        else if (pageSize <= 0)
            pageSize = 1;

        List<Product> temp = productTable;
        List<Product> filtered;

        if (accountId != -1) {
            filtered = temp.stream().filter(get -> get.accountId == accountId).collect(Collectors.toList());
            temp = filtered;
        }
        if (!search.isBlank()) {
            filtered = temp.stream().filter(get -> get.name.equals(search)).collect(Collectors.toList());
            temp = filtered;
        }
        if (minPrice != 0.0) {
            filtered = temp.stream().filter(get -> get.price >= minPrice).collect(Collectors.toList());
            temp = filtered;
        }
        if (maxPrice != 0.0) {
            filtered = temp.stream().filter(get -> get.price <= maxPrice).collect(Collectors.toList());
            temp = filtered;
        }
        if (category != null) {
            filtered = temp.stream().filter(get -> get.category == category).collect(Collectors.toList());
            temp = filtered;
        }

        if (temp.isEmpty())
            return null;

        int fromIndex = (page - 1) * pageSize;
        return temp.subList(fromIndex, Math.min(fromIndex + pageSize, temp.size()));
    }
}
