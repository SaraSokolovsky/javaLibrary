package servlets;

import bl.BorrowingService;
import dal.Borrowing;
import dal.Customer;
import dal.Item;
import dal.NewHibernateUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BookServlet", urlPatterns = {"/BookServlet"})
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName = request.getParameter("bookName");
        String action = request.getParameter("action");
        PrintWriter out =response.getWriter(); 

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Get username from cookie
        String username = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    break;
                }
            }
        }

        if (username == null) {
            response.getWriter().println("<html><body><h2>Username not found. Please log in again.</h2></body></html>");
            return;
        }

        // Create Item and Customer objects
        Item item = new Item();
        item.setName(bookName);

        Customer customer = (Customer) getServletContext().getAttribute("customer");

        BorrowingService borrowingService = new BorrowingService();

        if ("borrow".equals(action)) {
            if (bookName != null && !bookName.isEmpty()) {
                // Perform book borrowing
                Borrowing borrowing = new Borrowing(new Date(), customer, item);
                borrowingService.addBorrowing(borrowing);
                response.getWriter().println("<html><body><h2>The book '" + bookName  + " has been borrowed successfully.</h2></body></html>");
            } else {
                response.getWriter().println("<html><body><h2>Please fill in all fields.</h2></body></html>");
            }
        } else if ("return".equals(action)) {
            // Perform book return
            boolean success = borrowingService.returnBook(item, customer);
            if (success) {
                response.getWriter().println("<html><body><h2>The book '" + bookName + " has been returned successfully.</h2></body></html>");
            } else {
                response.getWriter().println("<html><body><h2>Failed to return the book. Please check book details and try again.</h2></body></html>");
            }
        } else {
            response.getWriter().println("<html><body><h2>Unknown action.</h2></body></html>");
        }
    }
}
