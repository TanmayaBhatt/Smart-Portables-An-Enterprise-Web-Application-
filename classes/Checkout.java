import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.text.DateFormat;
import java.util.*;

public class Checkout extends HttpServlet implements Serializable {
    
    //static ArrayList<Order> arr = Checkout.arr;
   // Random rand = new Random();
   //int i = rand.nextInt(100) + 1;
    HashMap<String,Cart> hm=Cart.cartMap;
    
   //@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        HttpSession session = request.getSession(true);
        String id = session.getAttribute("username").toString();
        
        String name= request.getParameter("name");
        String card = request.getParameter("card");
        String address = request.getParameter("address");

        
        PrintWriter out = response.getWriter();
       // out.println(name);
        //out.println(card);
        //out.println(address);
        //out.println(id);
        MySQLDataStoreUtilities db=new MySQLDataStoreUtilities();
        int i=0;
        try{
             i =db.addOrderDetails(hm,card,address,id);
            
        }
        catch(SQLException e)
        {
            out.println(e);
        }
        catch(ClassNotFoundException e)
        {
            out.println(e);
        }
        catch(InstantiationException e)
        {
            out.println(e);
        }
        catch(IllegalAccessException e)
        {
            out.println(e);
        }
       // int res=db.addOrderDetails(hm);
        for (String key : Cart.cartMap.keySet()) {
            Cart c = Cart.cartMap.get(key);
            //out.println(c.getProductName());
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 5);
        dt = c.getTime();
        String time = sdf.format(dt);
        //out.print("Hello");
       /*ServletContext sc = request.getServletContext();
        String filename = "/Library/Tomcat/webapps/csj/OrderDetails.txt";
        response.setContentType("text/html");
        
        HttpSession session = request.getSession(true);
        String id = session.getAttribute("username").toString();
        OrderBean ob=new OrderBean();
        ob.setOrderId(i);
        ob.setUserName(id);
        ArrayList<Cart> a = new ArrayList<Cart>();
        for(String key:hm.keySet())
            a.add(hm.get(key));
        ob.setList(a);
        HashMap<Integer, OrderBean> hob = new HashMap<Integer, OrderBean>();
        //hob.put(ob.getOrderId,ob);
        //out.println("Hello2");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 5);
        dt = c.getTime();
        String time = sdf.format(dt);
            
            try
        {   FileInputStream fileInputStream     = new FileInputStream(new File(filename));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        
            //out.println("reached here");
            hob = (HashMap)objectInputStream.readObject();
        }
        catch (Exception e)
        {
            out.println(e);
        }
            hob.put(i, ob);
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(hob);
            os.flush();
            os.close();
            fos.close();
           // for(String key: hm.keySet()){
                //out.print(hm.get(key).getUsername());
            //out.println("Reached here");
       */ out.println("<!doctype html>");
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
        out.println("<li><a href=\"TrackOrderCust\">TrackOrder</a></li>");
        out.println("<li><a href=\"#\">Contact</a></li>");
        out.println("<li style=\"float:right;\"><a href=\"#\">Cart </a></li>");
        out.println("<li class=\"end\" style=\"float:right;\"><a href=\"login.html\">SignOut</a></li>");
        out.println("</ul>");
        out.println("</div>");
        out.println(" </nav>");
        
        out.println("<div id=\"body\" class=\"width\">");
        out.println("<section id=\"content\">");
        out.println("<article>");
        out.println("<h2>"+ "Order Success" +"</h2>");
        out.println("<p>"+"Order Placed with id:"+i +"</p>");
        out.println("<p> Date: "+ time+ "</p>");
        out.println("</article>");
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

       
       
        
    }
}
    

