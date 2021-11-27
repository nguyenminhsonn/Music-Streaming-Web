package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.User;

/**
 *
 * @author admin
 */
public class UserManageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserManageServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserManageServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a == null) {
            response.sendRedirect("login");
        } else if (a.getRole().equals("admin")) {
            UserDAO udb = new UserDAO();
            int op = Integer.parseInt(request.getParameter("op"));

            String query = request.getParameter("query");
            List<User> listU = new ArrayList<>();
            switch (op) {
                case 1:
                    if (query != null) {
                        listU = udb.getUserbyName(query, null);
                        request.setAttribute("query", query);
                    } else {
                        listU = udb.getAllUser(null);
                    }
                    break;
                case 2:
                    if (query != null) {
                        listU = udb.getUserbyName(query, "premium");
                        request.setAttribute("query", query);
                    } else {
                        listU = udb.getAllUser("premium");
                    }
                    break;
                case 3:
                    if (query != null) {
                        listU = udb.getUserbyName(query, "normal");
                        request.setAttribute("query", query);
                    } else {
                        listU = udb.getAllUser("normal");
                    }
                    break;
            }
            int size = listU.size();
            int numperPage = 5;
            int numPage = size / numperPage + (size % numperPage == 0 ? 0 : 1);
            String spage = request.getParameter("page");
            int page;
            if (spage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(spage);
            }
            int start, end;
            start = (page - 1) * numperPage;
            end = Math.min(size, page * numperPage);
            List<User> result = udb.getUserByPage(listU, start, end);

//        PrintWriter out = response.getWriter();
//        out.print(request.getParameter("op"));
            request.setAttribute("op", op);
            request.setAttribute("num", numPage);
            request.setAttribute("data", result);
            request.setAttribute("udb", udb);
            request.setAttribute("page", page);
            request.setAttribute("total", size);
            request.setAttribute("display", numperPage);
            request.getRequestDispatcher("UserManage.jsp").forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.print("<h1>Access Denied!</h1>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
