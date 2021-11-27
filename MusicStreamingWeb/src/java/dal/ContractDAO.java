/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Contract;

/**
 *
 * @author nvlon
 */
public class ContractDAO extends DBContext {

    public Contract getContractInfo(String username) {
        String sql = "select * from contract where username = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Contract(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void AddContract(Contract contract) {
        String sql = "insert into contract values(?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, contract.getUsername());
            st.setString(2, contract.getStartDate());
            st.setString(3, contract.getEndDate());
            ResultSet rs = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void UpdateContract(Contract contract){
        String sql = "update contract set startDate = ?, expiredDate = ? where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(3, contract.getUsername());
            st.setString(1, contract.getStartDate());
            st.setString(2, contract.getEndDate());
            ResultSet rs = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        ContractDAO ctdb = new ContractDAO();
        System.out.println(ctdb.getContractInfo("long"));
    }
}
