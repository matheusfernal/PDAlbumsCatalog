/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;

/**
 *
 * @author matheusfernal
 */
public class AlbumsDAO
{
    private DB db;
    private DBCollection albums;
    
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
    
}
