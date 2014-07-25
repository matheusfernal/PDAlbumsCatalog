/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import entities.Album;
import entities.Collection;
import entitiesConverters.CollectionConverter;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
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
        db.getCollection("albums").remove(new BasicDBObject());
    }
    
    @After
    public void tearDown()
    {
        db.getCollection("albums").remove(new BasicDBObject());
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
        AlbumsDAO albumsDAO = new AlbumsDAO(db);
        
        Album album = new Album("album_1", "artist_1");
        albumsDAO.insertAlbum(album);
        
        long albunsQty = db.getCollection("albums").count();
        
        assertEquals(1, albunsQty);
    }
    
    @Test
    public void testFindAllAlbunsOfCollection()
    {
        Album album1 = new Album("album_1", "artist_1");
        album1.setCollection(CollectionConverter.convertStringToCollection("collection_1"));
        
        Album album2 = new Album("album_2", "artist_2");
        album2.setCollection(CollectionConverter.convertStringToCollection("collection_1"));
        
        Album album3 = new Album("album_3", "artist_3");
        album3.setCollection(CollectionConverter.convertStringToCollection("collection_1"));
        
        Album album4 = new Album("album_4", "artist_4");
        album4.setCollection(CollectionConverter.convertStringToCollection("collection_2"));
        
        AlbumsDAO albumsDAO = new AlbumsDAO(db);
        albumsDAO.insertAlbum(album1);
        albumsDAO.insertAlbum(album2);
        albumsDAO.insertAlbum(album3);
        albumsDAO.insertAlbum(album4);
        
        List<Album> albumsOfCollection1 = albumsDAO.findAllAlbunsOfCollection(CollectionConverter.convertStringToCollection("collection_1"));
        
        assertEquals(3, albumsOfCollection1.size());
    }
    
}
