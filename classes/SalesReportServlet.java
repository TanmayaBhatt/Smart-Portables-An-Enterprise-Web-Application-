
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;
import javax.servlet.*;
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

@WebServlet("/SalesReportServlet")
public class SalesReportServlet extends HttpServlet{
    private String error_msg;
    
    ArrayList<SalesBean> arr = new ArrayList<SalesBean>();
    ArrayList<String> arrs= new ArrayList<String>();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        MySQLDataStoreUtilities sql= new MySQLDataStoreUtilities();
        
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
        out.println("<li class=\"start selected\"><a href=\"customer.html\">Home</a></li>");
        out.println("<li><a href=\"products\">Products</a></li>");
        out.println("<li><a href=\"TrackOrder\">TrackOrder</a></li>");
        out.println("<li><a href=\"SalesChart\">SalesChart</a></li>");
        out.println("<li><a href=\"#\">Contact</a></li>");
        out.println("<li style=\"float:right;\"><a href=\"#\">Cart </a></li>");
        out.println("<li class=\"end\" style=\"float:right;\"><a href=\"login.html\">SignOut</a></li>");
        out.println("</ul>");
        out.println("</div>");
        out.println(" </nav>");
        
        out.println("<div id=\"body\" class=\"width\">");
        out.println("<section id=\"content\">");
        out.println(" <article>");
        out.println("<br>");
        out.println("<br>");
        out.println("<h1>Tables</h1>");
        out.println("</article>");
        out.println("<fieldset>");
        out.println("<h2>The tables are as follows:</h2><br>");
        out.println("List of all products sold, price, items sold and total sales");
        out.println("<table id=\"t01\">");
        out.println("<tr>");
        out.println("<th>Product</th>");
        out.println("<th>Price</th>");
        out.println("<th>Items</th>");
        out.println("<th>Sales</th>");
        out.println("</tr>");
        ArrayList<SalesBean> sb_arr = new ArrayList<SalesBean>();
         ArrayList<SalesBean> sb_arr2 = new ArrayList<SalesBean>();
        //ib_arr = sql.selectSales();
        /*try{
         Connection connection=null;
         Statement statement=null;
         PreparedStatement preparedStatement=null;
         ResultSet rs = null;
         PreparedStatement st= null;
         
         connection= sql.createConnection();
         //ArrayList<SalesBean> arr = new ArrayList<SalesBean>();
        
        // createConnection();
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
             out.println("name:"+sb.getName());
             out.println("price:"+sb.getPrice());
             out.println("sales:"+ sb.getSale());
         }
         }
         catch(Exception e){
             out.println(e);
         }*/
        sb_arr= sql.selectSales();
        for(SalesBean sb:sb_arr){
            //BasicDBObject bobj = (BasicDBObject) result;
            //out.println(bobj.getString("productname"));
            out.println("<tr>");
            out.println("<td>"+sb.getName()+"</td>");
            out.println("<td>"+sb.getPrice()+"</td>");
            out.println("<td>"+sb.getSold()+"</td>");
            out.println("<td>"+sb.getSale()+"</td>");
            out.println("</tr>");
            
        }
        out.println("</table><br><br><br>");
        out.println("Daily Transactions:");
        out.println("<table id=\"t02\">");
        out.println("<tr>");
        out.println("<th>Date</th>");
        out.println("<th>Sales</th>");
        //out.println("<th>Price</th>");
        out.println("</tr>");
        //sb_arr=sql.selectDateTransaction();
       /* try{
         Connection connection=null;
         Statement statement=null;
         PreparedStatement preparedStatement=null;
         ResultSet rs = null;
         PreparedStatement st= null;
         ArrayList<SalesBean> arr = new ArrayList<SalesBean>();
         connection= sql.createConnection();
         //ArrayList<SalesBean> arr = new ArrayList<SalesBean>();
         
         // createConnection();
            String query =" set sql_mode=' '";
            st = connection.prepareStatement(query);
            rs = st.executeQuery();
         query = "select date,orderprice, count(orderid) from customerorders group by date";
        st = connection.prepareStatement(query);
        rs = st.executeQuery();
         
         while(rs.next()){
         SalesBean sb = new SalesBean();
         String date = rs.getString("date");
         int items = Integer.parseInt(rs.getString("count(orderid)"));
        double price= Double.parseDouble(rs.getString("orderprice"));
         double sale= price * items;
         sb.setDate(date);
         sb.setOrderNo(items);
         sb.setSale(sale);
         sb_arr2.add(sb);
         }
         }
         catch(Exception e){
             out.println(e);
         }*/
        sb_arr2=sql.selectDateTransaction();
        for(SalesBean sb:sb_arr2){
            //out.println(bobj.getString("zipcode"));
            out.println("<tr>");
            out.println("<td>"+sb.getDate()+"</td>");
            out.println("<td>"+sb.getSale()+"</td>");
            //out.println("<td>"+ib.getPrice()+"</td>");
            out.println("</tr>");
            
        }
        
        out.println("</table><br><br><br>");
        
       
        out.println("</form>");
        
