/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ArtistDAO;
import dal.PlaylistDAO;
import dal.SongDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Playlist;
import model.Singer;
import model.Song;

/**
 *
 * @author nvlon
 */
public class SearchServlet extends HttpServlet {

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
            out.println("<title>Servlet SearchServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        String query = request.getParameter("query");
        String type = request.getParameter("type");
        HttpSession session = request.getSession();
        
        // nếu đã đăng nhập thì lấy ra list playlist của user đó lên
        if (session.getAttribute("account") != null) {
            Account a = (Account) session.getAttribute("account");
            PlaylistDAO listDAO = new PlaylistDAO();
            List<Playlist> listPlay = listDAO.getAllList(a.getUsername());
            request.setAttribute("listP", listPlay);
        }

        if (query == null || query.length() == 0) {
            String message = "Nothing yet! Enter your query and click the Search button";
            request.setAttribute("error", message);
        } else {
            if (type.equals("song")) {
                SongDAO sdb = new SongDAO();
                List<Song> slist = sdb.getSongByName(query);
                request.setAttribute("slist", slist);
            } else if (type.equals("artist")) {
                ArtistDAO adb = new ArtistDAO();
                List<Singer> alist = adb.getSingerByName(query);
                request.setAttribute("alist", alist);
            }
        }

        request.setAttribute("query", query);
        request.setAttribute("type", type);
        request.setAttribute("currentPage", "Search");
        request.getRequestDispatcher("Search.jsp").forward(request, response);
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
        request.setAttribute("currentPage", "Search");
        request.getRequestDispatcher("Search.jsp").forward(request, response);
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
