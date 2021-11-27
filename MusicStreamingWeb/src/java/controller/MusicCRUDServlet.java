/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.SongDAO;
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
import model.Song;

/**
 *
 * @author nvlon
 */
public class MusicCRUDServlet extends HttpServlet {

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
            out.println("<title>Servlet MusicCRUDServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MusicCRUDServlet at " + request.getContextPath() + "</h1>");
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

            // lấy ra list tổng
            SongDAO sdb = new SongDAO();
            String query = request.getParameter("query");
            List<Song> slist = new ArrayList<>();
            if(query!=null){
                slist = sdb.getSongByName(query);
                request.setAttribute("query", query);
            }
            else  slist = sdb.getSongsCRUD();

            // numperPage là số item trên trang, numPage là số lượng trang. 
            // ví dụ 100 item mà numperPage là 10 thì có 10 trang
            int size = slist.size();
            int numperPage = 5;
            int numPage = size / numperPage + (size % numperPage == 0 ? 0 : 1);

            // xác định xem page đang đi đến là page số mấy
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
            List<Song> result = sdb.getSongByPage(slist, start, end);

            request.setAttribute("num", numPage);
            request.setAttribute("data", result);
            request.setAttribute("page", page);
            request.setAttribute("total", size);
            request.setAttribute("display", numperPage);
            request.getRequestDispatcher("MusicCRUD.jsp").forward(request, response);
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