        out.println("</fieldset>");
        
        
        out.println("</section>");
        
        out.println("<aside class=\"sidebar\">");
        out.println("<br><br>");
        
        out.println("<ul>");
        out.println("<li>");
        out.println("<h4>Categories</h4>");
        out.println("<ul>");
        out.println("<li><a href=\"smartwatch\">Smart Watches</a></li>");
        out.println("<li><a href=\"speakers\">Speakers</a></li>");
        out.println("<li><a href=\"headphones\">HeadPhones</a></li>");
        out.println("<li><a href=\"phones\">Phones</a></li>");
        out.println("<li><a href=\"laptops\">Laptops</a></li>");
        out.println("<li><a href=\"externalstorage\">External Storage</a></li>");
        out.println(" </ul>");
        out.println(" </li>");
        
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
        
        out.println(" </aside>");
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
        /*out.println("<!doctype html>");
         out.println("<html>");
         out.println("<head>");
         out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
         out.println("<title>spigot - Free CSS Template by ZyPOP</title>");
         out.println("<link rel='stylesheet' href='styles.css' type='text/css' />");
         
         
         out.println("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
         out.println("<script type='text/javascript'>");
         out.println("google.charts.load('current', {packages: ['corechart', 'bar']});");
         out.println("google.charts.setOnLoadCallback(drawBasic);");
         out.println("function drawBasic() {");
         out.println("var data = google.visualization.arrayToDataTable([");
         out.println("['Product name', 'Quantity',],");
         
         for(InventoryBean ib:arr){
         String name = ib.getName();
         int quantity = ib.getItem();
         
         out.println("[' " +name+ " ', "+quantity+ "],");
         }
         
         
         
         // out.println("['New York City, NY', 8175000],");
         // out.println("['Los Angeles, CA', 3792000],");
         // out.println("['Chicago, IL', 2695000],");
         // out.println("['Houston, TX', 2099000],");
         // out.println("['Philadelphia, PA', 1526000]");
         out.println("]);");
         out.println("var options = {");
         out.println("title: 'Population of Largest U.S. Cities',");
         out.println("chartArea: {width: '50%'},");
         out.println("hAxis: {");
         out.println("title: 'Total number of products',");
         out.println("minValue: 0");
         out.println("},");
         out.println("vAxis: {");
         out.println("title: 'Product Name'");
         out.println("}");
         out.println("};");
         out.println("var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");
         out.println("chart.draw(data, options);");
         out.println("}");
         out.println("</script>");
         
         
         
         out.println("</head>");
         out.println("<body>");
         out.println("<div id='container'>");
         out.println("<header>");
         out.println("<h1><a href='/'>Smart<span>Portables</span></a></h1>");
         out.println("</header>");
         out.println("<nav>");
         out.println("<ul>");
         out.println("<li class='start selected'><a href='index.html'>Home</a></li>");
         
         out.println("<li><a href='#'>View Order</a></li>");
         out.println("<li><a href='Register'>Register</a></li>");
         out.println("<li><a href='Logout'>Logout</a></li>");
         out.println("<ul>");
         out.println("</nav>");
         out.println("<img class='header-image' src='images/image.jpg' alt='Buildings' />");
         
         out.println("<div id='body'>");
         out.println("<section id='content'>");
         
         
         out.println("<div id = 'chart_div'></div>");
         
         
         
         
         out.println("</section>");
         out.println("</div>");
         
         out.println("<aside class='sidebar'>");
         out.println("<ul>");
         
         out.println("<li>");
         out.println("<ul>");
         out.println("<li><a href='SmartWatchesList?maker=smartwatches'>Smart Watches</a></li>");
         out.println("</ul>");
         out.println("</li>");
         
         out.println("<li>");
         out.println("<ul>");
         out.println("<li><a href='SpeakersList?maker=speakers'>Speakers</a></li>");
         out.println("</ul>");
         out.println("</li>");
         
         out.println("<li>");
         out.println("<ul>");
         out.println("<li><a href='#'>Headphones</a></li>");
         out.println("</ul>");
         out.println("</li>");
         
         out.println("<li>");
         out.println("<ul>");
         out.println("<li><a href='#'>Phones</a></li>");
         out.println("</ul>");
         out.println("</li>");
         
         out.println("<li>");
         out.println("<ul>");
         out.println("<li><a href='#'>Laptops</a></li>");
         out.println("</ul>");
         out.println("</li>");
         
         out.println("<li>");
         out.println("<ul>");
         out.println("<li><a href='#'>External Storage</a></li>");
         out.println("</ul>");
         out.println("</li>");
         
         out.println("</ul>");
         out.println("</aside>");
         
         out.println("<div class='clear'></div>");
         out.println("</div>");
         
         out.println("<footer>");
         out.println("<div class='footer-content'>");
         out.println("<p>&copy; YourSite 2013. Free CSS Website Templates by ZyPOP</p>");
         out.println("</div>");
         out.println("</footer");
         
         out.println("</body>");
         out.println("</html>");
         */
    }
}

