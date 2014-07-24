/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitiesConverters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import entities.Album;
import entities.Track;

/**
 *
 * @author matheusfernal
 */
public abstract class TrackConverter
{
    public static DBObject convertTrackToDBObject(Track track)
    {
        BasicDBObject dbTrack = new BasicDBObject("number", track.getNumber())
                .append("name", track.getName());
        
        return dbTrack;
    }
    
    public static Track convertDBObjectToTrack(DBObject dbTrack)
    {
        //TODO: Verify if dbTrack is valid and throw exception otherwise
        Track track = new Track(Short.parseShort(dbTrack.get("number").toString()), dbTrack.get("name").toString());
        return track;
    }
}
