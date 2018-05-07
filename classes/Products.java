import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Products extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String id = session.getAttribute("username").toString();

        XMLParser xmlParser = new XMLParser("/Library/tomcat/webapps/csj/WEB-INF/ProductCatalog.xml");
        HashMap<String, SmartWatches> smartWatchesMap = xmlParser.getSmartWatchesMap();
        HashMap<String, Speakers> speakersMap = xmlParser.getSpeakersMap();
        HashMap<String, Laptops> laptopsMap = xmlParser.getLaptopsMap();
        HashMap<String, HeadPhones> headPhonesMap = xmlParser.getHeadPhonesMap();
        HashMap<String, Phones> phonesMap = xmlParser.getPhonesMap();
        HashMap<String, ExternalStorage> externalStorageMap = xmlParser.getExternalStorageMap();
        out.print("<!doctype html>");
        out.print("<html>");
        out.print("<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        out.print("<title>Smart Portables</title>");
        out.print("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
        out.print("<meta name=\"viewport\" content=\"width=device-width, minimum-scale=1.0, maximum-scale=1.0\" />");
        out.print("</head>\n<body>\n<div id=\"container\">\n<header>\n<div class=\"width\">\n<h1><a href=\"/\">CS<span>J</span></a></h1>");
        out.print("</div>\n</header>\n<nav>\n<div class=\"width\">\n<ul>\n<li class=\"start\"><a href=\"customer.html\">Home</a></li>");
        out.print("<li class=\"selected\"><a href=\"products\">Products</a></li><li><a href=\"track.html\">TrackOrder</a></li>");
        out.print("<li><a href=\"contact.html\">Contact</a></li>\n<li style=\"float:right;\"><a href=\"cart\">Cart ("+Cart.getCartCount()+")</a></li>");
        if(id.equals("null"))
            out.print("<li class=\"end\" style=\"float:right;\"><a href=\"login.html\">SignIn</a></li>\n</ul>\n</div>\n</nav>");
        else
            out.print("<li class=\"end\" style=\"float:right;\"><a href=\"Logout\">SignOut</a></li>\n</ul>\n</div>\n</nav>");
        out.print("<div id=\"body\" class=\"width\">\n<section id=\"content\">\n<article>\n<br><br>\n<center>\n<h2>Products</h2>");
        out.print("</center>\n<br>\n<h4>Smart Watches</h4>\n<div>\n");
        int count = 0;
        for (String key : smartWatchesMap.keySet()) {
        	count++;
        	SmartWatches smartWatch = smartWatchesMap.get(key);
        	if(count > 2){
        		count = 0;
        		break;
        	}
        	out.print("<div style=\"display: inline-block; width: 40%\">\n<h5 style=\"font-size: 17px; margin-bottom: -10px;\">"
        			+ smartWatch.getProductName()+"</h5>\n<p style=\"margin-bottom: 5px;padding-left: 10px; font-size: 10px;\"> by "
        					+smartWatch.getCompanyName()+"</p>\n<p style=\"margin-bottom: 0px;\">$ "+smartWatch.getPrice()+"</p>\n</div>\n");
		}
        out.print("<div style=\"display: inline-block; float: bottom;\">\n<a href=\"smartwatch\">SeeMore</a>\n</div>\n<div>\n");
        out.print("<br>\n<h4>Speakers</h4>\n<div>\n");
        for (String key : speakersMap.keySet()) {
        	count++;
        	Speakers speakers = speakersMap.get(key);
        	if(count > 2){
        		count = 0;
        		break;
        	}
        	out.print("<div style=\"display: inline-block; width: 40%\">\n<h5 style=\"font-size: 17px; margin-bottom: -10px;\">"
        			+ speakers.getProductName()+"</h5>\n<p style=\"margin-bottom: 5px;padding-left: 10px; font-size: 10px;\"> by "
        					+speakers.getCompanyName()+"</p>\n<p style=\"margin-bottom: 0px;\">$ "+speakers.getPrice()+"</p>\n</div>\n");
		}
        out.print("<div style=\"display: inline-block; float: bottom;\">\n<a href=\"speakers\">SeeMore</a>\n</div>\n<div>\n");
        out.print("<br>\n<h4>Headphones</h4>\n<div>\n");
        for (String key : headPhonesMap.keySet()) {
        	count++;
        	HeadPhones headphones = headPhonesMap.get(key);
        	if(count > 2){
        		count = 0;
        		break;
        	}
        	out.print("<div style=\"display: inline-block; width: 40%\">\n<h5 style=\"font-size: 17px; margin-bottom: -10px;\">"
        			+ headphones.getProductName()+"</h5>\n<p style=\"margin-bottom: 5px;padding-left: 10px; font-size: 10px;\"> by "
        					+headphones.getCompanyName()+"</p>\n<p style=\"margin-bottom: 0px;\">$ "+headphones.getPrice()+"</p>\n</div>\n");
		}
        out.print("<div style=\"display: inline-block; float: bottom;\">\n<a href=\"headphones\">SeeMore</a>\n</div>\n<div>\n");
        out.print("<br>\n<h4>Phones</h4>\n<div>\n");
        for (String key : phonesMap.keySet()) {
        	count++;
        	Phones phones = phonesMap.get(key);
        	if(count > 2){
        		count = 0;
        		break;
        	}
        	out.print("<div style=\"display: inline-block; width: 40%\">\n<h5 style=\"font-size: 17px; margin-bottom: -10px;\">"
        			+ phones.getProductName()+"</h5>\n<p style=\"margin-bottom: 5px;padding-left: 10px; font-size: 10px;\"> by "
        					+phones.getCompanyName()+"</p>\n<p style=\"margin-bottom: 0px;\">$ "+phones.getPrice()+"</p>\n</div>\n");
		}
        out.print("<div style=\"display: inline-block; float: bottom;\">\n<a href=\"phones\">SeeMore</a>\n</div>\n<div>\n");
        out.print("<br>\n<h4>Laptops</h4>\n<div>\n");
        for (String key : laptopsMap.keySet()) {
        	count++;
        	Laptops laptop = laptopsMap.get(key);
        	if(count > 2){
        		count = 0;
        		break;
        	}
        	out.print("<div style=\"display: inline-block; width: 40%\">\n<h5 style=\"font-size: 17px; margin-bottom: -10px;\">"
        			+ laptop.getProductName()+"</h5>\n<p style=\"margin-bottom: 5px;padding-left: 10px; font-size: 10px;\"> by "
        					+laptop.getCompanyName()+"</p>\n<p style=\"margin-bottom: 0px;\">$ "+laptop.getPrice()+"</p>\n</div>\n");
		}
        out.print("<div style=\"display: inline-block; float: bottom;\">\n<a href=\"laptops\">SeeMore</a>\n</div>\n<div>\n");
        out.print("<br>\n<h4>External Storage</h4>\n<div>\n");
        for (String key : externalStorageMap.keySet()) {
        	count++;
        	ExternalStorage externalStorage = externalStorageMap.get(key);
        	if(count > 2){
        		count = 0;
        		break;
        	}
        	out.print("<div style=\"display: inline-block; width: 40%\">\n<h5 style=\"font-size: 17px; margin-bottom: -10px;\">"
        			+ externalStorage.getProductName()+"</h5>\n<p style=\"margin-bottom: 5px;padding-left: 10px; font-size: 10px;\"> by "
        					+externalStorage.getCompanyName()+"</p>\n<p style=\"margin-bottom: 0px;\">$ "+externalStorage.getPrice()+"</p>\n</div>\n");
		}
        out.print("<div style=\"display: inline-block; float: bottom;\">\n<a href=\"externalstorage\">SeeMore</a>\n</div>\n<div>\n");
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
