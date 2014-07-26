/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitiesConverters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import entities.Track;
import org.json.JSONException;
import org.json.JSONObject;

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
    
    //TODO: Pending test
    public static JSONObject convertTrackToJsonObject(Track track) throws JSONException
    {
        JSONObject jsonTrack = new JSONObject();
        jsonTrack.put("number", track.getNumber());
        jsonTrack.put("name", track.getName());
                
        return jsonTrack;
    }
    
    public static Track convertDBObjectToTrack(DBObject dbTrack)
    {
        //TODO: Verify if dbTrack is valid and throw exception otherwise
        Track track = new Track((Integer) dbTrack.get("number"), dbTrack.get("name").toString());
        return track;
    }
}
