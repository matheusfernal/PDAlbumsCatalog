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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
    public void convertJsonObjectToAlbum() throws JSONException
    {
        JSONObject jsonAlbum = new JSONObject();
        jsonAlbum.put("title", "album_3");
        jsonAlbum.put("_id", "id_3");
        jsonAlbum.put("artist", "artist_3");
        jsonAlbum.put("genre", "genre_3");
        jsonAlbum.put("coverPath", "cover_path_3");
        jsonAlbum.put("label", "label_3");
        jsonAlbum.put("collection", "collection_3");
        jsonAlbum.put("year", 3);

        JSONArray jsonTags = new JSONArray(Arrays.asList("tag_7", "tag_8"));
        jsonAlbum.put("tags", jsonTags);

        JSONArray jsonTracks = new JSONArray();

        JSONObject track7 = new JSONObject();
        track7.put("name", "track_7");
        track7.put("number", 7);
        JSONObject track8 = new JSONObject();
        track8.put("name", "track_8");
        track8.put("number", 8);

        jsonTracks.put(track7);
        jsonTracks.put(track8);
        jsonAlbum.put("tracks", jsonTracks);

        Album album = AlbumConverter.convertJsonObjectToAlbum(jsonAlbum);

        assertEquals(jsonAlbum.get("title"), album.getTitle());
        assertEquals(jsonAlbum.get("artist"), album.getArtist());
        assertEquals(jsonAlbum.get("genre"), album.getGenre());
        assertEquals(jsonAlbum.get("coverPath"), album.getCoverPath());
        assertEquals(jsonAlbum.get("label"), album.getLabel());
        assertEquals(jsonAlbum.get("collection"), album.getCollection());
        assertEquals(jsonAlbum.get("year"), album.getYear());

        Track[] expectedTracks = new Track[2];

        for (int i = 0; i < jsonTracks.length(); i++)
        {
            JSONObject jsonTrack = jsonTracks.getJSONObject(i);
            expectedTracks[i] = new Track(jsonTrack.getInt("number"), jsonTrack.getString("name"));
        }

        assertArrayEquals(expectedTracks, album.getTracks().toArray(new Track[2]));
    }

    @Test
    public void convertAlbumToJsonObject() throws JSONException
    {
        Album album = new Album("album_4", "artist_4");
        album.setGenre("genre_4");
        album.setCoverPath("cover_path_4");
        album.setLabel("label_4");
        album.setCollection("collection_4");
        album.setYear(4);
        album.setTags(Arrays.asList("tag_9", "tag_10", "tag_11", "tag_12"));
        album.setTracks(Arrays.asList(new Track(9, "track_9"), new Track(10, "track_10"), new Track(11, "track_11"), new Track(12, "track_12")));

        JSONObject jsonAlbum = AlbumConverter.convertAlbumToJsonObject(album);

        assertEquals(album.getTitle(), jsonAlbum.getString("title"));
        assertEquals(album.getArtist(), jsonAlbum.getString("artist"));
        assertEquals(album.getGenre(), jsonAlbum.getString("genre"));
        assertEquals(album.getCoverPath(), jsonAlbum.getString("coverPath"));
        assertEquals(album.getLabel(), jsonAlbum.getString("label"));
        assertEquals(album.getCollection(), jsonAlbum.getString("collection"));
        assertEquals((int) album.getYear(), jsonAlbum.getInt("year"));

        String[] jsonTags = new String[4];
        for (int i = 0; i < jsonAlbum.getJSONArray("tags").length(); i++)
        {
            jsonTags[i] = jsonAlbum.getJSONArray("tags").getString(i);
        }
        assertArrayEquals(album.getTags().toArray(new String[4]), jsonTags);

        Track[] dbTracks = new Track[4];
        
        for (int i = 0; i < jsonAlbum.getJSONArray("tracks").length(); i++)
        {
            dbTracks[i] = new Track(jsonAlbum.getJSONArray("tracks").getJSONObject(i).getInt("number"), jsonAlbum.getJSONArray("tracks").getJSONObject(i).getString("name"));
        }
        assertArrayEquals(album.getTracks().toArray(new Track[4]), dbTracks);
    }

}
