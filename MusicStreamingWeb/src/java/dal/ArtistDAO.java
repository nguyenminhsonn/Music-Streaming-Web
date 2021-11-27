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
import model.Singer;

/**
 *
 * @author nvlon
 */
public class ArtistDAO extends DBContext{

    public Singer getSingerByID(int aid){

        Singer s = new Singer();

        String sql = "select * from singer where singerID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, aid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                s.setSingerID(aid);
                s.setName(rs.getString(2));
                s.setInfo(rs.getString(3));
                s.setImg(rs.getString(4));
                return s;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }
    public List<Singer> getFavSinger(){

        List<Singer> wArtist = new ArrayList<>();

        String sql = "select top(4) singer.singerID,singer.[name],singer.img\n" +
                "from song inner join songOf\n" +
                "on song.songID = songOf.songID inner join singer\n" +
                "on singer.singerID = songOf.singerID order by likeCount desc";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Singer s = new Singer();
                s.setSingerID(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setImg(rs.getString(3));
                wArtist.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return wArtist;
    }
    public List<Singer> getSingerByName(String name){

        List<Singer> slist = new ArrayList<>();

        String sql = "select * from singer where name like '%"+name+"%'";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Singer s = new Singer();
                s.setSingerID(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setInfo(rs.getString(3));
                s.setImg(rs.getString(4));
                slist.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return slist;
    }

    public List<Singer> getSingersCRUD(){

        List<Singer> slist = new ArrayList<>();

        String sql = "select * from singer";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Singer s = new Singer();
                s.setSingerID(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setInfo(rs.getString(3));
                s.setImg(rs.getString(4));
                slist.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return slist;
    }

    public List<Singer> getSingerByPage(List<Singer> list, int start, int end){
        List<Singer> t = new ArrayList<>();
        for (int i = start; i < end; i++) {
            t.add(list.get(i));
        }
        return t;
    }

    public int addSinger(Singer singer){
        int out = 0;
        String sql = "insert into singer values (?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, singer.getName());
            st.setString(2, singer.getInfo());
            st.setString(3, singer.getImg());
            out = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return out;
    }

    public boolean canDelete(int singerID){
        String sql = "select * from songOf where singerID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, singerID);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    public int delSinger(int singerID){
        int out = 0;
        String sql = "delete from singer where singerID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, singerID);
            out = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return out;
    }

    public List<Singer> getRelatedArtist(int aid){
        List<Singer> slist = new ArrayList<>();
        List<Integer> relateds = new ArrayList<>();

        String sql = "select * from songOf where singerID != ? and songID in (select songID from songOf where singerID = ?) ";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, aid);
            st.setInt(2, aid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                relateds.add(rs.getInt("singerID"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        for (int i = 0; i < relateds.size(); i++) {
            slist.add(getSingerByID(relateds.get(i)));
        }

        return slist;
    }

    public int updateSinger(Singer singer){
        int out = 0;
        String sql = "update singer set name = ?, info = ?, img = ? where singerID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(4, singer.getSingerID());
            st.setString(1, singer.getName());
            st.setString(2, singer.getInfo());
            st.setString(3, singer.getImg());
            out = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return out;
    }

    public static void main(String[] args) {
        ArtistDAO adb = new ArtistDAO();
        List<Singer> relateds = adb.getRelatedArtist(10);
        for (Singer related : relateds) {
            System.out.println(related);
        }
    }
}
