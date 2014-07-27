/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitiesConverters;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import entities.Album;
import entities.Track;
import java.util.Arrays;
import java.util.Collections;
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
        album.setCollection("collection_1");
        album.setYear(1);
        album.setTags(Arrays.asList("tag_1", "tag_2", "tag_3", "tag_4"));
        album.setTracks(Arrays.asList(new Track(1, "track_1"), new Track(2, "track_2"), new Track(3, "track_3"), new Track(4, "track_4")));
        
        DBObject dbAlbum = AlbumConverter.convertAlbumToDBObject(album);
        
        assertEquals(album.getTitle(), dbAlbum.get("title"));
        assertEquals(album.getArtist(), dbAlbum.get("artist"));
        assertEquals(album.getGenre(), dbAlbum.get("genre"));
        assertEquals(album.getCoverPath(), dbAlbum.get("coverPath"));
        assertEquals(album.getLabel(), dbAlbum.get("label"));
        assertEquals(album.getCollection(), dbAlbum.get("collection"));
        assertEquals(album.getYear(), dbAlbum.get("year"));
        
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
        BasicDBObject dbAlbum = new BasicDBObject("title", "album_2")
                .append("_id", "id_2")
                .append("artist", "artist_2")
                .append("genre", "genre_2")
                .append("coverPath", "cover_path_2")
                .append("label", "label_2")
                .append("collection", "collection_2")
                .append("year", 2);
                
        BasicDBList dbTags = new BasicDBList();
        dbTags.addAll(Arrays.asList("tag_5", "tag_6"));
        dbAlbum.append("tags", dbTags);
        
        BasicDBList dbTracks = new BasicDBList();
        dbTracks.add(new BasicDBObject("name", "track_5").append("number", 5));
        dbTracks.add(new BasicDBObject("name", "track_6").append("number", 6));
        dbAlbum.append("tracks", dbTracks);
        
        Album album = AlbumConverter.convertDBObjectToAlbum(dbAlbum);
        
        assertEquals(dbAlbum.get("title"), album.getTitle());
        assertEquals(dbAlbum.get("artist"), album.getArtist());
        assertEquals(dbAlbum.get("genre"), album.getGenre());
        assertEquals(dbAlbum.get("coverPath"), album.getCoverPath());
        assertEquals(dbAlbum.get("label"), album.getLabel());
        assertEquals(dbAlbum.get("collection"), album.getCollection());
        assertEquals(dbAlbum.get("year"), album.getYear());
        
        Track[] expectedTracks = new Track[2];
        int i = 0;
        for (Object track : dbTracks)
        {
            DBObject dbTrack = (DBObject) track;
            expectedTracks[i] = new Track((Integer) dbTrack.get("number"), dbTrack.get("name").toString());
            i++;
        }
        
        assertArrayEquals(expectedTracks, album.getTracks().toArray(new Track[2]));
    }
    
    @Test 
    public void convertJsonObjectToAlbum()
    {
        //TODO: Implement test
    }
    
    @Test
    public void convertAlbumToJsonObject()
    {
        //TODO: Implement test
    }
    
}
