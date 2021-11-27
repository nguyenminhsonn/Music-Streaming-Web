/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.Singer;
import model.Song;

/**
 *
 * @author nvlon
 */
public class SongDAO extends DBContext {

    public List<Song> getSongByName(String query) {
        List<Song> result = new ArrayList<>();
        String sql = "select song.songID, name, song.img, uri, likeCount, categoryName\n"
                + "from song inner join genre on song.songID = genre.songID \n"
                + "inner join category on genre.categoryID = category.categoryID\n"
                + "where name like '%" + query + "%'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Song s = new Song();
                s.setSongID(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setGenre(rs.getString(6));
                s.setImg(rs.getString(3));
                s.setUri(rs.getString(4));
                s.setlikeCount(rs.getInt(5));
                result.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e + " this");
        }

        for (int i = 0; i < result.size(); i++) {
            List<Singer> singer = new ArrayList<>();
            sql = "select songID, singer.singerID, name, info, img \n"
                    + "from songOf inner join singer on songOf.singerID = singer.singerID \n"
                    + "where songID = ?";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, result.get(i).getSongID());
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Singer sg = new Singer();
                    sg.setSingerID(rs.getInt(2));
                    sg.setName(rs.getString(3));
                    sg.setInfo(rs.getString(4));
                    sg.setImg(rs.getString(5));
                    singer.add(sg);
                }
            } catch (SQLException e) {
                System.out.println(e + " that");
            }
            result.get(i).setArtist(singer);
        }

        return result;
    }

    public List<Song> getSongOfArtist(int aid) {
        List<Song> result = new ArrayList<>();
        String sql = "select top 4 song.songID, song.name, song.img, uri, likeCount, categoryName, addDate\n"
                + "from song inner join genre on song.songID = genre.songID \n"
                + "inner join category on genre.categoryID = category.categoryID\n"
                + "inner join songOf on song.songID = songOf.songID\n"
                + "inner join singer on songOf.singerID = singer.singerID\n"
                + "where singer.singerID = ? \n"
                + "order by addDate desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, aid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Song s = new Song();
                s.setSongID(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setGenre(rs.getString(6));
                s.setImg(rs.getString(3));
                s.setUri(rs.getString(4));
                s.setlikeCount(rs.getInt(5));
                result.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e + " this");
        }

        for (int i = 0; i < result.size(); i++) {
            List<Singer> singer = new ArrayList<>();
            sql = "select songID, singer.singerID, name, info, img \n"
                    + "from songOf inner join singer on songOf.singerID = singer.singerID \n"
                    + "where songID = ?";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, result.get(i).getSongID());
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Singer sg = new Singer();
                    sg.setSingerID(rs.getInt(2));
                    sg.setName(rs.getString(3));
                    sg.setInfo(rs.getString(4));
                    sg.setImg(rs.getString(5));
                    singer.add(sg);
                }
            } catch (SQLException e) {
                System.out.println(e + " that");
            }
            result.get(i).setArtist(singer);
        }
        return result;
    }

    public List<Song> getLatest() {
        List<Song> result = new ArrayList<>();
        String sql = "select top 5 song.songID, name, song.img, uri, likeCount, categoryName, addDate\n"
                + "from song inner join genre on song.songID = genre.songID \n"
                + "inner join category on genre.categoryID = category.categoryID\n"
                + "order by addDate desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Song s = new Song();
                s.setSongID(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setGenre(rs.getString(6));
                s.setImg(rs.getString(3));
                s.setUri(rs.getString(4));
                s.setlikeCount(rs.getInt(5));
                result.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e + " this");
        }

        for (int i = 0; i < result.size(); i++) {
            List<Singer> singer = new ArrayList<>();
            sql = "select songID, singer.singerID, name, info, img \n"
                    + "from songOf inner join singer on songOf.singerID = singer.singerID \n"
                    + "where songID = ?";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, result.get(i).getSongID());
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Singer sg = new Singer();
                    sg.setSingerID(rs.getInt(2));
                    sg.setName(rs.getString(3));
                    sg.setInfo(rs.getString(4));
                    sg.setImg(rs.getString(5));
                    singer.add(sg);
                }
            } catch (SQLException e) {
                System.out.println(e + " that");
            }
            result.get(i).setArtist(singer);
        }

        return result;
    }

    public List<Song> getMostLike() {
        List<Song> result = new ArrayList<>();
        String sql = "select top 8 song.songID, name, song.img, uri, likeCount, categoryName\n"
                + "from song inner join genre on song.songID = genre.songID \n"
                + "inner join category on genre.categoryID = category.categoryID\n"
                + "order by likeCount desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Song s = new Song();
                s.setSongID(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setGenre(rs.getString(6));
                s.setImg(rs.getString(3));
                s.setUri(rs.getString(4));
                s.setlikeCount(rs.getInt(5));
                result.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e + " this");
        }

        for (int i = 0; i < result.size(); i++) {
            List<Singer> singer = new ArrayList<>();
            sql = "select songID, singer.singerID, name, info, img \n"
                    + "from songOf inner join singer on songOf.singerID = singer.singerID \n"
                    + "where songID = ?";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, result.get(i).getSongID());
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Singer sg = new Singer();
                    sg.setSingerID(rs.getInt(2));
                    sg.setName(rs.getString(3));
                    sg.setInfo(rs.getString(4));
                    sg.setImg(rs.getString(5));
                    singer.add(sg);
                }
            } catch (SQLException e) {
                System.out.println(e + " that");
            }
            result.get(i).setArtist(singer);
        }

        return result;
    }

    public List<Song> getSongByCategory(int categoryID) {
        List<Song> result = new ArrayList<>();
        String sql = "select song.songID, name, song.img, uri, likeCount, categoryName, addDate\n"
                + "from song inner join genre on song.songID = genre.songID \n"
                + "inner join category on genre.categoryID = category.categoryID\n"
                + "where category.categoryID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Song s = new Song();
                s.setSongID(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setGenre(rs.getString(6));
                s.setImg(rs.getString(3));
                s.setUri(rs.getString(4));
                s.setlikeCount(rs.getInt(5));
                result.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e + " this");
        }

        for (int i = 0; i < result.size(); i++) {
            List<Singer> singer = new ArrayList<>();
            sql = "select songID, singer.singerID, name, info, img \n"
                    + "from songOf inner join singer on songOf.singerID = singer.singerID \n"
                    + "where songID = ?";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, result.get(i).getSongID());
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Singer sg = new Singer();
                    sg.setSingerID(rs.getInt(2));
                    sg.setName(rs.getString(3));
                    sg.setInfo(rs.getString(4));
                    sg.setImg(rs.getString(5));
                    singer.add(sg);
                }
            } catch (SQLException e) {
                System.out.println(e + " that");
            }
            result.get(i).setArtist(singer);
        }

        return result;
    }

    public Song getSongByID(int songID) {
        Song s = new Song();
        String sql1 = "select song.songID, name, song.img, uri, likeCount, categoryName\n"
                + "from song inner join genre on song.songID = genre.songID \n"
                + "inner join category on genre.categoryID = category.categoryID\n"
                + "where song.songID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql1);
            st.setInt(1, songID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                s.setSongID(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setGenre(rs.getString(6));
                s.setImg(rs.getString(3));
                s.setUri(rs.getString(4));
                s.setlikeCount(rs.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println(e + " here");
        }

        List<Singer> singer = new ArrayList<>();
        String sql2 = "select songID, singer.singerID, name, info, img \n"
                + "from songOf inner join singer on songOf.singerID = singer.singerID \n"
                + "where songID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql2);
            st.setInt(1, songID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Singer sg = new Singer();
                sg.setSingerID(rs.getInt(2));
                sg.setName(rs.getString(3));
                sg.setInfo(rs.getString(4));
                sg.setImg(rs.getString(5));
                singer.add(sg);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        s.setArtist(singer);
        return s;
    }

    public List<Song> getSongsCRUD() {

        List<Song> result = new ArrayList<>();

        String sql = "select song.songID, name, song.img, uri, likeCount, categoryName\n"
                + "from song inner join genre on song.songID = genre.songID \n"
                + "inner join category on genre.categoryID = category.categoryID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Song s = new Song();
                s.setSongID(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setGenre(rs.getString(6));
                s.setImg(rs.getString(3));
                s.setUri(rs.getString(4));
                s.setlikeCount(rs.getInt(5));
                result.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e + " this");
        }

        for (int i = 0; i < result.size(); i++) {
            List<Singer> singer = new ArrayList<>();
            sql = "select songID, singer.singerID, name, info, img \n"
                    + "from songOf inner join singer on songOf.singerID = singer.singerID \n"
                    + "where songID = ?";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, result.get(i).getSongID());
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Singer sg = new Singer();
                    sg.setSingerID(rs.getInt(2));
                    sg.setName(rs.getString(3));
                    sg.setInfo(rs.getString(4));
                    sg.setImg(rs.getString(5));
                    singer.add(sg);
                }
            } catch (SQLException e) {
                System.out.println(e + " that");
            }
            result.get(i).setArtist(singer);
        }

        return result;
    }

    public List<Song> getSongByPage(List<Song> list, int start, int end) {
        List<Song> t = new ArrayList<>();
        for (int i = start; i < end; i++) {
            t.add(list.get(i));
        }
        return t;
    }

    public int addSong(Song s, int[] singers) {
        int out = 0;
        String sql1 = "insert into song values(?, ?, ?, 0, ?)";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);
        // insert into song values('Vung Ky Uc', 'image', 'link', 0, '2021-10-29')
        try {
            PreparedStatement st = connection.prepareStatement(sql1);
            st.setString(1, s.getName());
            st.setString(2, s.getImg());
            st.setString(3, s.getUri());
            st.setString(4, time);
            out = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        int id = -1;

        String sql2 = "select top 1 songID from song where addDate = ? order by songID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql2);
            st.setString(1, time);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        for (int i = 0; i < singers.length; i++) {
            String sql3 = "insert into songOf values(?, ?)";
            // insert into song values('Vung Ky Uc', 'image', 'link', 0, '2021-10-29')
            try {
                PreparedStatement st = connection.prepareStatement(sql3);
                st.setInt(1, id);
                st.setInt(2, singers[i]);
                st.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        String sql4 = "insert into genre values(?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql4);
            st.setInt(1, id);
            st.setInt(2, Integer.parseInt(s.getGenre()));
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return out;
    }

    public int deleteSong(int songID) {
        int out = 0;

        // xóa khỏi bảng genre
        try {
            PreparedStatement st = connection.prepareStatement("delete from genre where songID = ?");
            st.setInt(1, songID);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

        // xóa khỏi songOf
        try {
            PreparedStatement st = connection.prepareStatement("delete from songOf where songID = ?");
            st.setInt(1, songID);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

        // update song
        try {
            PreparedStatement st = connection.prepareStatement("delete from song where songID = ?");
            st.setInt(1, songID);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return out;
    }

    public int updateSong(Song s, int[] singers) {
        int out = 0;
        int songID = s.getSongID();

        // xóa khỏi bảng genre
        try {
            PreparedStatement st = connection.prepareStatement("delete from genre where songID = ?");
            st.setInt(1, songID);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

        // xóa khỏi songOf
        try {
            PreparedStatement st = connection.prepareStatement("delete from songOf where songID = ?");
            st.setInt(1, songID);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

        // update  lại song
        try {
            PreparedStatement st = connection.prepareStatement("update song set name = ?, uri = ?, img = ? where songID = ?");
            st.setString(1, s.getName());
            st.setString(2, s.getUri());
            st.setString(3, s.getImg());
            st.setInt(4, songID);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

        // thêm lại vào bảng songOf
        for (int i = 0; i < singers.length; i++) {
            try {
                PreparedStatement st = connection.prepareStatement("insert into songOf values(?, ?)");
                st.setInt(1, songID);
                st.setInt(2, singers[i]);
                st.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }


        // thêm lại vào bảng genre
        try {
            PreparedStatement st = connection.prepareStatement("insert into genre values(?, ?)");
            st.setInt(1, songID);
            st.setInt(2, Integer.parseInt(s.getGenre()));
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return out;
    }

    public static void main(String[] args) throws ParseException {
        Song s = new Song(25, 0, "Vung Ky Uc A", "1", "https://avatar-ex-swe.nixcdn.com/song/2020/05/14/c/6/d/9/1589425750636_640.jpg", "https://res.cloudinary.com/longfpt/video/upload/v1635522654/music/VungKyUc-Chillies-6278528_sqkswm.mp3");
        int[] singers = {5};

        SongDAO db = new SongDAO();
        db.updateSong(s, singers);
    }

}
