/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import entities.Album;
import entities.Collection;
import entitiesConverters.AlbumConverter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matheusfernal
 */
public class AlbumsDAO
{
    private final DB db;
    private final DBCollection albums;
    
    public AlbumsDAO(DB db)
    {
        this.db = db;
        this.albums = getDb().getCollection("albums");
    }

    private DB getDb()
    {
        return db;
    }
    
    public DBCollection getAlbums()
    {
        return albums;
    }
    
    
    // Public API

    public void insertAlbum(String title, String artist, Integer year, String genre, String collection) 
    {
        BasicDBObject album = new BasicDBObject("title", title);
        
        if (artist != null) 
        {
            album.append("artist", artist);
        }
        
        if (year != null)
        {
            album.append("year", year);
        }
        
        if (genre != null)
        {
            album.append("genre", genre);
        }
        
        album.append("collection", collection);
        
        getAlbums().insert(album);       
    }
    
    public List<Album> findAllAlbunsOfCollection(Collection collection)
    {
        getAlbums().createIndex(new BasicDBObject("collection", 1));
        
        DBCursor cursor = getAlbums().find(new BasicDBObject("collection", collection.getName()));
        List<Album> albums = new ArrayList<>();
        while (cursor.hasNext())
        {
            DBObject dbAlbum = cursor.next();
            Album album = AlbumConverter.convertDBObjectToAlbum(dbAlbum);
            albums.add(album);
        }
        
        return albums;
    }
}
