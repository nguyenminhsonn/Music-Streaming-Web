/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import model.Bill;

/**
 *
 * @author nvlon
 */
public class BillDAO extends DBContext {

    public int getLatestTransID() {
        int transID = 1;

        String sql = "select top 1 transID from bill where date = ? order by transID desc";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        LocalDateTime now = LocalDateTime.now(); 
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, dtf.format(now));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String id = rs.getString(1);
                transID = Integer.parseInt(id.substring(7, id.length()));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return transID;
    }
    
    public void InsertBill(Bill bill){
        String sql = "insert into bill values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, bill.getTransID());
            st.setString(2, bill.getUsername());
            st.setString(3, bill.getDate());
            st.setString(4, bill.getPhone());
            st.setString(5, bill.getAddress());
            st.setString(6, bill.getPostalCode());
            st.setString(7, bill.getCity());
            st.setString(8, bill.getCountry());
            st.setFloat(9, bill.getAmount());
            st.setString(10, bill.getContent());
            st.setString(11, bill.getBank());
            st.setString(12, bill.getFullname());
            ResultSet rs = st.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        BillDAO bdb = new BillDAO();
        System.out.println(bdb.getLatestTransID());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now(); 
        System.out.println(dtf.format(now).replaceAll("-", ""));
    }

}
