
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
public class MySQLDataStoreUtilities {
	
	Connection connection=null;
	Statement statement=null;
	PreparedStatement preparedStatement=null;
	ResultSet rs = null;
   PreparedStatement st= null;
	/*public static void main(String args[])
    {
        ArrayList<Cart> arr= new ArrayList<Cart>();
        MySQLDataStoreUtilities obj =new MySQLDataStoreUtilities();
        arr=obj.getOrderDetails("aaa");
    }*/
	
	//Code for adding a User ot the database module
	
	public int addUser(HashMap<String,UserBean> hmb) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		UserBean userBean = new UserBean();
		Set set = hmb.entrySet();
		Iterator iterator = set.iterator();
		
		while(iterator.hasNext())
		{
			Map.Entry entry   = (Map.Entry)iterator.next();
			userBean = (UserBean)entry.getValue();
			
		}
		String userName = userBean.getUserName();
		String passWord = userBean.getPassWord();
		String role     = userBean.getRole();
		
	
		
		createConnection();
        String query = "INSERT INTO REGISTRATION(USERNAME,PASSWORD,USERTYPE)"+"VALUES(?,?,?);";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1,userName);
		preparedStatement.setString(2,passWord);
		preparedStatement.setString(3,role);
		
		int result  = preparedStatement.executeUpdate();
	
		
		
		return result ;
	}
	
	
	// Code for retrieving a UserName 
	
	public int userExists( String userName){
		
		HashMap<String, UserBean> userList = new HashMap<String,UserBean>();
		UserBean userBean 	= new UserBean();
		ResultSet resultSet;
		int result =0;
		
		try {
			createConnection();
            String query = "SELECT USERNAME FROM REGISTRATION WHERE USERNAME = ? ;";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,userName);
			resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next())
			{
				result = 0;
			}
			else
			{
				result = 1;
			}
			
			
			
		} 
		
		catch (Exception e) {
			
			e.printStackTrace();
		} 
				
		return result;
		
	}
	
	// Code for Login and PassWord
	
	public HashMap<String, LoginBean> getLoginDetails(String userName)
	{
		HashMap<String, LoginBean> hlb = new HashMap<String, LoginBean>();
		ResultSet resultSet;
		LoginBean loginBean = new LoginBean();
		try {
			createConnection();
            String query = "SELECT USERNAME,PASSWORD,USERTYPE FROM REGISTRATION WHERE USERNAME = ? ;";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,userName);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				loginBean.setUserName(resultSet.getString("USERNAME"));
				loginBean.setPassWord(resultSet.getString("PASSWORD"));
				loginBean.setRole(resultSet.getString("USERTYPE"));
			}
			hlb.put(loginBean.getUserName(), loginBean);
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}

		
		
		
		return hlb;
	}
	
	
	
	public int addOrderDetails(HashMap<String,Cart> hm, String creditCardNumber,String address,String user ) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{

        Random rand = new Random();
        int i = rand.nextInt(100) + 1;
        
        int result=0;
        for (String key : Cart.cartMap.keySet()) {
           Cart c = Cart.cartMap.get(key);
            double cc= Double.parseDouble(creditCardNumber);
            String productName= c.getProductName();
            double price =c.getPrice();
            createConnection();
            String query = "INSERT INTO CustomerORders(ORDERID, USERNAME, ORDERNAME, ORDERPRICE, USERADDRESS, CREDITCARDNO)"+"VALUES(?,?,?,?,?,?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,i);
            preparedStatement.setString(2,user);
            preparedStatement.setString(3,productName);
            preparedStatement.setDouble(4,price);
            preparedStatement.setString(5,address);
            preparedStatement.setString(6, creditCardNumber);
            result = preparedStatement.executeUpdate();
 
	 }
	 
	 
	 return i;
	}
	
		
    
		public ArrayList<ProductBean> getProducts()
		{
			
			ResultSet resultSet;
            ArrayList<ProductBean> arr= new ArrayList<ProductBean>();
			try {
				createConnection();
				String query = "SELECT  * FROM productDetails ";
               /* preparedStatement.setInt(1,i);
                preparedStatement.setString(2,user);
                preparedStatement.setString(3,productName);
                preparedStatement.setDouble(4,price);
                preparedStatement.setString(5,address);
                preparedStatement.setString(6, creditCardNumber);
				PreparedStatement preparedStatement = connection.prepareStatement(query);*/
				PreparedStatement preparedStatement = connection.prepareStatement(query);
                
				resultSet = preparedStatement.executeQuery();
                
			while(resultSet.next())
            {  ProductBean  p = new ProductBean();
                 String product_id=resultSet.getString("product_id");
                //out.println(orderId);
                String retailer= resultSet.getString("retailer");
                //out.println(userName);
                String  productName=resultSet.getString("product_name");
               //out.println(userName);
                double price= resultSet.getDouble("price");
                //out.println(price);
                double item= resultSet.getDouble("item");
                //out.println(creditcard);
                String rebate= resultSet.getString("rebate");
                //out.println(address);
                 String sale= resultSet.getString("sale");
                p.setRetailer(retailer);
                p.setName(productName);
               // p.setCondtion(condition);
                p.setPrice(price);
                p.setItem(item);
               // p.setSale(sale);
               // p.setRebate(rebate);
                p.setId(product_id);
                arr.add(p);

					}
					

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return arr;
		}
    
    
    public HashMap<String,ArrayList<ProductBean>> viewAllProducts()
    {
        HashMap<String,ArrayList<ProductBean>> hpb = new HashMap<String,ArrayList<ProductBean>>();
        ResultSet resultSet = null;
        ArrayList<ProductBean> proBeans = new ArrayList<ProductBean>();
        try {
            createConnection();
            String query = "SELECT PRODUCT_ID, PRODUCT_NAME ,PRICE, RETAILER FROM PRODUCTDETAILS";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next())
            {
                ProductBean productBean = new ProductBean();
                productBean.setId(resultSet.getString("PRODUCT_ID"));
                productBean.setName(resultSet.getString("PRODUCT_NAME"));
                productBean.setPrice(resultSet.getDouble("PRICE"));
                productBean.setRetailer(resultSet.getString("RETAILER"));
                proBeans.add(productBean);
            }
            hpb.put("PRODUCTS", proBeans);
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return hpb;
    }
		
    public void deleteProduct(String productId)
    {
        int result = 0;
        try {
            createConnection();
            String query = "DELETE FROM ProductDetails WHERE PRODUCT_ID= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productId);
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        
    }
    public ArrayList<Cart> getOrderDetails(String user)
    {
        
        ResultSet resultSet;
        ArrayList<Cart> arr= new ArrayList<Cart>();
        try {
            createConnection();
            String query = "SELECT OrderID, UserName, ordername, orderprice,userADDRESS, creditcardno  FROM CustomerOrders WHERE username = ?";
            /* preparedStatement.setInt(1,i);
             preparedStatement.setString(2,user);
             preparedStatement.setString(3,productName);
             preparedStatement.setDouble(4,price);
             preparedStatement.setString(5,address);
             preparedStatement.setString(6, creditCardNumber);
             PreparedStatement preparedStatement = connection.prepareStatement(query);*/
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next())
            {   Cart  c = new Cart();
                int orderId=resultSet.getInt("OrderID");
                //out.println(orderId);
                String userName= resultSet.getString("USERNAME");
                //out.println(userName);
                String  productName=resultSet.getString("orderName");
                //out.println(userName);
                double price= resultSet.getDouble("orderprice");
                //out.println(price);
                String creditcard= resultSet.getString("CREDITCARDno");
                //out.println(creditcard);
                String address= resultSet.getString("userADDRESS");
                //out.println(address);
                c.setCount(orderId);
                c.setProductName(productName);
                c.setCredit(creditcard);
                c.setPrice(price);
                c.setAddress(address);
                arr.add(c);
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return arr;
    }
    

		
		public int cancelOrder(int orderNumber)
		{
			int result =0;
			
			try{
				createConnection();
				String query = "DELETE FROM CustomerOrders WHERE ORDERID = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, orderNumber);
				result = preparedStatement.executeUpdate();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return result;
		}
			

		
    public void addProducts(String productName,String companyName,String productId, double price, String sale, String rebate, double item ) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        
        
        int result=0;
       // create table productdetails(product_id VARCHAR(20), retailer VARCHAR(20), product_name VARCHAR(20), price VARCHAR(20), item VARCHAR(20), rebate VARCHAR(20), sale VARCHAR(20));
        //ALTER TABLE productdetails ADD CONSTRAINT pk_prod_id PRIMARY KEY (product_id);

    //create table prod_acc(product_id VARCHAR(20), acc_id VARCHAR(20), FOREIGN KEY (product_id) REFERENCES productdetails(product_id), FOREIGN KEY (acc_id) REFERENCES productdetails(product_id));
        
            createConnection();
            String query = "INSERT INTO productdetails(product_id, retailer, product_name,price, item, rebate,sale)"+"VALUES(?,?,?,?,?,?,?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,productId);
            preparedStatement.setString(2,companyName);
            preparedStatement.setString(3,productName);
            preparedStatement.setDouble(4,price);
            preparedStatement.setDouble(5,item);
            preparedStatement.setString(6, rebate);
        preparedStatement.setString(7, sale);
            result = preparedStatement.executeUpdate();
        
        
        //return result;
    }
    
    public void addAccessory(String productName,String companyName,String productId, double price,String ParentProductId) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        
        
        int result=0;
        // create table productdetails(product_id VARCHAR(20), retailer VARCHAR(20), product_name VARCHAR(20), price VARCHAR(20), item VARCHAR(20), rebate VARCHAR(20), sale VARCHAR(20));
        //ALTER TABLE productdetails ADD CONSTRAINT pk_prod_id PRIMARY KEY (product_id);
        
        //create table prod_acc(product_id VARCHAR(20), acc_id VARCHAR(20), productName(40), companyName(40), price varchar(40), FOREIGN KEY (product_id) REFERENCES productdetails(product_id), FOREIGN KEY (acc_id) REFERENCES productdetails(product_id));
        
        createConnection();
        String query = "INSERT INTO prod_acc(product_id,acc_id, productName, companyName,price )"+"VALUES(?,?,?,?,?)";
        String price1= price+"";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,ParentProductId);
        preparedStatement.setString(2,productId);
        preparedStatement.setString(3,productName);
        preparedStatement.setString(4,companyName);
        preparedStatement.setString(5,price1);

        result = preparedStatement.executeUpdate();
        
        
        //return result;
    }

		public ArrayList<Cart>  getSalesOrder()
		{
            ResultSet resultSet;
            ArrayList<Cart> arr= new ArrayList<Cart>();
            try {
                createConnection();
                String query = "SELECT OrderID, UserName, ordername, orderprice,userADDRESS, creditcardno  FROM CustomerOrders";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				
                while(resultSet.next())
                {   Cart  c = new Cart();
                    int orderId=resultSet.getInt("OrderID");
                    //out.println(orderId);
                    String userName= resultSet.getString("USERNAME");
                    //out.println(userName);
                    String  productName=resultSet.getString("orderName");
                    //out.println(userName);
                    double price= resultSet.getDouble("orderprice");
                    //out.println(price);
                    String creditcard= resultSet.getString("CREDITCARDno");
                    //out.println(creditcard);
                    String address= resultSet.getString("userADDRESS");
                    //out.println(address);
                    c.setCount(orderId);
                    c.setProductName(productName);
                    c.setCredit(creditcard);
                    c.setPrice(price);
                    c.setAddress(address);
                    arr.add(c);
                    
                }
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            return arr;
		}
		
	
    public  ArrayList<InventoryBean> selectInventory(){
        ArrayList<InventoryBean> ib_arr = new ArrayList<InventoryBean>();
        try{
            createConnection();
            String sql = "select product_name, price, item from productdetails";
           PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                InventoryBean ib = new InventoryBean();
                String pname = rs.getString("product_name");
                String price = rs.getString("price");
                String item = rs.getString("item");
                double price_str = Double.parseDouble(price);
                double item_strd = Double.parseDouble(item);
                int item_str=(int)item_strd;
                ib.setName(pname);
                ib.setPrice(price_str);
                ib.setItem(item_str);
                ib_arr.add(ib);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ib_arr;
    }
    
    public  ArrayList<String> selectSale() {
        ArrayList<String> ib_arr = new ArrayList<String>();
        try{
            createConnection();
            String sql = "select product_name, sale from productdetails";
           PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                String sale = rs.getString("sale");
                //InventoryBean ib = new InventoryBean();
                if(sale.equalsIgnoreCase("yes")){
                    String name = rs.getString("product_name");
                    ib_arr.add(name);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ib_arr;
    }
    
    public  ArrayList<String> selectRebate() {
        ArrayList<String> ib_arr = new ArrayList<String>();
        try{
            createConnection();
            String sql = "select product_name, rebate from productdetails";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                String sale = rs.getString("rebate");
                //InventoryBean ib = new InventoryBean();
                if(sale.equalsIgnoreCase("yes")){
                    String name = rs.getString("product_name");
                    ib_arr.add(name);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ib_arr;
    }
    
    public  ArrayList<SalesBean> selectSales(){
        ArrayList<SalesBean> sb_arr = new ArrayList<SalesBean>();
        try{
            createConnection();
            createConnection();
            String query = "select p.product_name, p.price, p.item, count(o.ordername) as product_sold from productdetails p, customerorders o where (p.product_name = o.ordername) group by p.product_id";
            st = connection.prepareStatement(query);
            rs = st.executeQuery();
            
            while(rs.next()){
                SalesBean sb = new SalesBean();
                String pname = rs.getString("p.product_name");
                double price = Double.parseDouble(rs.getString("p.price"));
                double item = Double.parseDouble(rs.getString("p.item"));
                
                int sold = Integer.parseInt(rs.getString("product_sold"));
                double sale= price * sold;
                sb.setName(pname);
                sb.setPrice(price);
                sb.setItem((int)item);
                sb.setSold(sold);
                sb.setSale(sale);
                sb_arr.add(sb);
                //out.println("name:"+sb.getName());
                //out.println("price:"+sb.getPrice());
                //out.println("sales:"+ sb.getSale());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return sb_arr;
    }
    
    
    public  ArrayList<SalesBean> selectDateTransaction(){
        ArrayList<SalesBean> sb_arr = new ArrayList<SalesBean>();
        try{
            createConnection();
            String query =" set sql_mode=' '";
             st = connection.prepareStatement(query);
            rs = st.executeQuery();
            query = "select date,orderprice,ordername, count(orderid) from customerorders group by date";
            st = connection.prepareStatement(query);
            rs = st.executeQuery();
            
            while(rs.next()){
                SalesBean sb = new SalesBean();
                String date = rs.getString("date");
                int items = Integer.parseInt(rs.getString("count(orderid)"));
                double price= Double.parseDouble(rs.getString("orderprice"));
                double sale= price * items;
                String name= rs.getString("ordername");
                sb.setDate(date);
                sb.setOrderNo(items);
                sb.setSale(sale);
                sb.setName(name);
                sb_arr.add(sb);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return sb_arr;
    }
    //String sql = "select ordername, count(ordername) from customerorders group by ordername";
    
    public ArrayList<SalesBean> selectSalesBarChart(){
     ArrayList<SalesBean> sb_arr = new ArrayList<SalesBean>();
     try{
     createConnection();
     String query =" set sql_mode=' '";
     st = connection.prepareStatement(query);
     rs = st.executeQuery();
     query = "select ordername, count(ordername),orderprice from customerorders group by ordername";
     st = connection.prepareStatement(query);
     rs = st.executeQuery();
     
     while(rs.next()){
     SalesBean sb = new SalesBean();
     int items = Integer.parseInt(rs.getString("count(ordername)"));
     double price= Double.parseDouble(rs.getString("orderprice"));
     double sale= price * items;
     String name= rs.getString("ordername");
     sb.setOrderNo(items);
     sb.setSale(sale);
     sb.setName(name);
     sb_arr.add(sb);
     }
     }
     catch(Exception e){
     e.printStackTrace();
     }
     return sb_arr;
    }
		/*public int updateSalesOrder(int orderNumber)
		{
			int result= 0;
			
			try {
				createConnection();
				String query = "UPDATE ORDER_DETAILS SET STATUS=2 WHERE ORDER_NUMBER = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, orderNumber);
				result = preparedStatement.executeUpdate();
				
				
			} catch (Exception e) {				
				e.printStackTrace();
			}
			return result;
		}*/
		

	// Code for Connection
	public Connection createConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		String driverName="com.mysql.jdbc.Driver";
		Class.forName(driverName).newInstance();
		String serverName="localhost";
		String portNumber="3306";
		String sid="Exampledatabase";
		String url="jdbc:mysql://"+serverName+":"+portNumber+"/"+sid;
		String username="root";
		String password="tanmaya";
		connection=DriverManager.getConnection(url, username, password);
		
		statement=connection.createStatement();
		return connection;
	}
}
