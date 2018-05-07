
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

@WebServlet("/InventoryServlet")
public class InventoryServlet extends HttpServlet{
private String error_msg;

ArrayList<InventoryBean> arr = new ArrayList<InventoryBean>();
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
    out.println("<li><a href=\"InventoryChart\">InventoryChart</a></li>");
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
    out.println("List of all products and items");
    out.println("<table id=\"t01\">");
    out.println("<tr>");
    out.println("<th>Product</th>");
    out.println("<th>Price</th>");
    out.println("<th>Items</th>");
    out.println("</tr>");
     ArrayList<InventoryBean> ib_arr = new ArrayList<InventoryBean>();
   ib_arr = sql.selectInventory();
    /*try{
        Connection connection=null;
        Statement statement=null;
        PreparedStatement preparedStatement=null;
        ResultSet rs = null;
        PreparedStatement st= null;

        connection= sql.createConnection();
       String query = "select product_name, price, item from productdetails";
       st = connection.prepareStatement(query);
        rs = st.executeQuery();
         InventoryBean ib = new InventoryBean();
       
       // PrintWriter out = response.getWriter();
        while(rs.next()){
           ib = new InventoryBean();
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
            //out.println(ib.getName());
           //out.println(ib.getPrice());
           // out.println(ib.getItem());
            
        }
    }
    catch(Exception e){
        out.println(e);
    }*/
    for(InventoryBean ib:ib_arr){
        //BasicDBObject bobj = (BasicDBObject) result;
        //out.println(bobj.getString("productname"));
        out.println("<tr>");
        out.println("<td>"+ib.getName()+"</td>");
         out.println("<td>"+ib.getPrice()+"</td>");
        out.println("<td>"+ib.getItem()+"</td>");
        out.println("</tr>");
        
    }
    out.println("</table><br><br><br>");
    out.println("All products on sale:");
    out.println("<table id=\"t02\">");
    out.println("<tr>");
    out.println("<th>Prod</th>");
    //out.println("<th>Price</th>");
    out.println("</tr>");
     arrs=sql.selectSale();
    for(String ib:arrs){
        //out.println(bobj.getString("zipcode"));
        out.println("<tr>");
        out.println("<td>"+ib+"</td>");
        //out.println("<td>"+ib.getPrice()+"</td>");
        out.println("</tr>");
      
    }
   
    out.println("</table><br><br><br>");
    
    out.println("All products that have rebate:");
    out.println("<table id=\"t03\">");
    out.println("<tr>");
    out.println("<th>Prod</th>");
   // out.println("<th>Price</th>");
    out.println("</tr>");
    arrs=sql.selectRebate();
    
    for(String ib:arrs){
        //BasicDBObject bobj = (BasicDBObject) result;
        //out.println(bobj.getString("zipcode"));
        out.println("<tr>");
        out.println("<td>"+ib+"</td>");
       // out.println("<td>"+bobj.getString("productname")+"</td>");
        out.println("</tr>");
        
    }
    out.println("</table>");
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
