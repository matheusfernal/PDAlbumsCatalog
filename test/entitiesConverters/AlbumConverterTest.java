/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitiesConverters;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import entities.Album;
import entities.Collection;
import entities.Track;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matheusfernal
 */
public class AlbumConverterTest
{
    
    public AlbumConverterTest()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of convertAlbumToDBObject method, of class AlbumConverter.
     */
    @Test
    public void testConvertAlbumToDBObject()
    {
        Album album = new Album("album_1", "artist_1");
        album.setGenre("genre_1");
        album.setCoverPath("cover_path_1");
        album.setLabel("label_1");
        album.setCollection(new Collection("collection_1"));
        album.setTags(Arrays.asList("tag_1", "tag_2", "tag_3", "tag_4"));
        album.setTracks(Arrays.asList(new Track(1, "track_1"), new Track(2, "track_2"), new Track(3, "track_3"), new Track(4, "track_4")));
        
        DBObject dbAlbum = AlbumConverter.convertAlbumToDBObject(album);
        
        assertEquals(album.getTitle(), dbAlbum.get("title"));
        assertEquals(album.getArtist(), dbAlbum.get("artist"));
        assertEquals(album.getGenre(), dbAlbum.get("genre"));
        assertEquals(album.getCoverPath(), dbAlbum.get("coverPath"));
        assertEquals(album.getLabel(), dbAlbum.get("label"));
        assertEquals(album.getCollection().getName(), dbAlbum.get("collection"));
        
        String[] dbTags = new String[4];
        int i = 0;
        for (Object tagObj : (BasicDBList) dbAlbum.get("tags"))
        {
            dbTags[i] = tagObj.toString();
            i++;
        }
        assertArrayEquals(album.getTags().toArray(new String[4]), dbTags);
        
        Track[] dbTracks = new Track[4];
        i = 0;
        for (Object trackObj : (BasicDBList) dbAlbum.get("tracks"))
        {
            dbTracks[i] = new Track((int) ((DBObject) trackObj).get("number"), ((DBObject) trackObj).get("name").toString());
            i++;
        }
        assertArrayEquals(album.getTracks().toArray(new Track[4]), dbTracks);
    }

    /**
     * Test of convertDBObjectToAlbum method, of class AlbumConverter.
     */
    @Test
    public void testConvertDBObjectToAlbum()
    {
    }
    
}
