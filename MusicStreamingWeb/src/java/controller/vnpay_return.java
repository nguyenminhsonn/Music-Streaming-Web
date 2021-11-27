/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BillDAO;
import dal.ContractDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Bill;
import model.Contract;
import model.User;

/**
 *
 * @author nvlon
 */
public class vnpay_return extends HttpServlet {

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
            out.println("<title>Servlet vnpay_return</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet vnpay_return at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc == null) {
            response.sendRedirect("login");
        } else {
            
            // thêm hóa đơn vào database
            BillDAO bdb = new BillDAO();
            Bill bill = (Bill) session.getAttribute("bill");
            bdb.InsertBill(bill);
            
            // nâng cấp status người dùng
            UserDAO udb = new UserDAO();
            udb.upgrade(bill.getUsername());
            User user = udb.getCustomerInfo(acc);
            session.setAttribute("user", user);
            
            // thêm thông tin contract
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();

            String start = dtf.format(now);
            String end = dtf.format(now.plusDays(30));
            Contract contract = new Contract(0, bill.getUsername(), start, end);
            
            ContractDAO cdb = new ContractDAO();
            if(cdb.getContractInfo(bill.getUsername())==null){
                cdb.AddContract(contract);
            }
            else cdb.UpdateContract(contract);
            
            contract = cdb.getContractInfo(bill.getUsername());
            session.setAttribute("contract", contract);

            String vnp_ResponseCode = request.getParameter("otpvalue").equals("123456") ? "01" : "03";

            request.setAttribute("vnp_ResponseCode", vnp_ResponseCode);

            response.sendRedirect("vnpay_return.jsp");
        }
//        PrintWriter out = response.getWriter();
//        out.print(paymentUrl);
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
