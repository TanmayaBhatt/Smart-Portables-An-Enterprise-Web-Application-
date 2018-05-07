

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
public class DeleteProduct extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        ArrayList<ProductBean> arr= new ArrayList<ProductBean>();
        MySQLDataStoreUtilities ob= new MySQLDataStoreUtilities();
        PrintWriter out = response.getWriter();
		String productId = request.getParameter("ProductId");
		
        try{
            ob.deleteProduct(productId);
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
                {   externalStorageMap.remove(key);
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            } for (String key : phonesMap.keySet()) {
                Phones phones = phonesMap.get(key);
                if(key.equals(productId))
                {                                                       phonesMap.remove(key);
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            } for (String key : laptopsMap.keySet()) {
                Laptops laptops = laptopsMap.get(key);
                if(key.equals(productId))
                {                                                       laptopsMap.remove(key);
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            } for (String key : speakersMap.keySet()) {
                Speakers speakers = speakersMap.get(key);
                if(key.equals(productId))
                {                                                       speakersMap.remove(key);
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            } for (String key : headPhonesMap.keySet()) {
                HeadPhones headPhones = headPhonesMap.get(key);
                if(key.equals(productId))
                {
                    headPhonesMap.remove(key);
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            }
            for (String key : smartWatchesMap.keySet()) {
                SmartWatches smartWatches = smartWatchesMap.get(key);
                if(key.equals(productId))
                {                                                       smartWatchesMap.remove(key);
                    //smartWatch.getCompanyName()
                    //smartWatch.getPrice()
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
       
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
