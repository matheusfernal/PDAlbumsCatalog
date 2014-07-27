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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author matheusfernal
 */
public abstract class AlbumConverter
{
    public static DBObject convertAlbumToDBObject(Album album)
    {
        BasicDBObject dbAlbum = new BasicDBObject("title", album.getTitle())
                .append("artist", album.getArtist())
                .append("genre", album.getGenre())
                .append("label", album.getLabel())
                .append("coverPath", album.getCoverPath())
                .append("year", album.getYear())
                .append("collection", album.getCollection());
        
        BasicDBList dbTags = new BasicDBList();
        if (album.getTags() != null && !album.getTags().isEmpty()) {
            for (String tag : album.getTags())
            {
                dbTags.add(tag);
            }
        }
        dbAlbum.append("tags", dbTags);
        
        BasicDBList dbTracks = new BasicDBList();
        if (album.getTracks() != null && !album.getTracks().isEmpty())
        {
            for (Track track : album.getTracks())
            {
                dbTracks.add(TrackConverter.convertTrackToDBObject(track));
            }
        }
        dbAlbum.append("tracks", dbTracks);
        
        return dbAlbum;
    }
    
    public static JSONObject convertAlbumToJsonObject(Album album) throws JSONException
    {
        JSONObject jsonAlbum = new JSONObject();
        jsonAlbum.put("title", album.getTitle());
        jsonAlbum.put("artist", album.getArtist());
        jsonAlbum.put("genre", album.getGenre());
        jsonAlbum.put("label", album.getLabel());
        jsonAlbum.put("coverPath", album.getCoverPath());
        jsonAlbum.put("year", album.getYear());
        jsonAlbum.put("collection", album.getCollection());
        
        jsonAlbum.put("tags", new JSONArray(album.getTags()));
        
        JSONArray jsonTracks = new JSONArray();
        if (album.getTracks() != null && !album.getTracks().isEmpty())
        {
            for (Track track : album.getTracks())
            {
                jsonTracks.put(TrackConverter.convertTrackToJsonObject(track));
            }
            
        }
        jsonAlbum.put("tracks", jsonTracks);
        
        return jsonAlbum;
    }
    
    public static Album convertJsonObjectToAlbum(JSONObject jsonAlbum) throws JSONException
    {
        Album album = new Album(jsonAlbum.getString("title"), jsonAlbum.getString("artist"));
        album.setGenre(jsonAlbum.getString("genre"));
        album.setLabel(jsonAlbum.getString("label"));
        album.setCoverPath(jsonAlbum.getString("coverPath"));
        album.setYear(jsonAlbum.getInt("year"));
        album.setCollection(jsonAlbum.getString("collection"));
        
        JSONArray jsonTags = jsonAlbum.getJSONArray("tags");
        if (jsonTags.length() > 0)
        {
            album.setTags(new ArrayList<>(jsonTags.length()));
            for (int i = 0; i < jsonTags.length(); i++)
            {
                album.getTags().add(jsonTags.getString(i));
            }
        }
        
        JSONArray jsonTracks = jsonAlbum.getJSONArray("tracks");
        if (jsonTracks.length() > 0)
        {
            album.setTracks(new ArrayList<>(jsonTracks.length()));
            for (int i = 0; i < jsonTracks.length(); i++)
            {
                album.getTracks().add(TrackConverter.convertJsonObjectToTrack(jsonTracks.getJSONObject(i)));
            }
        }
        
        return album;
    }
    
    public static Album convertDBObjectToAlbum(DBObject dbAlbum)
    {
        //TODO: Verify if dbAlbum is valid and throw exception otherwise
        Album album = new Album((String) dbAlbum.get("title"), (String) dbAlbum.get("artist"));
        album.setId(dbAlbum.get("_id").toString());
        album.setGenre((String) dbAlbum.get("genre"));
        album.setLabel((String) dbAlbum.get("label"));
        album.setCoverPath((String) dbAlbum.get("coverPath"));
        album.setYear((Integer) dbAlbum.get("year"));
        album.setCollection((String) dbAlbum.get("collection"));
        
        List<String> tags = new ArrayList<>();
        if (dbAlbum.get("tags") != null && dbAlbum.get("tags") instanceof BasicDBList)
        {
            BasicDBList dbTags = (BasicDBList) dbAlbum.get("tags");
            dbTags.forEach((dbTag) -> tags.add(dbTag.toString()));
        }
        album.setTags(tags);
        
        List<Track> tracks = new ArrayList<>();
        if (dbAlbum.get("tracks") != null && dbAlbum.get("tracks") instanceof BasicDBList)
        {
            BasicDBList dbTracks = (BasicDBList) dbAlbum.get("tracks");
            dbTracks.forEach(dbTrack -> tracks.add(TrackConverter.convertDBObjectToTrack((DBObject) dbTrack)));
        }
        album.setTracks(tracks);
        
        return album;
    }
}
