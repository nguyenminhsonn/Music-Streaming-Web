/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Bill;
import model.Song;

/**
 *
 * @author HP
 */
public class DashboardDAO extends DBContext {

    public ArrayList<Bill> getAll() throws SQLException {
        String sql = "select transID, username, date, amount from bill";
        ArrayList<Bill> result = new ArrayList<>();
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String date = rs.getString(3);
            float amount = rs.getFloat(4);
            Bill b = new Bill(id, name, date, amount);
            result.add(b);
        }
        return result;
    }

    public int getUser() throws SQLException {
        String sql = "Select COUNT(*) as 'Total User' from [User]";

        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        int totalUser = 0;
        while (rs.next()) {
            totalUser = rs.getInt(1);

        }

        return totalUser;
    }

    public int getRevenue() throws SQLException {
        String sql = "SELECT SUM(amount) FROM bill where MONTH(date) = MONTH(GETDATE())";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        int totalRevenue = 0;
        while (rs.next()) {
            totalRevenue = rs.getInt(1);
        }
        return totalRevenue;
    }

    public int getUserThisMonth() throws SQLException {
        String sql = "select COUNT(*) as 'Total User This Month' from [User] where MONTH(date) = MONTH(GETDATE())";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        int totalUserThisMonth = 0;
        while (rs.next()) {
            totalUserThisMonth = rs.getInt(1);

        }
        return totalUserThisMonth;
    }

    public int getUserLastMonth() throws SQLException {
        String sql = "select COUNT(*) as 'Total User Last Month' from [User] where MONTH(date) = MONTH(GETDATE()) - 1";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        int totalUserLastMonth = 0;
        while (rs.next()) {
            totalUserLastMonth = rs.getInt(1);

        }
        return totalUserLastMonth;
    }

    public int getPremiumUserThisMonth() throws SQLException {
        String sql = "select COUNT(*) as 'Total Premium User This Month' from [User] where MONTH(date) = MONTH(GETDATE()) and status = 'premium'";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        int totalPremiumUserThisMonth = 0;
        while (rs.next()) {
            totalPremiumUserThisMonth = rs.getInt(1);
        }
        return totalPremiumUserThisMonth;
    }

    public int getPremiumUserLastMonth() throws SQLException {
        String sql = "select COUNT(*) as 'Total Premium User Last Month' from [User] where MONTH(date) = MONTH(GETDATE()) - 1 and status = 'premium'";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        int totalPremiumUserLastMonth = 0;
        while (rs.next()) {
            totalPremiumUserLastMonth = rs.getInt(1);
        }
        return totalPremiumUserLastMonth;
    }

    public int getNormalUserThisMonth() throws SQLException {
        String sql = "select COUNT(*) as 'Total Premium User This Month' from [User] where MONTH(date) = MONTH(GETDATE()) and status = 'normal'";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        int totalNormalUserThisMonth = 0;
        while (rs.next()) {
            totalNormalUserThisMonth = rs.getInt(1);
        }
        return totalNormalUserThisMonth;
    }

    public int getNormalUserLastMonth() throws SQLException {
        String sql = "select COUNT(*) as 'Total Premium User Last Month' from [User] where MONTH(date) = MONTH(GETDATE()) - 1 and status = 'normal'";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        int totalNormalUserLastMonth = 0;
        while (rs.next()) {
            totalNormalUserLastMonth = rs.getInt(1);
        }
        return totalNormalUserLastMonth;
    }

    public int getRevenueJan(int month) throws SQLException {
        String sql = "select sum(amount) as 'Revenue in month' from bill where Month(date) = " + month;
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        int revenueByMonth = 0;
        while (rs.next()) {
            revenueByMonth = rs.getInt("Revenue in month");
        }
        return revenueByMonth;
    }

    public int getUserByMonth(int user) throws SQLException {
        String sql = "select COUNT(*) as 'User in month' from [user] where Month(date) = " + user;
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        int userByMonth = 0;
        while (rs.next()) {
            userByMonth = rs.getInt("User in month");
        }
        return userByMonth;
    }

//    public int getTopSong(int singerID) throws SQLException {
//        String sql = "select top 5 singerID, count(songID) as numOfSong from songOf group by singerID order by numOfSong desc";
//        PreparedStatement pre = connection.prepareStatement(sql);
//        ResultSet rs = pre.executeQuery();
//        while (rs.next()) {
////            numOfSong = rs.getInt("numOfSong");
//            singerID = rs.getInt("singerID");
//        }
//        return singerID;
//
//    }
    public ArrayList<Song> getTopLike() throws SQLException {
        String sql = "SELECT TOP(5)[name], [likeCount] FROM song order by likeCount desc";
        ArrayList<Song> list = new ArrayList<>();
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            String name = rs.getString(1);
            int likecount = rs.getInt(2);
            Song s = new Song(name, likecount);
            list.add(s);
        }
        return list;
    }

    public static void main(String[] args) throws SQLException {
        DashboardDAO db = new DashboardDAO();
//
//        int a = db.getUser();
//        int b = db.getRevenue();
//        int c = db.getUserThisMonth();
//        int d = db.getUserLastMonth();
//        int e = db.getPremiumUserThisMonth();
//        int f = db.getPremiumUserLastMonth();
//        int g = db.getNormalUserThisMonth();
//        int h = db.getNormalUserLastMonth();
//        int i = db.getRevenueJan(10);
//        System.out.println("asd: " + i);
//        int z = db.getUserByMonth(10);
//        System.out.println("add: " + z);
////        int songnumber = db.getTopSong();
//        System.out.println("adass: ");
        Song s = new Song();
        ArrayList list = db.getTopLike();
        
        
    }
}
