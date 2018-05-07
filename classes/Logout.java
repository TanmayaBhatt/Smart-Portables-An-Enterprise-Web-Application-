
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

public class Logout extends HttpServlet{
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {


response.setContentType("text/html");
HttpSession session = request.getSession();
    session.invalidate();
    response.sendRedirect("login.html");

}
}
