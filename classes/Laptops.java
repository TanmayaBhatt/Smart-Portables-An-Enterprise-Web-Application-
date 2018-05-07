public class Laptops {
	String companyName;
	String productId; // key
	String productName;
	double price;
	String imageName;
    String sale;
    String rebate;
    double item;
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
    public void setSale(String sale)
    {
        this.sale= sale;
    }
    public String getSale()
    {
        return sale;
    }
    public void setRebate(String rebate)
    {
        this.rebate=rebate;
    }
    public String getRebate()
    {
        return rebate;
    }
    public void setItem(double item )
    {
        this.item=item;
    }
    public double getItem()
    {
        return item;
    }

}

