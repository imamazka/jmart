package com.imamJmartMR;

public class ProductRating{
    
    private long total;
    private long count;
    
    public ProductRating(){
        this.total = 0;
        this.count = 0;
    }
    
    public double getAverage(){
        if(count == 0.0){
            return 0.0;
        }
        else{
            return total/count;
        }
    }
    
    public long getCount(){
        return count;
    }
    
    public long getTotal(){
        return total;
    }

    public void insert(int rating){
        this.total = total + rating;
        this.count++;
    }
}
