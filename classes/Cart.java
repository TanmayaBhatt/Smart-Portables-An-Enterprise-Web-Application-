import java.util.HashMap;
import java.io.Serializable;
public class Cart implements Serializable{
	String companyName;
    String address;
    String creditCard;
	String productId; // key
	String productName;
	double price;
	String imageName;
	int count;
	static HashMap<String, Cart> cartMap= new HashMap<String, Cart>();
	public Cart(){
	}
	
	public static int getCartCount(){
		int count = 0;
		for (String key : cartMap.keySet()) {
			count+=cartMap.get(key).count;
		}
		return count;
	}
	public int getCount(){
		return this.count;
	}

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
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCredit() {
        return creditCard;
    }
    
    public void setCredit(String creditCard) {
        this.creditCard = creditCard;
    }
    
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
