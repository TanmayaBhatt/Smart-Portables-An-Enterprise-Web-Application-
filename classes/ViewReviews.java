

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewReviews extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
       
        String productName = request.getParameter("productName");
        PrintWriter out = response.getWriter();
        
        
        
     
        
        out.print("<!doctype html>");
        out.print("<html>");
        out.print("<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        out.print("<title>Smart Portables</title>");
        out.print("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
        out.print("<meta name=\"viewport\" content=\"width=device-width, minimum-scale=1.0, maximum-scale=1.0\" />");
        out.print("</head>\n<body>\n<div id=\"container\">\n<header>\n<div class=\"width\">\n<h1><a href=\"/\">CS<span>J</span></a></h1>");
        out.print("</div>\n</header>\n<nav>\n<div class=\"width\">\n<ul>\n<li class=\"start\"><a href=\"index.html\">Home</a></li>");
        out.print("<li class=\"selected\"><a href=\"products\">Products</a></li><li><a href=\"track.html\">TrackOrder</a></li>");
        out.print("<li><a href=\"contact.html\">Contact</a></li>\n<li style=\"float:right;\"><a href=\"cart\">Cart ("+Cart.getCartCount()+")</a></li>");
        
        out.print("<li class=\"end\" style=\"float:right;\"><a href=\"Logout\">SignOut</a></li>\n</ul>\n</div>\n</nav>");
        out.print("<div id=\"body\" class=\"width\">\n<section id=\"content\">\n<article>\n<br><br>\n<center>\n<h2>Products</h2>");
        out.print("</center>\n<br>\n<h4></h4>\n<ul style=\"list-style-type: none;\">\n");
       
        HashMap<String, ArrayList<ReviewBean>> hrv = new HashMap<String, ArrayList<ReviewBean>>();
        MongoDBDataStoreUtilities dataStoreUtilities = new MongoDBDataStoreUtilities();
        
        hrv = dataStoreUtilities.viewReviews(productName);
        
        for( Map.Entry<String, ArrayList<ReviewBean>> map : hrv.entrySet())
        {
            for( ReviewBean reviewBean : map.getValue())
            {
                out.println("  Product Model Name  "+reviewBean.getProductName()+"<br/>");
                out.println("  Product Category  "+reviewBean.getProductCategory()+"<br/>");
                out.println("  Product Price  "+reviewBean.getProductPrice()+"<br/>");
                out.println("  Retailer Name  "+reviewBean.getRetailerName()+"<br/>");
                out.println("  Retailer City  "+reviewBean.getRetailerCity()+"<br/>");
                out.println("  Retailer State  "+reviewBean.getRetailerState()+"<br/>");
                out.println("  Retailer Zip   "+reviewBean.getRetailerZip()+"<br/>");
                out.println("  Product On Sale  "+reviewBean.getProductOnSale()+"<br/>");
                out.println("  Manufacturer Name  "+reviewBean.getManufacturerName()+"<br/>");
                out.println("  Manufacturer Rebate  "+reviewBean.getRebateAvailable()+"<br/>");
                out.println("  User ID  "+reviewBean.getUserName()+"<br/>");
                out.println("  User Age  "+reviewBean.getUserAge()+"<br/>");
                out.println("  User Gender  "+reviewBean.getUserGender()+"<br/>");
                out.println("  User Occupation  "+reviewBean.getUserOccupation()+"<br/>");
                out.println("  Review Rating  "+reviewBean.getReviewRating()+"<br/>");
                out.println("  Review Date  "+reviewBean.getReviewDate()+"<br/>");
                out.println("  Review Text "+reviewBean.getReviewText()+"<br/>");
                out.println(""+"<br/>");
                
            }
        }
        
        
        out.print("\n</ul>");
        out.print("</article>\n</section>\n<aside class=\"sidebar\">\n<br><br>\n<ul>\n<li>\n<h4>Categories</h4>\n<ul>\n");
        out.print("<li><a href=\"smartwatch\">Smart Watches</a></li>\n<li><a href=\"speakers\">Speakers</a></li>\n");
        out.print("<li><a href=\"headphones\">HeadPhones</a></li>\n<li><a href=\"phones\">Phones</a></li>\n");
        out.print("<li><a href=\"laptops\">Laptops</a></li>\n<li><a href=\"externalstorage\">External Storage</a></li>\n");
        out.print("</ul>\n</li>\n<li>\n<h4>About us</h4>\n<ul>\n<li class=\"text\">\n<p>Example website for online retail of smart portables.");
        out.print("<a href=\"contact.html\" class=\"readmore\">Read More &raquo;</a></p>\n</li>\n</ul>\n</li>\n</ul>\n</aside>");
        out.print("\n<div class=\"clear\"></div>\n</div>\n<footer>\n<div class=\"footer-bottom\">\n<p>&copy; CSJ 2017. Online Retailer "
                  + "smart portables by Tanmaya Bhatt</p>\n</div>\n</footer>\n</div>\n</body>\n</html>");
        
        
        
        
    }
    
}

