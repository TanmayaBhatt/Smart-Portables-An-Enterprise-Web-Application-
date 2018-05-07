import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartPage extends HttpServlet  {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String id = session.getAttribute("username").toString();
		String path = "/Library/tomcat/webapps/csj/";
        XMLParser xmlParser = new XMLParser(path+"WEB-INF/ProductCatalog.xml");
        HashMap<String, ExternalStorage> externalStorageMap = xmlParser.getExternalStorageMap();
        HashMap<String, SmartWatches> smartWatchesMap = xmlParser.getSmartWatchesMap();
        HashMap<String, Speakers> speakersMap = xmlParser.getSpeakersMap();
        HashMap<String, Laptops> laptopsMap = xmlParser.getLaptopsMap();
        HashMap<String, HeadPhones> headPhonesMap = xmlParser.getHeadPhonesMap();
        HashMap<String, Phones> phonesMap = xmlParser.getPhonesMap();
        HashMap<String, Accessories> accessoryMap = xmlParser.getAccessoriesMap();
        String cart_Id = request.getParameter("cart");
        HashMap<String, Accessories> recommend = new HashMap<>();
        if(cart_Id!=null){
        	if(cart_Id.contains("delete")){
        		cart_Id = cart_Id.replaceAll("delete_", "");
        		Cart.cartMap.remove(cart_Id);
        		out.print("<!doctype html>");
                out.print("<html>");
                out.print("<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
                out.print("<title>Smart Portables</title>");
                out.print("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
                out.print("<meta name=\"viewport\" content=\"width=device-width, minimum-scale=1.0, maximum-scale=1.0\" />");
                out.print("</head>\n<body>\n<div id=\"container\">\n<header>\n<div class=\"width\">\n<h1><a href=\"/\">CS<span>J</span></a></h1>");
                if(id.equals("null"))
                    out.print("</div>\n</header>\n<nav>\n<div class=\"width\">\n<ul>\n<li class=\"start\"><a href=\"index.html\">Home</a></li>");
                else
                    out.print("</div>\n</header>\n<nav>\n<div class=\"width\">\n<ul>\n<li class=\"start\"><a href=\"customer.html\">Home</a></li>");
                out.print("<li class=\"selected\"><a href=\"products\">Products</a></li><li><a href=\"track.html\">TrackOrder</a></li>");
                out.print("<li><a href=\"contact.html\">Contact</a></li>\n<li style=\"float:right;\"><a href=\"cart\">Cart ("+Cart.getCartCount()+")</a></li>");
                if(id.equals("null"))
                    out.print("<li class=\"end\" style=\"float:right;\"><a href=\"login.html\">SignIn</a></li>\n</ul>\n</div>\n</nav>");
                else
                    out.print("<li class=\"end\" style=\"float:right;\"><a href=\"Logout\">SignOut</a></li>\n</ul>\n</div>\n</nav>");
                out.print("<div id=\"body\" class=\"width\">\n<section id=\"content\">\n<article>\n<br><br>\n<center>\n<h2>Products</h2>");
                out.print("</center>\n<br>\n<h4>Cart</h4>\n<ul style=\"list-style-type: none;\">\n");
                if (Cart.cartMap.isEmpty()) {
                	out.print("<li>Cart is Empty</li></ul>");
    			}
                else{
                	for (String key : accessoryMap.keySet()) {
                		for (String key2 : Cart.cartMap.keySet()) {
                			if(accessoryMap.get(key).parentProductId.equals(key2)){
                				recommend.put(key, accessoryMap.get(key));
                			}
						}
					}
    	            for (String key : Cart.cartMap.keySet()) {
    	            	Cart c = Cart.cartMap.get(key);
    	            	out.print("<li>\n\n<div style=\"display: inline-block; width:40%;\">\n<img src=\"images/"+c.getImageName()+"\" "
    	            			+ "style=\"width: 100px; height: 100px;\">\n<h5 style=\"font-size: 17px; margin-bottom: -10px; "
    	            			+ "padding-left: 10px;\">"+c.getProductName()+"</h5>\n<p style=\"margin-bottom: 5px;padding-left: 10px;"
    	            			+ " font-size: 10px;\"> by "+c.getCompanyName()+"</p>\n<p style=\"margin-bottom: 0px;\""
    	            					+ ">$"+c.getPrice()+" x "+c.getCount()+"</p>"
    	            			+ "\n</div>\n<div style=\"display: inline-block;width:20%;\">\n<form action=\"cart	\">\n"
    	            			+ "<button type=\"submit\" value=\"delete_"+key+"\" name=\"cart\">Delete from cart</button>\n</form>\n\n</div>\n</li>\n<br>");
    	            	
    	    		}
    	            out.print("<hr>\n<li>\n<br><h4>Final Amount</h4>\n\n</li>\n<br><li>$");
    	            double finalamount = 0.0;
    	            for (String key : Cart.cartMap.keySet()) {
    	            	Cart c = Cart.cartMap.get(key);
    	            	finalamount+=c.getPrice()*c.getCount();
    	    		}
    	            out.print("\n<div style=\"display: inline-block; width:40%;\">"+finalamount+"\n</div>"
    	            		+ "<div style=\"display: inline-block;width:20%;\">\n<form action=\"Card.html	\">\n<button type=\"submit\" name=\"cart\">CheckOut</button>\n</form>\n\n</div>\n</li></ul>");
    	            
    	            if(!recommend.isEmpty())
    	            	out.print("<hr><br><h3>Recommendation</h3><br>");
    	            
    	            int slide_num = 1;
		            for(String key : recommend.keySet()){
		            	Accessories acc = recommend.get(key);
		            	out.print("<div class=\"slideshow-container\">\n<div class=\"mySlides fade\">\n<div class=\"numbertext\">"+slide_num+"/ "+recommend.keySet().size()+"</div>"
			            		+ "\n<img src=\"images/"+acc.getImageName()+"\" style=\"width:100%\">\n<div style=\"display:inline-block\">"
			            		+ "<div class=\"text\">"+acc.productName+"</div>\n<div class=\"text_sub\">by "+acc.getCompanyName()+"</div>\n<div class=\"text\" "
			            		+ "style=\"padding-top:3px;font-size:14px;\">$"+acc.getPrice()+"</div>\n</div>\n<div style=\"display:inline-block\">"
			            		+ "\n<center><form action=\"cart\">\n<button style=\"float:right;\" type=\"submit\" value=\""+key+"\" name=\"cart\">Add to cart</button>\n</form></center></div>\n</div>");
		            	slide_num++;
		            }
		            
		            if(!recommend.isEmpty())
		            	out.print("<script>\nvar slideIndex = 0;\nshowSlides();\nfunction showSlides() {\nvar i;\nvar "
		            			+ "slides = document.getElementsByClassName(\"mySlides\");\nfor (i = 0; i < slides.length; i++) {"
		            			+ "\nslides[i].style.display = \"none\";\n}\nslideIndex++;\nif (slideIndex > slides.length) "
		            			+ "{slideIndex = 1}\nslides[slideIndex-1].style.display = \"block\";\nsetTimeout(showSlides, 2000); "
		            			+ "\n}\n</script>");
                }
                	
                out.print("</article>\n</section>\n<aside class=\"sidebar\">\n<br><br>\n<ul>\n<li>\n<h4>Categories</h4>\n<ul>\n");
                out.print("<li><a href=\"smartwatch\">Smart Watches</a></li>\n<li><a href=\"speakers\">Speakers</a></li>\n");
                out.print("<li><a href=\"headphones\">HeadPhones</a></li>\n<li><a href=\"phones\">Phones</a></li>\n");
                out.print("<li><a href=\"laptops\">Laptops</a></li>\n<li><a href=\"externalstorage\">External Storage</a></li>\n");
                out.print("</ul>\n</li>\n<li>\n<h4>About us</h4>\n<ul>\n<li class=\"text\">\n<p>Example website for online retail of smart portables.");
                out.print("<a href=\"contact.html\" class=\"readmore\">Read More &raquo;</a></p>\n</li>\n</ul>\n</li>\n</ul>\n</aside>");
                out.print("\n<div class=\"clear\"></div>\n</div>\n<footer>\n<div class=\"footer-bottom\">\n<p>&copy; CSJ 2017. Online Retailer "
                		+ "smart portables by Tanmaya Bhatt</p>\n</div>\n</footer>\n</div>\n</body>\n</html>");

        	}
        	else{
	        	Cart cart = new Cart();
	    		if(Cart.cartMap.containsKey(cart_Id)){
	    			Cart.cartMap.get(cart_Id).setCount(Cart.cartMap.get(cart_Id).getCount()+1);
	    		}else if(externalStorageMap.containsKey(cart_Id)){
	    			for (String key : accessoryMap.keySet()) {
						if(accessoryMap.get(key).getParentProductId().equals(cart_Id)){
							recommend.put(key, accessoryMap.get(key));
						}
					}
	    			ExternalStorage external = externalStorageMap.get(cart_Id);
	    			cart.setProductId(cart_Id);
	    			cart.setCompanyName(external.getCompanyName());
	    			cart.setCount(1);
	    			cart.setImageName(external.getImageName());
	    			cart.setProductName(external.getProductName());
	    			cart.setPrice(external.getPrice());
	    			Cart.cartMap.put(cart_Id, cart);
	
	        	}else if(smartWatchesMap.containsKey(cart_Id)){
	        		for (String key : accessoryMap.keySet()) {
						if(accessoryMap.get(key).getParentProductId().equals(cart_Id)){
							recommend.put(key, accessoryMap.get(key));
						}
					}
	        		SmartWatches external = smartWatchesMap.get(cart_Id);
	    			cart.setProductId(cart_Id);
	    			cart.setCompanyName(external.getCompanyName());
	    			cart.setCount(1);
	    			cart.setImageName(external.getImageName());
	    			cart.setProductName(external.getProductName());
	    			cart.setPrice(external.getPrice());
	    			Cart.cartMap.put(cart_Id, cart);
	    			
	        	}else if(speakersMap.containsKey(cart_Id)){
	        		for (String key : accessoryMap.keySet()) {
						if(accessoryMap.get(key).getParentProductId().equals(cart_Id)){
							recommend.put(key, accessoryMap.get(key));
						}
					}
	        		Speakers external = speakersMap.get(cart_Id);
	    			cart.setProductId(cart_Id);
	    			cart.setCompanyName(external.getCompanyName());
	    			cart.setCount(1);
	    			cart.setImageName(external.getImageName());
	    			cart.setProductName(external.getProductName());
	    			cart.setPrice(external.getPrice());
	    			Cart.cartMap.put(cart_Id, cart);
	    			
	        	}else if (headPhonesMap.containsKey(cart_Id)) {
	        		for (String key : accessoryMap.keySet()) {
						if(accessoryMap.get(key).getParentProductId().equals(cart_Id)){
							recommend.put(key, accessoryMap.get(key));
						}
					}
	        		HeadPhones external = headPhonesMap.get(cart_Id);
	    			cart.setProductId(cart_Id);
	    			cart.setCompanyName(external.getCompanyName());
	    			cart.setCount(1);
	    			cart.setImageName(external.getImageName());
	    			cart.setProductName(external.getProductName());
	    			cart.setPrice(external.getPrice());
	    			Cart.cartMap.put(cart_Id, cart);
	    			
				}else if (phonesMap.containsKey(cart_Id)) {
					for (String key : accessoryMap.keySet()) {
						if(accessoryMap.get(key).getParentProductId().equals(cart_Id)){
							recommend.put(key, accessoryMap.get(key));
						}
					}
					Phones external = phonesMap.get(cart_Id);
	    			cart.setProductId(cart_Id);
	    			cart.setCompanyName(external.getCompanyName());
	    			cart.setCount(1);
	    			cart.setImageName(external.getImageName());
	    			cart.setProductName(external.getProductName());
	    			cart.setPrice(external.getPrice());
	    			Cart.cartMap.put(cart_Id, cart);
	    			
				}else if (laptopsMap.containsKey(cart_Id)) {
					for (String key : accessoryMap.keySet()) {
						if(accessoryMap.get(key).getParentProductId().equals(cart_Id)){
							recommend.put(key, accessoryMap.get(key));
						}
					}
					Laptops external = laptopsMap.get(cart_Id);
	    			cart.setProductId(cart_Id);
	    			cart.setCompanyName(external.getCompanyName());
	    			cart.setCount(1);
	    			cart.setImageName(external.getImageName());
	    			cart.setProductName(external.getProductName());
	    			cart.setPrice(external.getPrice());
	    			Cart.cartMap.put(cart_Id, cart);
				}else if (accessoryMap.containsKey(cart_Id)) {
					Accessories external = accessoryMap.get(cart_Id);
	    			cart.setProductId(cart_Id);
	    			cart.setCompanyName(external.getCompanyName());
	    			cart.setCount(1);
	    			cart.setImageName(external.getImageName());
	    			cart.setProductName(external.getProductName());
	    			cart.setPrice(external.getPrice());
	    			Cart.cartMap.put(cart_Id, cart);
				}
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
	            out.print("</center>\n<br>\n<h4>Cart</h4>\n<ul style=\"list-style-type: none;\">\n");
	            if (Cart.cartMap.isEmpty()) {
	            	out.print("<li>Cart is Empty</li></ul>");
				}
	            else{
                	for (String key : accessoryMap.keySet()) {
                		for (String key2 : Cart.cartMap.keySet()) {
                			if(accessoryMap.get(key).parentProductId.equals(key2)){
                				recommend.put(key, accessoryMap.get(key));
                			}
						}
					}
		            for (String key : Cart.cartMap.keySet()) {
		            	Cart c = Cart.cartMap.get(key);
		            	out.print("<li>\n\n<div style=\"display: inline-block; width:40%;\">\n<img src=\"images/"+c.getImageName()+"\" "
		            			+ "style=\"width: 100px; height: 100px;\">\n<h5 style=\"font-size: 17px; margin-bottom: -10px; "
		            			+ "padding-left: 10px;\">"+c.getProductName()+"</h5>\n<p style=\"margin-bottom: 5px;padding-left: 10px;"
		            			+ " font-size: 10px;\"> by "+c.getCompanyName()+"</p>\n<p style=\"margin-bottom: 0px;\""
		            					+ ">$"+c.getPrice()+" x "+c.getCount()+"</p>"
		            			+ "\n</div>\n<div style=\"display: inline-block;width:20%;\">\n<form action=\"cart	\">\n"
		            			+ "<button type=\"submit\" value=\"delete_"+key+"\" name=\"cart\">Delete from cart</button>\n</form>\n\n</div>\n</li>\n<br>");
		            	
		    		}
		            out.print("<hr>\n<li>\n<br><h4>Final Amount</h4>\n\n</li>\n<br><li>$");
		            double finalamount = 0.0;
		            for (String key : Cart.cartMap.keySet()) {
		            	Cart c = Cart.cartMap.get(key);
		            	finalamount+=c.getPrice()*c.getCount();
		    		}
		            out.print("\n<div style=\"display: inline-block; width:40%;\">"+finalamount+"\n</div>"
		            		+ "<div style=\"display: inline-block;width:20%;\">\n<form action=\"Card.html\">\n<button type=\"submit\" name=\"cart\">CheckOut</button>\n</form>\n\n</div>\n</li></ul>");
		            if(!recommend.isEmpty())
		            	out.print("<hr><br><h3>Recommendation</h3><br>");
	            
		            int slide_num = 1;
		            for(String key : recommend.keySet()){
		            	Accessories acc = recommend.get(key);
		            	out.print("<div class=\"slideshow-container\">\n<div class=\"mySlides fade\">\n<div class=\"numbertext\">"+slide_num+"/ "+recommend.keySet().size()+"</div>"
			            		+ "\n<img src=\"images/"+acc.getImageName()+"\" style=\"width:100%\">\n<div style=\"display:inline-block\">"
			            		+ "<div class=\"text\">"+acc.productName+"</div>\n<div class=\"text_sub\">by "+acc.getCompanyName()+"</div>\n<div class=\"text\" "
			            		+ "style=\"padding-top:3px;font-size:14px;\">$"+acc.getPrice()+"</div>\n</div>\n<div style=\"display:inline-block\">"
			            		+ "\n<center><form action=\"cart\">\n<button style=\"float:right;\" type=\"submit\" value=\""+key+"\" name=\"cart\">Add to cart</button>\n</form></center></div>\n</div>");
		            	slide_num++;
		            }
		            if(!recommend.isEmpty())
		            	out.print("<script>\nvar slideIndex = 0;\nshowSlides();\nfunction showSlides() {\nvar i;\nvar "
		            			+ "slides = document.getElementsByClassName(\"mySlides\");\nfor (i = 0; i < slides.length; i++) {"
		            			+ "\nslides[i].style.display = \"none\";\n}\nslideIndex++;\nif (slideIndex > slides.length) "
		            			+ "{slideIndex = 1}\nslides[slideIndex-1].style.display = \"block\";\nsetTimeout(showSlides, 2000); "
		            			+ "\n}\n</script>");
		            
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
	        }
	         else{
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
	            out.print("</center>\n<br>\n<h4>Cart</h4>\n<ul style=\"list-style-type: none;\">\n");
	            if (Cart.cartMap.isEmpty()) {
	            	out.print("<li>Cart is Empty</li></ul>");
				}
	            else{
                	for (String key : accessoryMap.keySet()) {
                		for (String key2 : Cart.cartMap.keySet()) {
                			if(accessoryMap.get(key).parentProductId.equals(key2)){
                				recommend.put(key, accessoryMap.get(key));
                			}
						}
					}
		            for (String key : Cart.cartMap.keySet()) {
		            	Cart c = Cart.cartMap.get(key);
		            	out.print("<li>\n\n<div style=\"display: inline-block; width:40%;\">\n<img src=\"images/"+c.getImageName()+"\" "
		            			+ "style=\"width: 100px; height: 100px;\">\n<h5 style=\"font-size: 17px; margin-bottom: -10px; "
		            			+ "padding-left: 10px;\">"+c.getProductName()+"</h5>\n<p style=\"margin-bottom: 5px;padding-left: 10px;"
		            			+ " font-size: 10px;\"> by "+c.getCompanyName()+"</p>\n<p style=\"margin-bottom: 0px;\""
		            					+ ">$"+c.getPrice()+" x "+c.getCount()+"</p>"
		            			+ "\n</div>\n<div style=\"display: inline-block;width:20%;\">\n<form action=\"cart	\">\n"
		            			+ "<button type=\"submit\" value=\"delete_"+key+"\" name=\"cart\">Delete from cart</button>\n</form>\n\n</div>\n</li>\n<br>");	            	
		    		}
		            out.print("<hr>\n<li>\n<br><h4>Final Amount</h4>\n\n</li>\n<br><li>$");
		            double finalamount = 0.0;
		            for (String key : Cart.cartMap.keySet()) {
		            	Cart c = Cart.cartMap.get(key);
		            	finalamount+=c.getPrice()*c.getCount();
		    		}
		            out.print("\n<div style=\"display: inline-block; width:40%;\">"+finalamount+"\n</div>"
		            		+ "<div style=\"display: inline-block;width:20%;\">\n<form action=\"Card.html\">\n"
		            		+ "<button type=\"submit\" name=\"cart\">CheckOut</button>\n</form>\n\n</div>\n</li></ul>");
		            if(!recommend.isEmpty())
		            	out.print("<hr><br><h3>Recommendation</h3><br>");
		            int slide_num = 1;
		            for(String key : recommend.keySet()){
		            	Accessories acc = recommend.get(key);
		            	out.print("<div class=\"slideshow-container\">\n<div class=\"mySlides fade\">\n<div class=\"numbertext\">"+slide_num+"/ "+recommend.keySet().size()+"</div>"
			            		+ "\n<img src=\"images/"+acc.getImageName()+"\" style=\"width:100%\">\n<div style=\"display:inline-block\">"
			            		+ "<div class=\"text\">"+acc.productName+"</div>\n<div class=\"text_sub\">by "+acc.getCompanyName()+"</div>\n<div class=\"text\" "
			            		+ "style=\"padding-top:3px;font-size:14px;\">$"+acc.getPrice()+"</div>\n</div>\n<div style=\"display:inline-block\">"
			            		+ "\n<center><form action=\"cart\">\n<button style=\"float:right;\" type=\"submit\" value=\""+key+"\" name=\"cart\">Add to cart</button>\n</form></center></div>\n</div>");
		            slide_num++;
		            }
		            if(!recommend.isEmpty())
		            	out.print("<script>\nvar slideIndex = 0;\nshowSlides();\nfunction showSlides() {\nvar i;\nvar "
		            			+ "slides = document.getElementsByClassName(\"mySlides\");\nfor (i = 0; i < slides.length; i++) {"
		            			+ "\nslides[i].style.display = \"none\";\n}\nslideIndex++;\nif (slideIndex > slides.length) "
		            			+ "{slideIndex = 1}\nslides[slideIndex-1].style.display = \"block\";\nsetTimeout(showSlides, 2000); "
		            			+ "\n}\n</script>");
	            }
	            
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
}
