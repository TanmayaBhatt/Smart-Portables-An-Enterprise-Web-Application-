

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SaveReviews extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		PrintWriter out = response.getWriter();
		HashMap<String, ReviewBean> hrb = new HashMap<String, ReviewBean>();  
		


		
		
		
		String productCategory = request.getParameter("productCategory");
		String productPrice = request.getParameter("productPrice");
		String retailerName = request.getParameter("retailerName");
		String retailerCity = request.getParameter("retailerCity");
		String retailerState = request.getParameter("retailerState");
		String retailerZip = request.getParameter("retailerZip");
		String productOnSale = request.getParameter("productOnSale");
		String manufacturerName = request.getParameter("manufacturerName");
		String rebateAvailable = request.getParameter("rebateAvailable");
		String productName = request.getParameter("productName");
		String userName = request.getParameter("userName");
		String userAge = request.getParameter("userAge");
		String userGender = request.getParameter("gender");
		String userOccupation = request.getParameter("userOccupation");
		String reviewRating = request.getParameter("reviewRating");	
		String reviewDate = request.getParameter("reviewDate");
		String reviewText = request.getParameter("reviewText");
	
		
		ReviewBean reviewBean = new ReviewBean();
		
		reviewBean.setProductCategory(productCategory);
		reviewBean.setProductPrice(productPrice);
		reviewBean.setRetailerName(retailerName);
		reviewBean.setRetailerCity(retailerCity);
		reviewBean.setRetailerState(retailerState);
		reviewBean.setRetailerZip(retailerZip);
		reviewBean.setProductOnSale(productOnSale);
		reviewBean.setManufacturerName(manufacturerName);
		reviewBean.setRebateAvailable(rebateAvailable);
		reviewBean.setProductName(productName);
		reviewBean.setUserName(userName);
		reviewBean.setUserAge(userAge);
		reviewBean.setUserGender(userGender);
		reviewBean.setUserOccupation(userOccupation);
		reviewBean.setReviewRating(reviewRating);
		reviewBean.setReviewDate(reviewDate);
		reviewBean.setReviewText(reviewText);
		
		hrb.put(reviewBean.getProductName(), reviewBean);
		
		MongoDBDataStoreUtilities dataStoreUtilities = new MongoDBDataStoreUtilities();
		int result = dataStoreUtilities.submitReview(hrb);
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
        out.println("<h2>"+ "Review Success" +"</h2>");
        out.println("<p>"+"Review Placed" +"</p>");
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
