import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRegistration extends HttpServlet implements Serializable  {
	PrintWriter out;
		UserBean userBean = new UserBean();
	
	    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	    {
			out = response.getWriter();
			String userName = request.getParameter("username");
			String passWord = request.getParameter("password");
			//String confirmPassword = request.getParameter("confirmpassword");
			String role = request.getParameter("role");
			
			
			userBean.setUserName(userName);
			userBean.setPassWord(passWord);
			userBean.setRole(role);
	    
		}
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				processRequest(request,response);
			
				HashMap<String,UserBean> hmb = new HashMap<String,UserBean>();
            hmb.put(userBean.getUserName(), userBean);
				/*ServletContext sc = request.getServletContext();
				String fileName= "/Library/tomcat/webapps/csj/UserDetails.txt";
				
	
				
				try{
					
					FileInputStream fileInputStream     = new FileInputStream(new File(fileName));
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);		
					
					hmb = (HashMap)objectInputStream.readObject();
					}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("File not read !!!!");
				}
					
				
				if(hmb.containsKey(userBean.getUserName()))
				{
					
					out.println("The UserName "+userBean.getUserName()+" already exists");
                    response.sendRedirect("login.html");
				
				}
				else
				{   
					hmb.put(userBean.getUserName(), userBean);
					FileOutputStream fileOutputStream = new FileOutputStream(fileName);
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
					objectOutputStream.writeObject(hmb);
					objectOutputStream.flush();
					objectOutputStream.close();
					fileOutputStream.close();
					//out.println("The UserName "+userBean.getUserName()+" has been added");
					response.sendRedirect("login.html");
					
				}*/
            out.println("Here");
            MySQLDataStoreUtilities dataStoreUtilities = new MySQLDataStoreUtilities();
            try {
                out.println("Here2");
                
                int userExists = dataStoreUtilities.userExists(userBean.getUserName());
                out.println("userExists="+userExists);
                if(userExists == 1)
                {
                    out.println("The UserName "+userBean.getUserName()+" already exists");
                    response.sendRedirect("signup.html");
                }
                else
                {
                    dataStoreUtilities.addUser(hmb);
                   
                        response.sendRedirect("login.html");
                    
                }
                
                
                
            } catch (ClassNotFoundException e1) {
                
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                
                e1.printStackTrace();
            } catch (SQLException e1) {
                
                e1.printStackTrace();
            }
            
        }

	

}
