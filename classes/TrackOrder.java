

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

public class TrackOrder extends HttpServlet implements Serializable {
    
    
    
    /* protected void processRequest(HttpServletRequest request, HttpServletResponse response)
     {
     String username = request.getParameter("username");
     String password = request.getParameter("password");
     String role = request.getParameter("role");
     
     
     loginBean.setUserName(username);
     loginBean.setPassWord(password);
     loginBean.setRole(role);
     
     
     }*/
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //processRequest(request,response);
        PrintWriter out = response.getWriter();
        ArrayList<Cart> arr= new ArrayList<Cart>();
        // HashMap<String, Cart> hm =
        MySQLDataStoreUtilities ob= new MySQLDataStoreUtilities();
        HttpSession session = request.getSession(true);
        String id = session.getAttribute("username").toString();
        arr=ob.getSalesOrder();
        //out.println(arr.size());
        
        
        /* ResultSet resultSet;
         // ArrayList<Cart> arr= new ArrayList<Cart>();
         try {
         Connection connection= ob.createConnection();
         String query = "SELECT OrderID, UserName, ordername, orderprice,userADDRESS, creditcardno  FROM CustomerOrders WHERE username = ?";
         /* preparedStatement.setInt(1,i);
         preparedStatement.setString(2,user);
         preparedStatement.setString(3,productName);
         preparedStatement.setDouble(4,price);
         preparedStatement.setString(5,address);
         preparedStatement.setString(6, creditCardNumber);
         PreparedStatement preparedStatement = connection.prepareStatement(query);*/
        /*   PreparedStatement preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1, id);
         
         resultSet = preparedStatement.executeQuery();
         
         while(resultSet.next())
         {  Cart  c = new Cart();
         int orderId=resultSet.getInt("OrderID");
         out.println(orderId);
         String userName= resultSet.getString("USERNAME");
         out.println(userName);
         String  productName=resultSet.getString("orderName");
         out.println(userName);
         double price= resultSet.getDouble("orderprice");
         out.println(price);
         String creditcard= resultSet.getString("CREDITCARDno");
         out.println(creditcard);
         String address= resultSet.getString("userADDRESS");
         out.println(address);
         c.setCount(orderId);
         c.setProductName(productName);
         c.setCredit(creditcard);
         c.setPrice(price);
         c.setAddress(address);
         arr.add(c);
         
         }
         
         
         } catch (Exception e) {
         e.printStackTrace();
         }*/
        
        // out.println("Size="+arr.size());
        
        //  for(int i = 0; i < arr.size(); i++) {
        //    out.println(arr.get(i).getCount());
        //    out.println(arr.get(i).getProductName());
        // }
        //ServletContext sc = request.getServletContext();
        //String fileName= "/Library/tomcat/webapps/csj/OrderDetails.txt";
        
        /*try{
         
         FileInputStream fileInputStream     = new FileInputStream(new File(fileName));
         ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
         
         hmb = (HashMap)objectInputStream.readObject();
         }
         catch(Exception e)
         {
         e.printStackTrace();
         System.out.println("File not read !!!!");
         }*/
        
