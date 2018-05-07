import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import com.mongodb.AggregationOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;

public class TrendingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	//public void init() throws ServletException{
      	// Connect to Mongo DB
		//mongo = new MongoClient("localhost", 27017);
		
	//}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //init();
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
        MongoDBDataStoreUtilities db = new MongoDBDataStoreUtilities();
        AggregationOutput aggregation1= db.Top5search();
        AggregationOutput aggregation2= db.Top5Zipcode();
        AggregationOutput aggregation3= db.Top5Sold();
		/*DB db = mongo.getDB("CustomerReview");
		
		// If the collection does not exists, MongoDB will create it for you
		DBCollection myReviews = db.getCollection("MyReviews");
		
 
        DBObject sort =  new BasicDBObject();
        sort.put("reviewRatingMongo", -1);
        
        DBObject limit = new BasicDBObject();
        DBObject orderby = new BasicDBObject();
        DBObject groupfields = new BasicDBObject("_id", 0);
        groupfields.put("_id", "$productNameMongo");
        groupfields.put("count", new BasicDBObject("$sum", 1));
        DBObject group = new BasicDBObject("$group", groupfields);
        DBObject projectfields = new BasicDBObject("_id", 0);
        projectfields.put("productname", "$_id");
        projectfields.put("Review count", "$count");
        DBObject project = new BasicDBObject("$project", projectfields);
        
        orderby = new BasicDBObject("$sort", sort);
        
        limit = new BasicDBObject("$limit", 5);
        
        AggregationOutput aggregation1 = myReviews.aggregate(group, project, orderby, limit);
        /*for(DBObject result:aggregation1.results()){
            BasicDBObject bobj = (BasicDBObject) result;
            out.println(bobj.getString("productname"));
    }*/
        
        out.println("\n");
        
        
       /* groupfields.put("_id", "$retailerZipMongo");
        groupfields.put("count", new BasicDBObject("$sum", 1));
        sort.put("count", -1);
        projectfields.put("zipcode", "$_id");
        projectfields.put("Review count", "$count");
        */
        
       //AggregationOutput aggregation2 = myReviews.aggregate(group, project, orderby, limit);
        /*for(DBObject result:aggregation2.results()){
            BasicDBObject bobj = (BasicDBObject) result;
            out.println(bobj.getString("zipcode"));
        }*/
        
        /*groupfields.put("_id", "$productNameMongo");
        groupfields.put("count", new BasicDBObject("$sum", 1));
        sort.put("count", -1);
        projectfields.put("productname", "$_id");
        projectfields.put("Review count", "$count");
        
        groupfields.put("_id", "$productNameMongo");
        groupfields.put("count", new BasicDBObject("$sum", 1));
        sort.put("count", 1);
        projectfields.put("productname", "$_id");
        projectfields.put("Review count", "$count");
        
        AggregationOutput aggregation3 = myReviews.aggregate(group, project, orderby, limit);
        /*for(DBObject result:aggregation3.results()){
            BasicDBObject bobj = (BasicDBObject) result;
            out.println(bobj.getString("productname"));
        }*/
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
        out.println("<h1>Top Trending</h1>");
        out.println("</article>");
        out.println("<fieldset>");
        out.println("<h2>The Three trending tables are as follows:</h2><br>");
        out.println("Top Five most Liked Products:");
        out.println("<table id=\"t01\">");
        out.println("<tr>");
        out.println("<th>Id</th>");
        out.println("<th>Prod</th>");
        out.println("</tr>");
        int i=1;
        for(DBObject result:aggregation1.results()){
            BasicDBObject bobj = (BasicDBObject) result;
            //out.println(bobj.getString("productname"));
        out.println("<tr>");
        out.println("<td>"+i+"</td>");
        out.println("<td>"+bobj.getString("productname")+"</td>");
            out.println("</tr>");
                    i++;
            }
        out.println("</table><br><br><br>");
         out.println("Top Five Zipcodes with max Products Sold:");
        out.println("<table id=\"t02\">");
        out.println("<tr>");
        out.println("<th>Id</th>");
        out.println("<th>Prod</th>");
        out.println("</tr>");
         i=1;
        for(DBObject result:aggregation2.results()){
            BasicDBObject bobj = (BasicDBObject) result;
            //out.println(bobj.getString("zipcode"));
            out.println("<tr>");
            out.println("<td>"+i+"</td>");
            out.println("<td>"+bobj.getString("zipcode")+"</td>");
            out.println("</tr>");
            i++;
        }
        out.println("</table><br><br><br>");
        
        out.println("Top Five max Products Sold:");
        out.println("<table id=\"t03\">");
        out.println("<tr>");
        out.println("<th>Id</th>");
        out.println("<th>Prod</th>");
        out.println("</tr>");
        i=1;
        for(DBObject result:aggregation3.results()){
            BasicDBObject bobj = (BasicDBObject) result;
            //out.println(bobj.getString("zipcode"));
            out.println("<tr>");
            out.println("<td>"+i+"</td>");
            out.println("<td>"+bobj.getString("productname")+"</td>");
            out.println("</tr>");
            i++;
        }
        out.println("</table>");
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
