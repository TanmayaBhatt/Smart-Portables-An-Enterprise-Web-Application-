

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteReviews extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		String productName = request.getParameter("productName");
		String productId   = request.getParameter("productId");
		String retailer	   = request.getParameter("retailer");
		String price	   = request.getParameter("price");
		String category    = request.getParameter("category");

		
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
        out.println("<form  action='SaveReviews'>");
        out.println("<h2>SUBMIT REVIEW</h2>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<td>Product Model Name</td>");
        out.println("<td><input type='text' readonly height='20' width='30' name='productName' value='"+productName+"'"+"></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> Product Category</td>");
        out.println("<td> <input type='text' readonly name='productCategory'value='"+category+"'"+"></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> Product Price</td>");
        out.println("<td> <input type='text' readonly name='productPrice' value='"+price+"'"+"></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> Retailer Name</td>");
        out.println("<td> <input type='text' readonly name='retailerName' value='"+retailer+"'"+"></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> Retailer City</td>");
        out.println("<td> <input type='text' name='retailerCity'></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> Retailer State</td>");
        out.println("<td> <input type='text' name='retailerState'></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> Retailer Zip</td>");
        out.println("<td> <input type='text' name='retailerZip'></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> ProductOnSale</td>");
        out.println("<td> <input type='text' name='productOnSale'></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> ManufacturerName</td>");
        out.println("<td> <input type='text' name='manufacturerName'></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> ManufacturerRebate</td>");
        out.println("<td> <input type='text' name='rebateAvailable'></td>");
        out.println("</tr>");
        out.println("");
        out.println("<tr>");
        out.println("<td> Username</td>");
        out.println("<td> <input type='text' name='userName'></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> User Age</td>");
        out.println("<td> <input type='text' name='userAge'></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> User gender</td>");
        out.println("<td> <select name='gender'>");
        out.println("<option>Male");
        out.println("</option>");
        out.println("<option>Female");
        out.println("</option>");
        out.println("</select>");
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> User Occupation</td>");
        out.println("<td> <input type='text' name='userOccupation'></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> Review Rating</td>");
        out.println("<td> <input type='text' name='reviewRating'></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> Review Date</td>");
        out.println("<td> <input type='text' name='reviewDate'></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> Review Text</td>");
        out.println("<td> <textarea rows='4' cols='50' name='reviewText'></textarea></td>");
        out.println("</tr>");
        out.println("");
        out.println("<tr><td> <input type='submit' value='Submit'></td> </tr>");
        out.println("</table>");
        out.println("</form>");
        out.println("");
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
