
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
public class AjaxUtility {
	
	// GETTING THE MATCHING CODE IN A STRINGBUFFER STARTS HERE 
	
		public StringBuffer readData(String searchId)
		{
			StringBuffer sb = new StringBuffer();
            HashMap<String, ProductBean> data=new HashMap<String, ProductBean>();
            
            AjaxUtility aj= new AjaxUtility();
            data= aj.getData();
            Iterator it = data.entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry pi = (Map.Entry)it.next();
                ProductBean p=(ProductBean)pi.getValue();
                if (p.getName().toLowerCase().startsWith(searchId)) {
                    sb.append("<product>");
                    sb.append("<productName>" + p.getName() + "</productName>");
                    sb.append("<id>" + p.getId() + "</id>");
                    
                    sb.append("</product>");
                } }
            return sb;
			}
			
		
	

	
	public HashMap<String, ProductBean> getData()
	{

		HashMap<String,ProductBean> products = new HashMap<String,ProductBean>();
		MySQLDataStoreUtilities dataStoreUtilities =  new MySQLDataStoreUtilities();
		ResultSet resultSet = null;
		
		try {
			dataStoreUtilities.createConnection();
			
			String query = "SELECT PRODUCT_NAME, PRODUCT_ID FROM ProductDetails";
			PreparedStatement preparedstatement = dataStoreUtilities.connection.prepareStatement(query);
			resultSet = preparedstatement.executeQuery();
			
			while(resultSet.next())
			{
				ProductBean productBean = new ProductBean();
                String pid=resultSet.getString("PRODUCT_ID");
                productBean.setId(pid);
                String pname= resultSet.getString("PRODUCT_NAME");
                productBean.setName(pname);
				
                products.put(pid,productBean);
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
		} 		

        return products;
	}
		

	
}
