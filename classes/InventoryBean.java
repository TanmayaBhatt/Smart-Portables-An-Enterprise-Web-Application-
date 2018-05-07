

public class InventoryBean {
    
   String productName ;
   double productPrice;
   int item;
   
    
    
    public double getPrice() {
        return productPrice;
    }
    
    public void setPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    
  
    public void setItem(int item)
    {
        this.item=item;
    }
    
    public int getItem()
    {
        return item;
    }
    
    public String getName() {
        return productName;
    }
    
    public void setName(String productName) {
        this.productName = productName;
    }

    
    
}

