/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitiesConverters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import entities.Track;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matheusfernal
 */
public class TrackConverterTest
{
    
    public TrackConverterTest()
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
     * Test of convertTrackToDBObject method, of class TrackConverter.
     */
    @Test
    public void testConvertTrackToDBObject()
    {
        Track track = new Track(1, "track_1");
        DBObject dbTrack = TrackConverter.convertTrackToDBObject(track);
        
        assertEquals(track.getNumber(), dbTrack.get("number"));
        assertEquals(track.getName(), dbTrack.get("name").toString());
    }

    /**
     * Test of convertDBObjectToTrack method, of class TrackConverter.
     */
    @Test
    public void testConvertDBObjectToTrack()
    {
        DBObject dbTrack = new BasicDBObject("number", 2).append("name", "track_2");
        Track track = TrackConverter.convertDBObjectToTrack(dbTrack);
        
        assertEquals(dbTrack.get("number"), track.getNumber());
        assertEquals(dbTrack.get("name"), track.getName());
    }
    
}
