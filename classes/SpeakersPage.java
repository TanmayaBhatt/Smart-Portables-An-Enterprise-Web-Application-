import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SpeakersPage extends HttpServlet  {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String path = "/Library/tomcat/webapps/csj/";
        XMLParser xmlParser = new XMLParser(path+"WEB-INF/ProductCatalog.xml");
        HttpSession session = request.getSession(true);
        String id = session.getAttribute("username").toString();
        HashMap<String, Speakers> speakersMap = xmlParser.getSpeakersMap();
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
        if(id.equals("null"))
            out.print("<li class=\"end\" style=\"float:right;\"><a href=\"login.html\">SignIn</a></li>\n</ul>\n</div>\n</nav>");
        else
            out.print("<li class=\"end\" style=\"float:right;\"><a href=\"Logout\">SignOut</a></li>\n</ul>\n</div>\n</nav>");
        out.print("<div id=\"body\" class=\"width\">\n<section id=\"content\">\n<article>\n<br><br>\n<center>\n<h2>Products</h2>");
        out.print("</center>\n<br>\n<h4>Speakers</h4>\n<ul style=\"list-style-type: none;\">\n");
        for (String key : speakersMap.keySet()) {
        	Speakers speaker = speakersMap.get(key);
        	out.print("<li>\n\n<div style=\"display: inline-block;width:40%;\">\n<img src=\"images/"+speaker.getImageName()+"\" "
        			+ "style=\"width: 100px; height: 100px;\">\n<h5 style=\"font-size: 17px; margin-bottom: -10px; "
        			+ "padding-left: 10px;\">"+speaker.getProductName()+"</h5>\n<p style=\"margin-bottom: 5px;padding-left: 10px;"
        			+ " font-size: 10px;\"> by "+speaker.getCompanyName()+"</p>\n<p style=\"margin-bottom: 0px;\">$"+speaker.getPrice()+"</p>"
        			+ "\n</div>\n<div style=\"display: inline-block;width:20%;\">\n<form action=\"cart	\">\n<button "
        			+ "type=\"submit\" value=\""+key+"\" name=\"cart\">Add to cart</button>\n</form>\n\n</div>\n</li>\n<br>");
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
