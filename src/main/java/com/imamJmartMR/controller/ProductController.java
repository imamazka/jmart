package com.imamJmartMR.controller;

import com.imamJmartMR.*;
import com.imamJmartMR.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for a product.
 * Handle incoming HTTP request and send response back to the caller.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

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

    /**
     * Create a new product
     * @param accountId id of the store owner
     * @param name name of the product
     * @param weight wight of the product
     * @param conditionUsed condition of the product
     * @param price price of the product
     * @param discount discount for the product
     * @param category category of the product
     * @param shipmentPlans plans of shipping of the product
     * @return New created product details
     */
    @PostMapping("/create")
    Product create (@RequestParam int accountId, @RequestParam String name, @RequestParam int weight, @RequestParam boolean conditionUsed, @RequestParam double price, @RequestParam double discount, @RequestParam ProductCategory category,@RequestParam byte shipmentPlans) {
        if (productTable == null)
            return null;

        for (Account get : AccountController.accountTable) {
            if (get.id == accountId && get.store != null) {
                Product temp = new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
                productTable.add(temp);
                return temp;
            }
        }
        return null;
    }

    /**
     * Get product of selected store
     * @param id id of the store
     * @param page selected page
     * @param pageSize size of each page
     * @return List of store owned product
     */
    @GetMapping("/{id}/store")
    List<Product> getProductByStore (@PathVariable int id, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize) {
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

    /**
     * Filter the product list
     * @param page selected page
     * @param pageSize size of each page
     * @param accountId id of the store
     * @param search name of the product
     * @param minPrice minimum price of the product
     * @param maxPrice maximum price of the product
     * @param newCondition condition of the product is new
     * @param usedCondition condition of the product is used
     * @param category category of the product
     * @return List of the filtered product
     */
    @GetMapping("/getFiltered")
    List<Product> getProductFiltered (
            @RequestParam(value="page", defaultValue = "1") int page,
            @RequestParam(value="pageSize", defaultValue = "5") int pageSize,
            @RequestParam(value="accountId", defaultValue = "-1") int accountId,
            @RequestParam(value="search", defaultValue = "") String search,
            @RequestParam(value="minPrice", defaultValue = "0.0") double minPrice,
            @RequestParam(value="maxPrice", defaultValue = "0.0") double maxPrice,
            @RequestParam(value="newCondition", defaultValue = "false") boolean newCondition,
            @RequestParam(value="usedCondition", defaultValue = "false") boolean usedCondition,
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
            filtered = temp.stream().filter(get -> get.name.equalsIgnoreCase(search)).collect(Collectors.toList());
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
        if (newCondition || usedCondition) {
            if (newCondition) {
                filtered = temp.stream().filter(get -> !get.conditionUsed).collect(Collectors.toList());
                temp = filtered;
            }
            else if (usedCondition) {
                filtered = temp.stream().filter(get -> get.conditionUsed).collect(Collectors.toList());
                temp = filtered;
            }
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
