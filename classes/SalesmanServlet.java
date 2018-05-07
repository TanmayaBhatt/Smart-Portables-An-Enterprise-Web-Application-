import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SalesmanServlet extends HttpServlet implements Serializable  {
    PrintWriter out;
    UserBean userBean = new UserBean();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        out = response.getWriter();
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        //String confirmPassword = request.getParameter("confirmpassword");
        String role = request.getParameter("role");
        
        
        userBean.setUserName(userName);
        userBean.setPassWord(passWord);
        userBean.setRole(role);
        
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        processRequest(request,response);
        
        HashMap<String,UserBean> hmb = new HashMap<String,UserBean>();
        ServletContext sc = request.getServletContext();
        String fileName= "/Library/tomcat/webapps/csj/UserDetails.txt";
        
        
        
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
        
        
        if(hmb.containsKey(userBean.getUserName()))
        {
            
            out.println("The UserName "+userBean.getUserName()+" already exists");
            response.sendRedirect("login.html");
            
        }
        else
        {
            hmb.put(userBean.getUserName(), userBean);
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(hmb);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
            //out.println("The UserName "+userBean.getUserName()+" has been added");
           // response.sendRedirect("login.html");
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
            out.println("<li class=\"start selected\"><a href=\"Salesman\">Home</a></li>");
            out.println("<li><a href=\"products\">Products</a></li>");
            out.println("<li><a href=\"TrackOrder\">TrackOrder</a></li>");
            out.println("<li><a href=\"#\">Contact</a></li>");
            out.println("<li style=\"float:right;\"><a href=\"#\">Cart </a></li>");
            out.println("<li class=\"end\" style=\"float:right;\"><a href=\"login.html\">SignOut</a></li>");
            out.println("</ul>");
            out.println("</div>");
            out.println(" </nav>");
            
            out.println("<div id=\"body\" class=\"width\">");
            out.println("<section id=\"content\">");
            out.println("<fieldset>");
            out.println("<legend>Registration Details</legend>");
            out.println("<form action=\"/UserRegistration\" method=\"get\">");
            out.println("<p><label for=\"role\">Role:</label>");
            out.println("<input name=\"role\" id=\"role\" value=\"\" type=\"text\" /></p>");
            out.println("<p><label for=\"username\">UserName:</label>");
            out.println("<input name=\"username\" id=\"username\" value=\"\" type=\"text\" /></p>");
            out.println("<p><label for=\"password\">Password:</label>");
            out.println("<input name=\"password\" id=\"password\" value=\"\" type=\"Password\" /></p>");
            out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"AddUser\" type=\"submit\" /></p>");
            
            
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
        }
        
        
    }
    
    
    
}

