
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
public class AutoComplete extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            //out.println("Hey");
            boolean namesAdded = false;
            StringBuffer stringBuffer = new StringBuffer();
        
            //out.println("Here");
            String action = request.getParameter("action");
		String searchId= request.getParameter("searchId");
		
		
       // out.println("Here?");
       //out.println("search id is:"+searchId);
       //out.println("action=" + action);
       
		if(action.equals("complete"))
		{
			AjaxUtility ajaxUtility = new AjaxUtility();
			stringBuffer = ajaxUtility.readData(searchId);
            //out.println("Utility Called");
			if(stringBuffer != null || !stringBuffer.equals(""))
            {   //out.println("Something added");
				namesAdded = true;
			}
			if(namesAdded)
			{
				response.setContentType("text/xml");
				response.getWriter().write("<products>"+stringBuffer.toString()+"</products>");
			}
		}
        
        
        
       // out.println(stringBuffer.toString());
	
	}
}
