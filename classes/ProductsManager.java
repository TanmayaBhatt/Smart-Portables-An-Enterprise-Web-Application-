import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.sql.PreparedStatement;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductsManager extends HttpServlet  {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
         ArrayList<ProductBean> arr= new ArrayList<ProductBean>();
          MySQLDataStoreUtilities ob= new MySQLDataStoreUtilities();
		PrintWriter out = response.getWriter();
        /*try
        {
        MySQLDataStoreUtilities ob= new MySQLDataStoreUtilities();
       
         Connection connection= ob.createConnection();
        String query = "SELECT  * FROM productDetails ";
        ResultSet resultSet;
        /* preparedStatement.setInt(1,i);
         preparedStatement.setString(2,user);
         preparedStatement.setString(3,productName);
         preparedStatement.setDouble(4,price);
         preparedStatement.setString(5,address);
         preparedStatement.setString(6, creditCardNumber);
         PreparedStatement preparedStatement = connection.prepareStatement(query);
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
        }
        catch(Exception e)
        {
            out.println(e);
        }*/
		//String path = "/Library/tomcat/webapps/csj/";
        //XMLParser xmlParser = new XMLParser("/Library/tomcat/webapps/csj/WEB-INF/ProductCatalog.xml");
        //HashMap<String, SmartWatches> smartWatchesMap = xmlParser.getSmartWatchesMap();
        //HashMap<String, Speakers> speakersMap = xmlParser.getSpeakersMap();
        //HashMap<String, Laptops> laptopsMap = xmlParser.getLaptopsMap();
        //HashMap<String, HeadPhones> headPhonesMap = xmlParser.getHeadPhonesMap();
        //HashMap<String, Phones> phonesMap = xmlParser.getPhonesMap();
        //HashMap<String, ExternalStorage> externalStorageMap = xmlParser.getExternalStorageMap();
        out.print("<!doctype html>");
        out.print("<html>");
        out.print("<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        out.print("<title>Smart Portables</title>");
        out.print("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
        out.print("<meta name=\"viewport\" content=\"width=device-width, minimum-scale=1.0, maximum-scale=1.0\" />");
        out.print("</head>\n<body>\n<div id=\"container\">\n<header>\n<div class=\"width\">\n<h1><a href=\"/\">CS<span>J</span></a></h1>");
        out.print("</div>\n</header>\n<nav>\n<div class=\"width\">\n<ul>\n<li class=\"start\"><a href=\"index.html\">Home</a></li>");
        out.print("<li class=\"selected\"><a href=\"ProductsManager\">Products</a></li><li><a href=\"track.html\">TrackOrder</a></li>");
      //  out.print("<li><a href=\"contact.html\">Contact</a></li>\n<li style=\"float:right;\"><a href=\"cart\">Cart ("+Cart.getCartCount()+")</a></li>");
        //if(id.equals("null"))
            out.print("<li class=\"end\" style=\"float:right;\"><a href=\"login.html\">SignOut</a></li>\n</ul>\n</div>\n</nav>");
        //else
            //out.print("<li class=\"end\" style=\"float:right;\"><a href=\"Logout\">SignOut</a></li>\n</ul>\n</div>\n</nav>");
        out.print("<div id=\"body\" class=\"width\">\n<section id=\"content\">\n<article>\n<br><br>\n<center>\n<h2>Products</h2>");
        out.print("</center>\n<br>\n\n<ul style=\"list-style-type: none;\">\n");
        out.println("<h3>Table</h3>");
        
        out.println("<table cellspacing=\"0\">");
        out.println("<tr>");
        out.println("<th>ProductId</th>");
        
        out.println("<th>ProductName</th>");
        out.println("<th>Retailer</th>");
        out.println("<th>Price</th>");
        out.println("<th>Items</th>");
        
        
        out.println("</tr>");
        
        //out.println("key: "+ mentry.getKey() + " & Value: ");
        //OrderBean ob=(OrderBean)mentry.getValue();
        //out.println(ob.getUserName());
        //out.println(ob.getOrderId());
        
       arr= ob.getProducts();
        
        
        for(int i = 0; i < arr.size(); i++) {
            out.println("<tr>");
            out.println("<td>"+arr.get(i).getId()  +"</td>");
            
            out.println("<td>"+arr.get(i).getName()+"</td>");
            out.println("<td>"+arr.get(i).getRetailer()+"</td>");
            out.println("<td>"+arr.get(i).getPrice()+"</td>");
            out.println("<td>"+arr.get(i).getItem()+"</td>");
            out.println("<td><form action=\"DeleteProduct\" method=\"get\">");
            out.println("<input type=\"hidden\" name=\"ProductId\" value="+ arr.get(i).getId()+" > \n");
           //out.println("<input type=\"hidden\" name=\"orderId\" value="+ arr.get(i).getCount()+ ">\n");
            out.println("<input type=\"submit\" value=\"Delete\" class=\"formbutton\" > \n </form>");
             // out.println("<form<td><input type=\"submit\" name=\"fetchdata\" value=\"Delete\" action=\"Delete\" /></td>");
            out.println("</td>");
            out.println("<td><form action=\"UpdateProduct\" method=\"get\">");
            out.println("<input type=\"hidden\" name=\"ProductId\" value="+ arr.get(i).getId()+" > \n");
            //out.println("<input type=\"hidden\" name=\"orderId\" value="+ arr.get(i).getCount()+ ">\n");
            out.println("<input type=\"submit\" value=\"Update\" class=\"formbutton\" > \n </form>");
            // out.println("<form<td><input type=\"submit\" name=\"fetchdata\" value=\"Delete\" action=\"Delete\" /></td>");
            out.println("</td>");
          
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
        out.print("\n</ul>");
        out.print("</article>\n</section>\n<aside class=\"sidebar\">\n<br><br>\n<ul>\n<li>\n<h4>Categories</h4>\n<ul>\n");
        out.print("<li><a href=\"smartwatch\">Smart Watches</a></li>\n<li><a href=\"speakers\">Speakers</a></li>\n");
        out.print("<li><a href=\"headphones\">HeadPhones</a></li>\n<li><a href=\"phones\">Phones</a></li>\n");
        out.print("<li><a href=\"laptops\">Laptops</a></li>\n<li><a href=\"externalstorage\">External Storage</a></li>\n");
        out.print("<li><a href=\"TrendingServlet\">Trending</a></li>\n");
        out.print("</ul>\n</li>\n<li>\n<h4>About us</h4>\n<ul>\n<li class=\"text\">\n<p>Example website for online retail of smart portables.");
        out.print("<a href=\"contact.html\" class=\"readmore\">Read More &raquo;</a></p>\n</li>\n</ul>\n</li>\n</ul>\n</aside>");
        out.print("\n<div class=\"clear\"></div>\n</div>\n<footer>\n<div class=\"footer-bottom\">\n<p>&copy; CSJ 2017. Online Retailer "
        		+ "smart portables by Tanmaya Bhatt</p>\n</div>\n</footer>\n</div>\n</body>\n</html>");

	}
	

}
