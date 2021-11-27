/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import model.Singer;
import dal.ArtistDAO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nvlon
 */
public class ArtistDAOTest {
    ArtistDAO adb = new ArtistDAO();
    
    @Test
    public void testArtistDAOGivenRightArgumentReturnsGoodResult() {
        Singer expected = new Singer(3, "My Anh", "N/A", "https://avatar-ex-swe.nixcdn.com/song/2021/07/06/7/a/3/0/1625546730475_640.jpg");
        Singer actual = new Singer(3, "My Anh", "N/A", "https://avatar-ex-swe.nixcdn.com/song/2021/07/06/7/a/3/0/1625546730475_640.jpg");
        
        assertEquals(expected, actual);
    }
    
}
