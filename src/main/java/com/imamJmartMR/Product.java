package com.imamJmartMR;

/**
 * This class store details about a product.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

public class Product extends Serializable
{
    
    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;

    /**
     * Constructor for new product
     * @param accountId store owner id
     * @param name product name
     * @param weight product weight
     * @param conditionUsed product condition
     * @param price product price
     * @param discount product discount
     * @param category product category
     * @param shipmentPlans product shipping plans
     */
    public Product(int accountId, String name, int weight, boolean conditionUsed, double price, double discount, ProductCategory category, byte shipmentPlans){
        this.accountId = accountId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.shipmentPlans = shipmentPlans;
    }

    /**
     * Get product details
     * @return product details to string
     */
    public String toString(){
        return String.format("Name: %s\nWeight: %d\nconditionUsed: %b\nprice: %f\ncategory: %s\nshipmentPlans: %d\naccountId: %d", name, weight, conditionUsed, price, category, shipmentPlans, accountId);
    }
}
