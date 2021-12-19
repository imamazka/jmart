package com.imamJmartMR;

/**
 * Store ratings from user for a product.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

public class ProductRating{
    
    private long total;
    private long count;

    /**
     * Initial rating
     */
    public ProductRating(){
        this.total = 0;
        this.count = 0;
    }

    /**
     * Count rating average
     * @return average rating
     */
    public double getAverage(){
        if(count == 0.0){
            return 0.0;
        }
        else{
            return total/count;
        }
    }

    /**
     * Get number of rating
     * @return number of rating
     */
    public long getCount(){
        return count;
    }

    /**
     * Get rating total
     * @return rating total
     */
    public long getTotal(){
        return total;
    }

    /**
     * Insert new rating
     * @param rating rating from user
     */
    public void insert(int rating){
        this.total = total + rating;
        this.count++;
    }
}
