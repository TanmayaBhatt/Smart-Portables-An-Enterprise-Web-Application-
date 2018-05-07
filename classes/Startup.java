

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Startup")
public class Startup extends HttpServlet
{
    
	public void init() throws ServletException
    {

			//XMLParser ob=new XMLParser("/Library/tomcat/webapps/csj/WEB-INF/ProductCatalog.xml");

// 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
// 						throws ServletException, IOException {
// 							PrintWriter pw = response.getWriter();
// 							this.doPost(request,response);
// 							pw.print("Hey!!!!!!!");
// }
//
// 	protected void doPost(HttpServletRequest request, HttpServletResponse response)
// 						throws ServletException, IOException {
// 							PrintWriter pw = response.getWriter();
//
// 			pw.print("dopost");
		//new SmartWatchesHashMap();
		// new GameHashMap();
		// new UserHashMap();
		// new TabletHashMap();

    }
}
