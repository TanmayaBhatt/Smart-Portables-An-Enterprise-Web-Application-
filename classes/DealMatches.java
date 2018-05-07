import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DealMatches extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		
		PrintWriter out = response.getWriter();
		MySQLDataStoreUtilities dataStoreUtilities = new MySQLDataStoreUtilities();
		
		
        response.setContentType("text/html");
        
		
        HashMap<String, ProductBean> selectedProducts = new HashMap<String, ProductBean>();
        int z=0;
		try
		{
            ArrayList<ProductBean> arr= new ArrayList<ProductBean>();
            MySQLDataStoreUtilities ob= new MySQLDataStoreUtilities();
            out.print("<!doctype html>");
            out.print("<html>");
            out.print("<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
            out.print("<title>Smart Portables</title>");
            out.print("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
            out.print("<meta name=\"viewport\" content=\"width=device-width, minimum-scale=1.0, maximum-scale=1.0\" />");
            out.print("</head>\n<body>\n<div id=\"container\">\n<header>\n<div class=\"width\">\n<h1><a href=\"/\">Smart<span>Portables</span></a></h1>");
            out.print("</div>\n</header>\n<nav>\n<div class=\"width\">\n<ul>\n<li class=\"start\"><a href=\"index.html\">Home</a></li>");
            //  out.print("<li><a href=\"contact.html\">Contact</a></li>\n<li style=\"float:right;\"><a href=\"cart\">Cart ("+Cart.getCartCount()+")</a></li>");
            //if(id.equals("null"))
            out.print("<li class=\"end\" style=\"float:right;\"><a href=\"login.html\">SignIn</a></li>\n</ul>\n</div>\n</nav>");
            //else
            //out.print("<li class=\"end\" style=\"float:right;\"><a href=\"Logout\">SignOut</a></li>\n</ul>\n</div>\n</nav>");
            out.print("<div id=\"body\" class=\"width\">\n<section id=\"content\">\n<article>\n<br><br>\n<center>\n<h2>We beat our competitors in all aspects. Price Match Guaranteed</h2>");
            out.print("</center>\n<br>\n\n<ul style=\"list-style-type: none;\">\n");
           
            //out.println("key: "+ mentry.getKey() + " & Value: ");
            //OrderBean ob=(OrderBean)mentry.getValue();
            //out.println(ob.getUserName());
            //out.println(ob.getOrderId());
            
            
            
            
            
            String line= null;
            HashMap<String, ArrayList<ProductBean>> productMap = new HashMap<String, ArrayList<ProductBean>>();
            productMap = dataStoreUtilities.viewAllProducts();
            
            
            for(Map.Entry<String, ArrayList<ProductBean>> map : productMap.entrySet())
            {
                for(ProductBean bean : map.getValue())
                {
                    
                    if((selectedProducts.size() < 2 ) && !selectedProducts.containsKey(bean.getName()))
                    {
                        
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Library/tomcat/webapps/csj/DealMatches.txt")));
                        line = bufferedReader.readLine();
                        //line = bufferedReader.readLine();
                        //out.println("FILE");
                        //out.println(line);
                        
                        if(line == null)
                        {
                            out.println("<h2>No Offers Found<h2> ");
                            
                            //out.println("<h2 align='center'>NO OFFERS FOUND</h2>");
                            
                            
                            break;
                            
                            
                        }
                        else
                        {
                            
                            do
                            {
                                //out.println(bean.getName());
                                if(line.contains(bean.getName()))
                                {
                                    out.println(line);
                                    out.println("<br>");
                                    selectedProducts.put(bean.getId(), bean);
                                    //out.println(bean.getName());
                                    
                                    
                                }
                                line = bufferedReader.readLine();
                                //if(z==0)
                                    //out.println(line);
                            }while(line!=null);
                            //out.println(z++);
                        }
                    }
                }
            }
            
            if(selectedProducts.isEmpty()){
                
                out.println("No Offers Found");
                
            }
            
            
         
             out.print("\n</ul>");
            
            out.print("</center>\n<br>\n\n<ul style=\"list-style-type: none;\">\n");
            out.println("<h2>Deal Matches</h2><br><br>");
            
            if(selectedProducts.isEmpty()){
                out.println("No Offers Found");
                
            }
            else{
            Set set = selectedProducts.entrySet();
            Iterator iterator = set.iterator();
                
            
            while(iterator.hasNext())
            {   //out.println("Has Next");
                Map.Entry<String, ProductBean> map = (Map.Entry)iterator.next();
                ProductBean bean = (ProductBean)map.getValue();
                //out.println( bean.getId());
                //out.println(bean.getRetailer());
                
                String image="";
                String productName="";
                String companyName="";
                double price=0.0;
                String category="";
                //smartWatch.getProductName()
                //smartWatch.getCompanyName()
                //smartWatch.getPrice()
                 String path = "/Library/tomcat/webapps/csj/";
                XMLParser xmlParser = new XMLParser(path+"WEB-INF/ProductCatalog.xml");
                HashMap<String, ExternalStorage> externalStorageMap = xmlParser.getExternalStorageMap();
                HashMap<String, SmartWatches> smartWatchesMap = xmlParser.getSmartWatchesMap();
                HashMap<String, Speakers> speakersMap = xmlParser.getSpeakersMap();
                HashMap<String, Laptops> laptopsMap = xmlParser.getLaptopsMap();
                HashMap<String, HeadPhones> headPhonesMap = xmlParser.getHeadPhonesMap();
                HashMap<String, Phones> phonesMap = xmlParser.getPhonesMap();
                String key1="";
                for (String key : externalStorageMap.keySet()) {
                    ExternalStorage externalStorage = externalStorageMap.get(key);
                    if(key.equals(bean.getId()))
                    {                                                       image=externalStorage.getImageName();
                        productName=externalStorage.getProductName();
                        companyName=externalStorage.getCompanyName();
                        price=externalStorage.getPrice();
                        key1=key;
                        category="External Storage";
                       // out.println("Has Next");
                        //smartWatch.getCompanyName()
                        //smartWatch.getPrice()
                    }
                }
                for (String key : smartWatchesMap.keySet()) {
                    SmartWatches smartWatches = smartWatchesMap.get(key);
                    if(key.equals(bean.getId()))
                    {                                                       image=smartWatches.getImageName();
                        productName=smartWatches.getProductName();
                        companyName=smartWatches.getCompanyName();
                        price=smartWatches.getPrice();
                        key1=key;
                        category="SmartWatches";
                        //smartWatch.getCompanyName()
                        //smartWatch.getPrice()
                    }
                }
                
                for (String key : phonesMap.keySet()) {
                    Phones phones = phonesMap.get(key);
                    if(key.equals(bean.getId()))
                    {                                                       image=phones.getImageName();
                        productName=phones.getProductName();
                        companyName=phones.getCompanyName();
                        price=phones.getPrice();
                        key1=key;
                        category="Phones";
                        //smartWatch.getCompanyName()
                        //smartWatch.getPrice()
                    }
                }
                for (String key : headPhonesMap.keySet()) {
                    HeadPhones headPhones = headPhonesMap.get(key);
                    if(key.equals(bean.getId()))
                    {
                        image=headPhones.getImageName();
                        productName=headPhones.getProductName();
                        companyName=headPhones.getCompanyName();
                        price=headPhones.getPrice();
                        key1=key;
                        category="HeadPhones";
                        //smartWatch.getCompanyName()
                        //smartWatch.getPrice()
                    }
                }
                for (String key : laptopsMap.keySet()) {
                    Laptops laptops = laptopsMap.get(key);
                    if(key.equals(bean.getId()))
                    {
                        image=laptops.getImageName();
                        productName=laptops.getProductName();
                        companyName=laptops.getCompanyName();
                        price=laptops.getPrice();
                        key1=key;
                        category="Laptops";
                        //smartWatch.getCompanyName()
                        //smartWatch.getPrice()
                    }
                }
                for (String key : speakersMap.keySet()) {
                    Speakers speakers = speakersMap.get(key);
                    if(key.equals(bean.getId()))
                    {
                        image=speakers.getImageName();
                        productName=speakers.getProductName();
                        companyName=speakers.getCompanyName();
                        price=speakers.getPrice();
                        key1=key;
                        category="Speakers";
                        //smartWatch.getCompanyName()
                        //smartWatch.getPrice()
                    }
                }
                
                out.print("<li>\n\n<div style=\"display: inline-block;width:40%;\">\n<img src=\"images/"+image+"\" "
                          + "style=\"width: 100px; height: 100px;\">\n<h5 style=\"font-size: 17px; margin-bottom: -10px; "
                          + "padding-left: 10px;\">"+productName+"</h5>\n<p style=\"margin-bottom: 5px;padding-left: 10px;"
                          + " font-size: 10px;\"> by "+companyName+"</p>\n<p style=\"margin-bottom: 0px;\">$"+price+"</p>"
                          + "\n</div>\n<div style=\"display: inline-block;width:20%;\">\n<form action=\"cart    \">\n<button "
                          + "type=\"submit\" value=\""+key1+"\" name=\"cart\">Add to cart</button>\n</form>\n\n\n\n<br>"
                          + "<form action=\"WriteReviews\" method=\"get\">"
                          +"<input type=\"hidden\" name=\"productName\" value =\""+productName +" \"> </input> "
                          +
                          "<input type=\"hidden\" name=\"retailer\" value =\""+companyName +" \"> </input> "
                          +
                          "<input type=\"hidden\" name=\"price\" value =\""+price +" \"> </input> "
                          +
                          "<input type=\"hidden\" name=\"category\" value =\""+category+"\"> </input> "
                          +
                          "<button type=\"submit\" value=\""+key1+"\" name=\"cart\">WriteReview</button></form>\n<br>"
                          +"<form action=\"ViewReviews\" method=\"get\">"
                          +"<input type=\"hidden\" name=\"productName\" value =\""+productName +" \"> </input> "
                          +"<button type=\"submit\" value=\""+key1+"\" name=\"cart\">ViewReview</button></form></div></li>\n<br>");
                
            }
            
            
            }
            
            
            
            
            
            
            
         
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

		catch(Exception e)
		{
            out.println(e);
		}
	

		 
		
	
	}

}
