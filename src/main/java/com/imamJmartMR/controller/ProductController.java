package com.imamJmartMR.controller;

import com.imamJmartMR.*;
import com.imamJmartMR.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product>  {

    public @JsonAutowired(value = Product.class, filepath = "//imamJmartMR//randomProductList.json") static JsonTable<Product> productTable;

    @PostMapping("/create")
    Product create (int accountId, String name, int weight, boolean conditionUsed, double price, double discount, ProductCategory category, byte shipmentPlans) {

        for (Product get : productTable) {
            if (get.id == accountId)
                return null;
            else {
                Product temp = new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
                productTable.add(temp);
                return temp;
            }
        }
        return null;
    }

    @Override
    public JsonTable<Product> getJsonTable () {
        return productTable;
    }

    @GetMapping("/{id}/store")
    List<Product> getProductByStore (int id, int page, int pageSize) {

        List<Product> temp = new ArrayList<Product>();

        for (Product get : productTable) {
            if (get.id == id) {
                temp.add(get);
            }
        }
        int index = (page - 1) * pageSize;
        return temp.subList(index, Math.min(index + pageSize, temp.size()));
    }

    @GetMapping("/getFiltered")
    List<Product> getProductFiltered (int page, int pageSize, int accountId, String search, int minPrice, int maxPrice, ProductCategory category) {
        return null;
    }
}