        /*System.out.println(loginBean.getUserName()+"1  "+ hmb.get(loginBean.getUserName()).getRole()+" 2"+hmb.get(loginBean.getUserName()).getPassWord().equals(loginBean.getPassWord()));
         
         if(hmb.containsKey(loginBean.getUserName()) && hmb.get(loginBean.getUserName()).getRole().equals(loginBean.getRole())  && hmb.get(loginBean.getUserName()).getPassWord().equals(loginBean.getPassWord()))
         {*/
        /* Set set = hmb.entrySet();
         Iterator iterator = set.iterator();
         while(iterator.hasNext()) {
         Map.Entry mentry = (Map.Entry)iterator.next();
         out.println("key: "+ mentry.getKey() + " & Value: ");
         OrderBean ob=(OrderBean)mentry.getValue();
         out.println(ob.getUserName());
         out.println(ob.getOrderId());*/
        
        
        out.println("<!doctype html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        out.println("<title>Smart Portables</title>");
        out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
        out.println("<meta name=\"viewport\" content=\"width=device-width, minimum-scale=1.0, maximum-scale=1.0\" />");
        out.println("</head>");
        out.println("<body>");
        out.println("<div id=\"container\">");
        out.println("<header>");
        out.println("<div class=\"width\">");
        out.println("<h1><a href=\"/\">CS<span>J</span></a></h1>");
        out.println("</div>");
        out.println("</header>");
        out.println("<nav>");
        out.println("<div class=\"width\">");
        out.println("<ul>");
        out.println("<li class=\"start\"><a href=\"customer.html\">Home</a></li>");
        out.println("<li class=\"product\"><a href=\"Products\">Products</a></li>");
        out.println("<li class=\"selected\"><a href=\"#\">TrackOrderCust</a></li>");
        out.println("<li><a href=\"#\">Contact</a></li>");
        out.println("<li style=\"float:right;\"><a href=\"#\">Cart </a></li>");
        out.println("<li class=\"end\" style=\"float:right;\"><a href=\"login.html\">SignOut</a></li>");
        out.println("</ul>");
        out.println("</div>");
        out.println("</nav>");
        
        out.println("<div id=\"body\" class=\"width\">");
        
        out.println("<section id=\"content\">");
        
        out.println("<article>");
        
        out.println("<p>&nbsp;</p>");
        
        out.println("<h3>Table</h3>");
        
        out.println("<table cellspacing=\"0\">");
        out.println("<tr>");
        out.println("<th>OrderNo</th>");
       
        out.println("<th>ProductName</th>");
        out.println("<th>Price</th>");
        out.println("<th>Address</th>");
        out.println("<th>CreditCard</th>");
        
        out.println("</tr>");
        
        //out.println("key: "+ mentry.getKey() + " & Value: ");
        //OrderBean ob=(OrderBean)mentry.getValue();
        //out.println(ob.getUserName());
        //out.println(ob.getOrderId());
        
        
        out.println("<tr>");
        for(int i = 0; i < arr.size(); i++) {
            out.println("<td>"+arr.get(i).getCount()  +"</td>");
            
            out.println("<td>"+arr.get(i).getProductName()+"</td>");
            out.println("<td>"+arr.get(i).getPrice()+"</td>");
            out.println("<td>"+arr.get(i).getAddress()+"</td>");
            out.println("<td>"+arr.get(i).getCredit()+"</td>");
            out.println("<td><form action=\"DeleteOrder\" method=\"get\">");
            out.println("<input type=\"hidden\" name=\"username\" value="+ id +" > \n");
            out.println("<input type=\"hidden\" name=\"orderId\" value="+ arr.get(i).getCount()+ ">\n");
            out.println("<input type=\"submit\" value=\"Delete\" class=\"formbutton\" > \n </form>");
            out.println("</td>");
            //out.println("<form<td><input type=\"submit\" name=\"fetchdata\" value=\"Delete\" action=\"Delete\" /></td>");
            out.println("</tr>");
            
            
            
        }
        /*out.println("<tr>");
         out.println("<td>2</td>");
         out.println("<td>Fred James</td>");
         out.println("<td>49</td>");
         out.println("</tr>");
         out.println("<tr>");
         out.println("<td>3</td>");
         out.println("<td>Rachel Johnson</td>");
         out.println("<td>19</td>");
         out.println("</tr>");*/
        
        out.println("</table>");
        
        out.println("<p>&nbsp;</p>");
        
        
        
        out.println("</article>");
        out.println("</section>");
        
        out.println("<aside class=\"sidebar\">");
        out.println("<br><br>");
        
        out.println("<ul>");
        out.println("<li>");
        out.println("<h4>Categories</h4>");
        out.println("<ul>");
        out.println("<li><a href=\"#\">Smart Watches</a></li>");
        out.println("<li><a href=\"#\">Speakers</a></li>");
        out.println("<li><a href=\"#\">HeadPhones</a></li>");
        out.println("<li><a href=\"#\">Phones</a></li>");
        out.println("<li><a href=\"#\">Laptops</a></li>");
        out.println("<li><a href=\"#\">External Storage</a></li>");
        out.println("</ul>");
        out.println("</li>");
        
        out.println("<li>");
        out.println("<h4>About us</h4>");
        out.println("<ul>");
        out.println("<li class=\"text\">");
        out.println("<p style=\"margin: 0;\">");
        out.println("Example website for online retail of smart portables.");
        
        out.println("<a href=\"#\" class=\"readmore\">Read More &raquo;</a></p>");
        out.println("</li>");
        out.println("</ul>");
        out.println("</li>");
        
        out.println("</ul>");
        
        out.println("</aside>");
        out.println("<div class=\"clear\"></div>");
        out.println("</div>");
        out.println("<footer>");
        out.println("<div class=\"footer-bottom\">");
        out.println("<p>&copy; CSJ 2017. Online Retailer smart portables by Tanmaya Bhatt</p>");
        out.println("</div>");
        out.println("</footer>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        
        
    }
    
}

