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
public class ArtistServlet extends HttpServlet {

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
            out.println("<title>Servlet ArtistServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ArtistServlet at " + request.getContextPath() + "</h1>");
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
        int aid = Integer.parseInt(request.getParameter("aid"));
        
        HttpSession session = request.getSession();
        
        // nếu đã đăng nhập thì lấy ra list playlist của user đó lên
        if (session.getAttribute("account") != null) {
            Account a = (Account) session.getAttribute("account");
            PlaylistDAO listDAO = new PlaylistDAO();
            List<Playlist> listPlay = listDAO.getAllList(a.getUsername());
            request.setAttribute("listP", listPlay);
        }
        
        ArtistDAO adb = new ArtistDAO();
        Singer singer = adb.getSingerByID(aid);
        
        request.setAttribute("singer", singer);
        
        SongDAO sdb = new SongDAO();
        
        List<Song> songs = sdb.getSongOfArtist(aid);
        List<Singer> relateds = adb.getRelatedArtist(aid);
        
        request.setAttribute("songs", songs);
        request.setAttribute("relateds", relateds);
        
        request.getRequestDispatcher("Artist.jsp").forward(request, response);
        
//        PrintWriter out = response.getWriter();
//        out.print(songs);
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
