import java.io.Serializable;
import java.util.ArrayList;

public class OrderBean implements Serializable {
    
    private String userName;
    private int OrderId;
    private ArrayList<Cart> a;
    
    
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getOrderId() {
        return OrderId;
    }
    public void setOrderId(int OrderId) {
        this.OrderId = OrderId;
    }
    public ArrayList<Cart> getList() {
        return a;
    }
    public void setList(ArrayList<Cart> a) {
        this.a = a;
    }
    
}
