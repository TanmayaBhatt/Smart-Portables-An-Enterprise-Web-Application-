
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteOrder extends HttpServlet implements Serializable {
    int orderId;
    OrderBean ob = new OrderBean();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
       // String username = request.getParameter("username");
         orderId = Integer.parseInt(request.getParameter("orderId"));
       
        
        
       // ob.setUserName(username);
        ob.setOrderId(orderId);
        
        
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // OrderBean ob1= new OrderBean();
        processRequest(request,response);
        PrintWriter out = response.getWriter();
        //ArrayList<Cart> arr= new ArrayList<Cart>();
        // HashMap<String, Cart> hm =
        MySQLDataStoreUtilities ob= new MySQLDataStoreUtilities();
        HttpSession session = request.getSession(true);
        String id = session.getAttribute("username").toString();
       // out.println(orderId);
      int res= ob.cancelOrder(orderId);
        
        /*HashMap<Integer,OrderBean> hmb = new HashMap<Integer,OrderBean>();
        //ServletContext sc = request.getServletContext();
        String fileName= "/Library/tomcat/webapps/csj/OrderDetails.txt";
        
        try{
            
            FileInputStream fileInputStream     = new FileInputStream(new File(fileName));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            
            hmb = (HashMap)objectInputStream.readObject();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("File not read !!!!");
        }
        PrintWriter out = response.getWriter();
       /* Set set = hmb.entrySet();
        Iterator iterator = set.iterator();*/
       // int z=ob.getOrderId();
        
        
        //out.println(ob.getUserName());
        //out.println(ob.getOrderId());
       /* while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            out.println("key: "+ mentry.getKey() + " & Value: ");
            OrderBean ob=(OrderBean)mentry.getValue();
            out.println(ob.getUserName());
            out.println(ob.getOrderId());
            }*/
        //hmb.remove(z);
        
       /* FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(hmb);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();*/
        
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
        out.println("<h2>"+ "Order Cancelled" +"</h2>");
        out.println("<p>"+"You may now track your order to confirm the cancellation "+"</p>");
        //out.println("<p> Date: "+ time+ "</p>");
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
