/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.PDACProperties;

/**
 *
 * @author matheusfernal
 */
public class AlbumsDAOTest
{
    private DB db;
    
    public AlbumsDAOTest()
    {
    }
    
    @Before
    public void setUp()
    {
        this.db = PDACMongoClient.getMongoClientInstance().getDB(PDACProperties.getInstance().getTestDB());
        db.getCollection("albuns").remove(new BasicDBObject());
    }
    
    @After
    public void tearDown()
    {
        db.getCollection("albuns").remove(new BasicDBObject());
    }
    
    @Test
    public void testEmptyAlbumsCollection()
    {
        long albunsQty = db.getCollection("albums").count();
        assertEquals(0, albunsQty);
    }

    /**
     * Test of insertAlbum method, of class AlbumsDAO.
     */
    @Test
    public void testInsertAlbum()
    {
        System.out.println("insertAlbum");
        AlbumsDAO instance = new AlbumsDAO(db);
        instance.insertAlbum("Lotus", "Christina Aguilera", 2012, "pop", "my favorites");
        
        long albunsQty = db.getCollection("albums").count();
        
        assertEquals(1, albunsQty);
    }
    
}
