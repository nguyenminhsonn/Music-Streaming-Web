/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ArtistDAO;
import dal.DashboardDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Bill;
import model.Singer;
import model.Song;
import model.User;

/**
 *
 * @author nvlon
 */
public class DashboardServlet extends HttpServlet {

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
            out.println("<title>Servlet DashboardServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashboardServlet at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a == null) {
            response.sendRedirect("login");
        } else if (a.getRole().equals("admin")) {

            try {
                UserDAO adb = new UserDAO();
                User cus = adb.getCustomerInfo(a);
                Song s = new Song();
                DashboardDAO d = new DashboardDAO();
                ArrayList<Bill> list = d.getAll();
                ArrayList listR = new ArrayList();
                ArrayList listU = new ArrayList();
                ArrayList<Song> listS = d.getTopLike();

                ArrayList listSS = new ArrayList();
                int totalUser = d.getUser();
                int totalUserPre = d.getRevenue();
                int totalUserThisMonth = d.getUserThisMonth();
                int totalUserLastMonth = d.getUserLastMonth();
                int totalPremiumUserThisMonth = d.getPremiumUserThisMonth();
                int totalPremiumUserLastMonth = d.getPremiumUserLastMonth();
                int totalNormalUserThisMonth = d.getNormalUserThisMonth();
                int totalNormalUserLastMonth = d.getNormalUserLastMonth();
                for (int i = 1; i <= 12; i++) {
                    listR.add(d.getRevenueJan(i));
                }
                for (int i = 1; i <= 12; i++) {
                    listU.add(d.getUserByMonth(i));
                }
                request.setAttribute("listS", listS);
                request.setAttribute("userMonth", listU);
                request.setAttribute("amountMonth", listR);
                request.setAttribute("list", list);
                request.setAttribute("totalUser", totalUser);
                request.setAttribute("totalUserPre", totalUserPre);
                request.setAttribute("totalUserThisMonth", totalUserThisMonth);
                request.setAttribute("totalUserLastMonth", totalUserLastMonth);
                request.setAttribute("totalPremiumUserThisMonth", totalPremiumUserThisMonth);
                request.setAttribute("totalPremiumUserLastMonth", totalPremiumUserLastMonth);
                request.setAttribute("totalNormalUserThisMonth", totalNormalUserThisMonth);
                request.setAttribute("totalNormalUserLastMonth", totalNormalUserLastMonth);

                request.setAttribute("user", cus);
                request.getRequestDispatcher("Dashboard.jsp").forward(request, response);
            } catch (SQLException e) {
                out.print(e);
            }
        } else {
            PrintWriter out = response.getWriter();
            out.print("<h1>Access Denied!</h1>");
        }
    }

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
