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
                .append("year", album.getYear());
        
        String collectionName = null;
        if (album.getCollection() != null)
        {
            collectionName = album.getCollection().getName();
        }
        dbAlbum.append("collection", collectionName);
        
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
    
    public static Album convertDBObjectToAlbum(DBObject dbAlbum)
    {
        //TODO: Verify if dbAlbum is valid and throw exception otherwise
        Album album = new Album(dbAlbum.get("title").toString(), dbAlbum.get("artist").toString());
        album.setGenre(dbAlbum.get("genre").toString());
        album.setLabel(dbAlbum.get("label").toString());
        album.setCoverPath(dbAlbum.get("coverPath").toString());
        album.setYear((Integer) dbAlbum.get("year"));
        album.setCollection(CollectionConverter.convertStringToCollection(dbAlbum.get("collection").toString()));
        
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
