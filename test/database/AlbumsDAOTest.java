/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import entities.Album;
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
        
        long albunsQty = albumsDAO.getAlbums().count();
        
        assertEquals(1, albunsQty);
    }
    
    @Test
    public void testFindAllAlbunsOfCollection()
    {
        Album album1 = new Album("album_1", "artist_1");
        album1.setCollection("collection_1");
        
        Album album2 = new Album("album_2", "artist_2");
        album2.setCollection("collection_1");
        
        Album album3 = new Album("album_3", "artist_3");
        album3.setCollection("collection_1");
        
        Album album4 = new Album("album_4", "artist_4");
        album4.setCollection("collection_2");
        
        AlbumsDAO albumsDAO = new AlbumsDAO(db);
        albumsDAO.insertAlbum(album1);
        albumsDAO.insertAlbum(album2);
        albumsDAO.insertAlbum(album3);
        albumsDAO.insertAlbum(album4);
        
        List<Album> albumsOfCollection1 = albumsDAO.findAllAlbunsOfCollection("collection_1");
        
        assertEquals(3, albumsOfCollection1.size());
    }
    
    @Test
    public void testFindAllAlbunsOfNullCollection()
    {
        Album album8 = new Album("album_8", "artist_8");
        album8.setCollection(null);
        
        Album album9 = new Album("album_9", "artist_9");
        album9.setCollection("collection_9");
        
        AlbumsDAO albumsDAO = new AlbumsDAO(db);
        albumsDAO.insertAlbum(album8);
        albumsDAO.insertAlbum(album9);
        
        Album albumWithoutCollection = albumsDAO.findAllAlbunsOfCollection(null).get(0);
        
        assertEquals(album8.getTitle(), albumWithoutCollection.getTitle());
        assertEquals(album8.getArtist(), albumWithoutCollection.getArtist());
    }
    
    @Test
    public void testInsertAlbumSideEfectOfSettingId()
    {
        Album album5 = new Album("album_5", "artist_5");
        
        AlbumsDAO albumsDAO = new AlbumsDAO(db);
        albumsDAO.insertAlbum(album5);
        
        assertNotNull("id was not set", album5.getId());
    }
    
    @Test 
    public void testFindAlbumById()
    {
        Album album6 = new Album("album_6", "artist_6");
        
        AlbumsDAO albumsDAO = new AlbumsDAO(db);
        albumsDAO.insertAlbum(album6);
        
        Album retrievedAlbum6 = albumsDAO.findAlbumById(album6.getId());
        
        assertEquals(album6.getTitle(), retrievedAlbum6.getTitle());
        assertEquals(album6.getArtist(), retrievedAlbum6.getArtist());
    }
    
    @Test
    public void testUpdateAlbum()
    {
        Album album7 = new Album("album_7", "artist_7");
        album7.setGenre("genre_7");
        album7.setLabel("label_7");
        
        AlbumsDAO albumsDAO = new AlbumsDAO(db);
        albumsDAO.insertAlbum(album7);
        
        Album album7u = new Album(album7.getTitle(), album7.getArtist());
        album7u.setGenre("genre_7u");
        album7u.setId(album7.getId());
        
        albumsDAO.updateAlbum(album7u);
        
        long albunsQty = albumsDAO.getAlbums().count();
        assertEquals(1, albunsQty);
        
        Album album7ur = albumsDAO.findAlbumById(album7.getId());
        assertEquals("genre_7u", album7ur.getGenre());
        assertNull(album7ur.getLabel());
    }
    
    @Test
    public void testFindAllCollections()
    {
        Album album10 = new Album("album_10", "artist_10");
        album10.setCollection("B_collection");
        
        Album album11 = new Album("album_11", "artist_11");
        album11.setCollection("A_collection");
        
        Album album12 = new Album("album_12", "artist_12");
        album12.setCollection(null);
        
        Album album13 = new Album("album_13", "artist_13");
        album13.setCollection("C_collection");
        
        
        AlbumsDAO albumsDAO = new AlbumsDAO(db);
        albumsDAO.insertAlbum(album10);
        albumsDAO.insertAlbum(album11);
        albumsDAO.insertAlbum(album12);
        albumsDAO.insertAlbum(album13);
        
        List<String> collections = albumsDAO.findAllCollections();
        String[] expectedArray = {"<No Collection>", "A_collection", "B_collection", "C_collection"};
        assertArrayEquals(expectedArray, collections.toArray(new String[4]));
    }
    
    @Test
    public void testFindAllAlbums()
    {
        Album album14 = new Album("album_14", "artist_14");
        album14.setCollection("collection_14");
        
        Album album15 = new Album("album_15", "artist_15");
        album15.setCollection("collection_14");
        
        Album album16 = new Album("album_16", "artist_16");
        album16.setCollection(null);
        
        Album album17 = new Album("album_17", "artist_17");
        album17.setCollection("collection_15");
        
        AlbumsDAO albumsDAO = new AlbumsDAO(db);
        albumsDAO.insertAlbum(album14);
        albumsDAO.insertAlbum(album15);
        albumsDAO.insertAlbum(album16);
        albumsDAO.insertAlbum(album17);
        
        List<Album> albumsInserted = albumsDAO.findAllAlbums();
        
        assertEquals(4, albumsInserted.size());
    }
    
    @Test
    public void testFindAllArtists()
    {
        Album album18 = new Album("album_18", "C_artist");
        Album album19 = new Album("album_19", "A_artist");
        Album album20 = new Album("album_20", "D_artist");
        Album album21 = new Album("album_21", "B_artist");
        
        AlbumsDAO albumsDAO = new AlbumsDAO(db);
        albumsDAO.insertAlbum(album18);
        albumsDAO.insertAlbum(album19);
        albumsDAO.insertAlbum(album20);
        albumsDAO.insertAlbum(album21);
        
        List<String> allArtists = albumsDAO.findAllArtists();
        
        String[] expectedArtists = {"A_artist", "B_artist", "C_artist", "D_artist"};
        
        assertArrayEquals(expectedArtists, allArtists.toArray(new String[4]));
    }
    
    @Test
    public void testFindAllGenres()
    {
        Album album22 = new Album("album_22", "artist_22");
        album22.setGenre("C_genre");
        Album album23 = new Album("album_23", "artist_23");
        album23.setGenre(null);
        Album album24 = new Album("album_24", "artist_24");
        album24.setGenre("A_genre");
        Album album25 = new Album("album_25", "artist_25");
        album25.setGenre("B_genre");
        
        AlbumsDAO albumsDAO = new AlbumsDAO(db);
        albumsDAO.insertAlbum(album22);
        albumsDAO.insertAlbum(album23);
        albumsDAO.insertAlbum(album24);
        albumsDAO.insertAlbum(album25);
        
        List<String> allGenres = albumsDAO.findAllGenres();
        
        String[] expectedGenres = {"A_genre", "B_genre", "C_genre"};
        
        assertArrayEquals(expectedGenres, allGenres.toArray(new String[3]));
    }
    
    @Test
    public void testFindAllLabels()
    {
        Album album26 = new Album("album_22", "artist_22");
        album26.setLabel("C_label");
        Album album27 = new Album("album_23", "artist_23");
        album27.setLabel(null);
        Album album28 = new Album("album_24", "artist_24");
        album28.setLabel("A_label");
        Album album29 = new Album("album_25", "artist_25");
        album29.setLabel("B_label");
        
        AlbumsDAO albumsDAO = new AlbumsDAO(db);
        albumsDAO.insertAlbum(album26);
        albumsDAO.insertAlbum(album27);
        albumsDAO.insertAlbum(album28);
        albumsDAO.insertAlbum(album29);
        
        List<String> allLabels = albumsDAO.findAllLabels();
        
        String[] expectedLabels = {"A_label", "B_label", "C_label"};
        
        assertArrayEquals(expectedLabels, allLabels.toArray(new String[3]));
    }
}
