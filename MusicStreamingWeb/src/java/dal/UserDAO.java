package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.User;

/**
 *
 * @author admin
 */
public class UserDAO extends DBContext {

    public int addUserInfo(User a) {
        String sql = "insert into dbo.[user] values(?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getUsername());
            st.setString(2, a.getFullname());
            st.setString(3, a.getEmail());
            st.setString(4, a.getStatus());
            long millis = System.currentTimeMillis();
            Date now = new Date(millis);
            st.setDate(5, now);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public User getCustomerInfo(Account a) {
        String sql = "  select username, fullname, email, status \n"
                + "from dbo.[user] where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getUsername());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new User(a.getUsername(), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public void updateUser(String fullname, String newmail, String username) {
        String sql = "UPDATE dbo.[user] SET fullname = ? , email = ? WHERE username = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullname);
            st.setString(2, newmail);
            st.setString(3, username);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void upgrade(String username) {
        String sql = "update [user] set status = 'premium' where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void downgrade(String username) {
        String sql = "update [user] set status = 'normal' where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public List<User> getAllUser(String role) {
        List<User> list = new ArrayList<>();
        String sql = "select * from dbo.[user] u where 1=1";
        if (role != null) {
            sql += "and u.status like '" + role + "'";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public String getRole(String username) {
        String sql = "select role from dbo.account where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<User> getUserbyName(String query, String role) {
        List<User> list = new ArrayList<>();
        String sql = "select u.* from dbo.[user] u\n"
                + "where u.fullName like '%" + query + "%'";
        if (role != null) {
            sql += "and u.status like '" + role + "'";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<User> getUserByPage(List<User> list, int start, int end) {
        List<User> t = new ArrayList<>();
        for (int i = start; i < end; i++) {
            t.add(list.get(i));
        }
        return t;
    }
    
    public boolean getActive(String username) {
        String sql = "select active from dbo.account where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getBoolean("active");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public Date getRegisDate(String username) {
        String sql = "select date from dbo.[user] where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDate("date");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        UserDAO u = new UserDAO();
        u.addUserInfo(new User("tung", "Phan Tung", "tung@gmail.com", "normal"));
    }

}
