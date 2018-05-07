import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet implements Serializable {

	LoginBean loginBean = new LoginBean();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");


		loginBean.setUserName(username);
		loginBean.setPassWord(password);
		loginBean.setRole(role);


	}
   
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processRequest(request,response);

		HashMap<String,LoginBean> hlb = new HashMap<String,LoginBean>();
		ServletContext sc = request.getServletContext();
		/*String fileName= "/Library/tomcat/webapps/csj/UserDetails.txt";

		try{

			FileInputStream fileInputStream     = new FileInputStream(new File(fileName));
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

			hmb = (HashMap)objectInputStream.readObject();
			}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("File not read !!!!");
		}*/

		PrintWriter out = response.getWriter();
		//System.out.println(loginBean.getUserName()+"1  "+ hmb.get(loginBean.getUserName()).getRole()+" 2"+hmb.get(loginBean.getUserName()).getPassWord().equals(loginBean.getPassWord()));
        
        MySQLDataStoreUtilities dataStoreUtilities = new MySQLDataStoreUtilities();
        
        int userExists = dataStoreUtilities.userExists(loginBean.getUserName());

		//if(hmb.containsKey(loginBean.getUserName()) && hmb.get(loginBean.getUserName()).getRole().equals(loginBean.getRole())  && hmb.get(loginBean.getUserName()).getPassWord().equals(loginBean.getPassWord()))
        XMLParser xmlParser = new XMLParser("/Library/tomcat/webapps/csj/WEB-INF/ProductCatalog.xml");
        HashMap<String, SmartWatches> smartWatchesMap = xmlParser.getSmartWatchesMap();
        HashMap<String, Speakers> speakersMap = xmlParser.getSpeakersMap();
        HashMap<String, Laptops> laptopsMap = xmlParser.getLaptopsMap();
        HashMap<String, HeadPhones> headPhonesMap = xmlParser.getHeadPhonesMap();
        HashMap<String, Phones> phonesMap = xmlParser.getPhonesMap();
        HashMap<String, ExternalStorage> externalStorageMap = xmlParser.getExternalStorageMap();
         HashMap<String, Accessories> accessoryMap = xmlParser.getAccessoriesMap();
        try{
           
            for (String key : externalStorageMap.keySet())
            {  ExternalStorage  externalstorage= externalStorageMap.get(key);
                dataStoreUtilities.addProducts(externalstorage.getProductName(),externalstorage.getCompanyName(),externalstorage.getProductId(),externalstorage.getPrice(),externalstorage.getSale(),externalstorage.getRebate(),externalstorage.getItem());
            }
        for (String key : smartWatchesMap.keySet())
         {   SmartWatches smartWatch = smartWatchesMap.get(key);
             dataStoreUtilities.addProducts(smartWatch.getProductName(),smartWatch.getCompanyName(),smartWatch.getProductId(),smartWatch.getPrice(),smartWatch.getSale(),smartWatch.getRebate(),smartWatch.getItem());
         }
            for (String key : speakersMap.keySet())
            {   Speakers  speaker= speakersMap.get(key);
                dataStoreUtilities.addProducts(speaker.getProductName(),speaker.getCompanyName(),speaker.getProductId(),speaker.getPrice(),speaker.getSale(),speaker.getRebate(),speaker.getItem());
            }
            
            for (String key : laptopsMap.keySet())
            {   Laptops  laptop= laptopsMap.get(key);
                dataStoreUtilities.addProducts(laptop.getProductName(),laptop.getCompanyName(),laptop.getProductId(),laptop.getPrice(),laptop.getSale(),laptop.getRebate(),laptop.getItem());
            }
            
            for (String key : headPhonesMap.keySet())
            {   HeadPhones  headphone= headPhonesMap.get(key);
                dataStoreUtilities.addProducts(headphone.getProductName(),headphone.getCompanyName(),headphone.getProductId(),headphone.getPrice(),headphone.getSale(),headphone.getRebate(),headphone.getItem());
            }
            
            for (String key : phonesMap.keySet())
            {  Phones  phone= phonesMap.get(key);
                dataStoreUtilities.addProducts(phone.getProductName(),phone.getCompanyName(),phone.getProductId(),phone.getPrice(),phone.getSale(),phone.getRebate(),phone.getItem());
            }
            

        }
        catch(Exception e)
        {
          System.out.println(e);
        }
        try
        {
            for (String key : accessoryMap.keySet())
            {  Accessories acc = accessoryMap.get(key);
            //dataStoreUtilities.addAccessory(acc.getProductName(),acc.getCompanyName(),acc.getProductId(),acc.getPrice(),acc.getParentProductId());
                //out.println("Hello");
                //out.println(acc.getProductName() + " "+ acc.getCompanyName()+ " "+ acc.getProductId()+" "+acc.getPrice()+" "+acc.getParentProductId());
                
            }
            
        }
        catch(Exception e)
        {
            out.println(e);
        }
        
       //for (String key : externalStorageMap.keySet())
        //{
        //out.println(externalStorageMap.get(key)+" Key="+key);
        //}
        if(userExists ==1)
		{
            hlb  = (HashMap)dataStoreUtilities.getLoginDetails(loginBean.getUserName());
            HttpSession session = request.getSession();
            
            //out.println(id);
			//response.sendRedirect("customer.html");
            
            //if(loginBean.getRole().equalsIgnoreCase("Customer") )
            if(hlb.get(loginBean.getUserName()).getRole().equals(loginBean.getRole())  && hlb.get(loginBean.getUserName()).getPassWord().equals(loginBean.getPassWord()))
            {
                session.setAttribute("username",loginBean.getUserName());
                String id = session.getAttribute("username").toString();
          if(hlb.get(loginBean.getUserName()).getRole().equals("customer"))
          {
            out.println("<!doctype html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
            out.println("<title>Smart Portables</title>");
            out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
               out.println("<script type=\"text/javascript\" src=\"search.js\"></script>");
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
                        out.println("<br><br><br>");
              out.println("<div style=\"text-align: right;\">");
              out.println("<body onload=\"init()\">");
                        out.println("<script type=\"text/javascript\"");   out.println("src=\"search.js\"></script>");
              
                        out.println("<div name=\"autofillform\">");
                        out.println("<input type=\"text\" name=\"searchId\" value=\"\" class=\"input\" id=\"searchId\"");
                        out.println("onkeyup=\"doCompletion()\" placeholder=\"search here..\" style=\"padding: 5px; font-size: 16px;\" />");
                        out.println("<div id=\"auto-row\">");
                        out.println("<table id=\"complete-table\" class=\"gridtable\" style=\"position: absolute; width: 315px;\"></table>");
                        out.println("</div> </div>");
                        out.println("</body>");
                        out.println("</div>");
                        out.println("</div>");
                        out.println(" </nav>");

                        out.println("<div id=\"body\" class=\"width\">");

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
            else if(hlb.get(loginBean.getUserName()).getRole().equals("salesman"))
                    {
                
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
                        out.println("<h1>Hello"+ loginBean.getUserName() +" Add users or track orders.<h1>");
                        out.println("</article>");
                out.println("<fieldset>");
                out.println("<legend>Registration Details</legend>");
                        out.println("<form action=\"/csj/SalesmanServlet\" method=\"get\">");
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
        
        else if(hlb.get(loginBean.getUserName()).getRole().equals("storemanager"))
        {
            
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
            out.println("<li><a href=\"ProductsManager\">Products</a></li>");
            out.println("<li><a href=\"#\">TrackOrder</a></li>");
            out.println("<li><a href=\"InventoryServlet\">Inventory</a></li>");
            out.println("<li><a href=\"SalesReportServlet\">SalesReport</a></li>");
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
            out.println("<form action=\"AddProductServlet\" method=\"get\">");
            out.println("<p><label for=\"Type\">Type:</label>");
            out.println("<input name=\"Type\" id=\"Type\" value=\"\" type=\"text\" /></p>");
            out.println("<p><label for=\"ProductId\">ProductId:</label>");
            out.println("<input name=\"ProductId\" id=\"ProductId\" value=\"\" type=\"text\" /></p>");
            out.println("<p><label for=\"RetailerName\">RetailerName:</label>");
            out.println("<input name=\"RetailerName\" id=\"RetailerName\" value=\"\" type=\"text\" /></p>");
            
            out.println("<p><label for=\"ProductName\">ProductName:</label>");
            out.println("<input name=\"ProductName\" id=\"ProductName\" value=\"\" type=\"text\" /></p>");
            out.println("<p><label for=\"Condition\">Condition:</label>");
            out.println("<input name=\"Condition\" id=\"Condition\" value=\"\" type=\"text\" /></p>");
            out.println("<p><label for=\"Price\">Price:</label>");
            out.println("<input name=\"Price\" id=\"Price\" value=\"\" type=\"text\" /></p>");
            out.println("<p><label for=\"Sale\">Sale:</label>");
            out.println("<input name=\"Sale\" id=\"Sale\" value=\"\" type=\"text\" /></p>");
            out.println("<p><label for=\"Rebate\">Rebate</label>");
            out.println("<input name=\"Rebate\" id=\"Rebate\" value=\"\" type=\"text\" /></p>");
            out.println("<p><label for=\"ImageName\">ImageName:</label>");
            out.println("<input name=\"ImageName\" id=\"ImageName\" value=\"\" type=\"text\" /></p>");
           out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Add\" type=\"submit\" /></p>");
            //out.println("<p><button><a href=\"AddProductServlet\">Add</a></button>");
           // out.println("<button><a href=\"DeleteProductServlet\">Delete</a></button>");
            //out.println("<button><a href=\"UpdateProductServlet\">Update</a></button></p>");
             out.println("</form>");
            //<input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Delete\" type=\"submit\" />  <input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update\" type=\"submit\" /> </p>");
            
            
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
        
		else
		{
            response.sendRedirect("login.html");


		}

}
}
    
}
