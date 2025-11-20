import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        // Check if user is logged in
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (isLoggedIn == null || !isLoggedIn) {
            response.sendRedirect("login.html");
            return;
        }
        
        try {
            String depositAmountStr = request.getParameter("depositAmount");
            double depositAmount = Double.parseDouble(depositAmountStr);
            
            if (depositAmount <= 0) {
                request.setAttribute("error", "Deposit amount must be greater than zero");
                request.getRequestDispatcher("deposit.jsp").forward(request, response);
                return;
            }
            
            // Get current balance from session (in production, fetch from database)
            Double currentBalance = (Double) session.getAttribute("balance");
            if (currentBalance == null) {
                currentBalance = 5000.00;
            }
            
            // Update balance
            double newBalance = currentBalance + depositAmount;
            session.setAttribute("balance", newBalance);
            
            // In production: Save to database
            // accountService.deposit(accountNumber, depositAmount);
            
            // Redirect to dashboard with success message
            session.setAttribute("successMessage", "Deposit of ৳" + depositAmount + " processed successfully!");
            response.sendRedirect("dashboard.html");
            
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid deposit amount");
            request.getRequestDispatcher("deposit.jsp").forward(request, response);
        }
    }
}