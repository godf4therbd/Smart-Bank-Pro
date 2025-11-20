import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        // Check if user is logged in
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (isLoggedIn == null || !isLoggedIn) {
            response.sendRedirect("login.html");
            return;
        }
        
        // Get user data (in production, fetch from database)
        String accountNumber = (String) session.getAttribute("accountNumber");
        
        // Set user data for JSP
        request.setAttribute("accountNumber", accountNumber);
        request.setAttribute("userName", "John Anderson"); // Fetch from database
        request.setAttribute("balance", "5000.00"); // Fetch from database
        
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}