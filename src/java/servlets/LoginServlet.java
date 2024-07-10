package servlets;

import bl.CustomerService;
import dal.Customer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && !username.isEmpty()) {
            // יצירת עוגיה עם שם המשתמש
            Cookie usernameCookie = new Cookie("username", username);
            usernameCookie.setMaxAge(60 * 60 * 24); // תקופת החיים של העוגיה - יום אחד
            response.addCookie(usernameCookie);

            // יצירת אובייקט Customer
            Customer customer = new Customer(username, password);

            // בדיקה אם הלקוח כבר קיים במערכת
            CustomerService cs = new CustomerService();
            Customer existingCustomer = cs.getCustomerByName(username);
            
            if (existingCustomer == null) {
                // הוספת הלקוח לבסיס הנתונים
                cs.addClient(customer);
            } else {
                // שימור הלקוח הקיים ב- `ServletContext` אם הוא קיים
                customer = existingCustomer;
            }

            // שמירת הלקוח ב- `ServletContext`
            getServletContext().setAttribute("customer", customer);

            // הפניה לדף קבלת פנים עם שם המשתמש
            request.setAttribute("username", username);
            request.getRequestDispatcher("wellcome.html").forward(request, response);
        } else {
            // במקרה שאין שם משתמש
            request.getRequestDispatcher("index.html").forward(request, response);
        }
}}

