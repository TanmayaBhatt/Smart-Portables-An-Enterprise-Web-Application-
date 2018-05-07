import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         XMLParser xmlParser = new XMLParser("/Library/tomcat/webapps/csj/WEB-INF/ProductCatalog.xml");
		response.setContentType("text/html");
		//response.sendRedirect("login.html");
        response.sendRedirect("DealMatches");
	}
}
