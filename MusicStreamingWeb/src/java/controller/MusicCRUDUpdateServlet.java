/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ArtistDAO;
import dal.CategoryDAO;
import dal.SongDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Singer;
import model.Song;

/**
 *
 * @author nvlon
 */
public class MusicCRUDUpdateServlet extends HttpServlet {

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
            out.println("<title>Servlet MusicCRUDUpdateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MusicCRUDUpdateServlet at " + request.getContextPath() + "</h1>");
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
        ArtistDAO adb = new ArtistDAO();
        List<Singer> singers = adb.getSingersCRUD();

        CategoryDAO cdb = new CategoryDAO();
        List<Category> categories = cdb.getAllCategory();

        int songID = Integer.parseInt(request.getParameter("id"));
        SongDAO sdb = new SongDAO();
        Song song = sdb.getSongByID(songID);

        List<Singer> slist = song.getArtist();
        String artists = "";
        for (int i = 0; i < slist.size(); i++) {
            artists += ("*" + slist.get(i).getSingerID() + "*");
        }

//        PrintWriter out = response.getWriter();
//        out.print(artists);
        request.setAttribute("singers", singers);
        request.setAttribute("categories", categories);
        request.setAttribute("artists", artists);
        request.setAttribute("song", song);
        request.setAttribute("slist", slist);
        request.getRequestDispatcher("MusicCRUDUpdate.jsp").forward(request, response);
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
        
        int songID = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String[] s = request.getParameterValues("states[]");
        int[] singers = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            singers[i] = Integer.parseInt(s[i]);
        }
        String genre = request.getParameter("category");
        String uri = request.getParameter("song");
        String img = request.getParameter("img");

        Song song = new Song(songID, 0, name, genre, img, uri);
        SongDAO sdb = new SongDAO();

        sdb.updateSong(song, singers);
        response.sendRedirect("mcrud");
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
