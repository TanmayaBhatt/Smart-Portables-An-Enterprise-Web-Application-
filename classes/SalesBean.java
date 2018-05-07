

public class SalesBean {
    
    String productName ;
    String date;
    double productPrice;
    int item;
    int sold;
    double sale;
    int order;
    
    
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
    
    
    public void setSold(int sold)
    {
        this.sold=sold;
    }
    
    public int getSold()
    {
        return sold;
    }
    public void setSale(double sale)
    {
        this.sale=sale;
    }
    public double getSale()
    {
        return sale;
    }
    public void setDate(String date)
    {
        this.date=date;
    }
    public String getDate()
    {
        return date;
    }
    
    public void setOrderNo(int order)
    {
        this.order=order;
    }
    
    public int getOrder()
    {
        return order;
    }
}


