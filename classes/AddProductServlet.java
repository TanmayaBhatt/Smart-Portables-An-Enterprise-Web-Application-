

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class AddProductServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String productId = request.getParameter("ProductId");
		String productName = request.getParameter("ProductName");
		String retailer = request.getParameter("RetailerName");
		String condition = request.getParameter("condition");
		String price  = request.getParameter("Price");
		String type = request.getParameter("type");
		String accessories = request.getParameter("accessories");
        String sale = request.getParameter("Sale");
        String rebate = request.getParameter("Rebate");
        String image = request.getParameter("Image");
        double pr= Double.parseDouble(price);
        int x=1;
        try{
            MySQLDataStoreUtilities dataStoreUtilities = new MySQLDataStoreUtilities();
        dataStoreUtilities.addProducts(productName,retailer,productId,pr,sale,rebate,x);
            String path = "/Library/tomcat/webapps/csj/";
            XMLParser xmlParser = new XMLParser(path+"WEB-INF/ProductCatalog.xml");
            HashMap<String, ExternalStorage> externalStorageMap = xmlParser.getExternalStorageMap();
            HashMap<String, SmartWatches> smartWatchesMap = xmlParser.getSmartWatchesMap();
            HashMap<String, Speakers> speakersMap = xmlParser.getSpeakersMap();
            HashMap<String, Laptops> laptopsMap = xmlParser.getLaptopsMap();
            HashMap<String, HeadPhones> headPhonesMap = xmlParser.getHeadPhonesMap();
            HashMap<String, Phones> phonesMap = xmlParser.getPhonesMap();
           
            
            for (String key : externalStorageMap.keySet()) {
                ExternalStorage externalStorage = externalStorageMap.get(key);
                if(key.equals(productId))
                {                                                       externalStorage.setImageName(image);
                    externalStorage.setProductName(productName);
                    externalStorage.setCompanyName(retailer);
                    externalStorage.setPrice(pr);
                    externalStorage.setSale(sale);
                    externalStorageMap.put(key,externalStorage);
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            } for (String key : phonesMap.keySet()) {
                Phones phones = phonesMap.get(key);
                if(key.equals(productId))
                {                                                       phones.setImageName(image);
                    phones.setProductName(productName);
                    phones.setCompanyName(retailer);
                    phones.setPrice(pr);
                    phones.setSale(sale);
                    phonesMap.put(key,phones);
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            } for (String key : laptopsMap.keySet()) {
                Laptops laptops = laptopsMap.get(key);
                if(key.equals(productId))
                {                                                       laptops.setImageName(image);
                    laptops.setProductName(productName);
                    laptops.setCompanyName(retailer);
                    laptops.setPrice(pr);
                    laptops.setSale(sale);
                    laptopsMap.put(key,laptops);
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            } for (String key : speakersMap.keySet()) {
                Speakers speakers = speakersMap.get(key);
                if(key.equals(productId))
                {                                                       speakers.setImageName(image);
                    speakers.setProductName(productName);
                    speakers.setCompanyName(retailer);
                    speakers.setPrice(pr);
                    speakers.setSale(sale);
                    speakersMap.put(key,speakers);
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            } for (String key : headPhonesMap.keySet()) {
                HeadPhones headPhones = headPhonesMap.get(key);
                if(key.equals(productId))
                {                                                       headPhones.setImageName(image);
                    headPhones.setProductName(productName);
                    headPhones.setCompanyName(retailer);
                    headPhones.setPrice(pr);
                    headPhones.setSale(sale);
                    headPhonesMap.put(key,headPhones);
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            }
            for (String key : smartWatchesMap.keySet()) {
                SmartWatches smartWatches = smartWatchesMap.get(key);
                if(key.equals(productId))
                {                                                       smartWatches.setImageName(image);
                    smartWatches.setProductName(productName);
                    smartWatches.setCompanyName(retailer);
                    smartWatches.setPrice(pr);
                    smartWatches.setSale(sale);
                    smartWatchesMap.put(key,smartWatches);
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            }
            /*for (String key : smartWatchesMap.keySet()) {
                SmartWatches smartWatches = smartWatchesMap.get(key);
                if(key.equals(searchId))
                {                                                       image=smartWatches.getImageName();
                    productName=smartWatches.getProductName();
                    companyName=smartWatches.getCompanyName();
                    price=smartWatches.getPrice();
                    key1=key;
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            }
            
            for (String key : phonesMap.keySet()) {
                Phones phones = phonesMap.get(key);
                if(key.equals(searchId))
                {                                                       image=phones.getImageName();
                    productName=phones.getProductName();
                    companyName=phones.getCompanyName();
                    price=phones.getPrice();
                    key1=key;
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            }
            for (String key : headPhonesMap.keySet()) {
                HeadPhones headPhones = headPhonesMap.get(key);
                if(key.equals(searchId))
                {                                                       image=headPhones.getImageName();
                    productName=headPhones.getProductName();
                    companyName=headPhones.getCompanyName();
                    price=headPhones.getPrice();
                    key1=key;
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            }
            for (String key : laptopsMap.keySet()) {
                Laptops laptops = laptopsMap.get(key);
                if(key.equals(searchId))
                {                                                       image=laptops.getImageName();
                    productName=laptops.getProductName();
                    companyName=laptops.getCompanyName();
                    price=laptops.getPrice();
                    key1=key;
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            }
            for (String key : speakersMap.keySet()) {
                Speakers speakers = speakersMap.get(key);
                if(key.equals(searchId))
                {                                                       image=speakers.getImageName();
                    productName=speakers.getProductName();
                    companyName=speakers.getCompanyName();
                    price=speakers.getPrice();
                    key1=key;
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }*/
            }
        
        catch(Exception e)
        {
            System.out.println(e);
        }
       
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

}
