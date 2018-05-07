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


public class DealMatchesTest extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		
		PrintWriter out = response.getWriter();
		MySQLDataStoreUtilities dataStoreUtilities = new MySQLDataStoreUtilities();
		
		
		//HTML code chunk part 1 starts here
		
HashMap<String, ProductBean> selectedProducts = new HashMap<String, ProductBean>();
        int i=0;
		try
		{
	        

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
                            out.println("No Offers");

							//out.println("<h2 align='center'>NO OFFERS FOUND</h2>");
							

							break;	
							
					        
						}
						else
						{
							
						do
						{
                            out.println(bean.getName());
							if(line.contains(bean.getName()))
							{
                                out.println(line);
								selectedProducts.put(bean.getId(), bean);
                                out.println(bean.getName());
														        
						        						        
							}
                           line = bufferedReader.readLine();
                            if(i==0)
                            out.println(line);
							}while(line!=null);
                            out.println(i++);
						}
					  }					
				    }
			      }
			

			
			
				Set set = selectedProducts.entrySet();
				Iterator iterator = set.iterator();
			
				while(iterator.hasNext())
                {   out.println("Has Next");
					Map.Entry<String, ProductBean> map = (Map.Entry)iterator.next();
					ProductBean bean = (ProductBean)map.getValue();
					
					
			        
			        
			        out.println( bean.getId());
			        
			        
			        
			    	out.println(bean.getRetailer());
			        

			        

					
				}	
			
		}

		catch(Exception e)
		{
            out.println(e);
		}
	

		 
		
	
	}

}
