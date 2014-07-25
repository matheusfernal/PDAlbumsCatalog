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
import org.bson.types.ObjectId;

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

    public void insertAlbum(Album album) 
    {
        BasicDBObject dbAlbum = new BasicDBObject("title", album.getTitle())
                .append("artist", album.getArtist())
                .append("year", album.getYear())
                .append("genre", album.getGenre())
                .append("label", album.getLabel())
                .append("coverPath", album.getCoverPath());
                
        dbAlbum.append("collection", album.getCollection() != null ? album.getCollection().getName() : null);
        
        getAlbums().insert(dbAlbum);
        album.setId(dbAlbum.get("_id").toString());
    }
    
    public List<Album> findAllAlbunsOfCollection(Collection collection)
    {
        getAlbums().createIndex(new BasicDBObject("collection", 1));
        
        DBCursor cursor = getAlbums().find(new BasicDBObject("collection", collection.getName()));
        List<Album> _albums = new ArrayList<>();
        while (cursor.hasNext())
        {
            DBObject dbAlbum = cursor.next();
            Album album = AlbumConverter.convertDBObjectToAlbum(dbAlbum);
            _albums.add(album);
        }
        
        return _albums;
    }
    
    public Album findAlbumById(String id)
    {
        DBObject dbAlbum = getAlbums().findOne(new BasicDBObject("_id", new ObjectId(id)));
        
        return AlbumConverter.convertDBObjectToAlbum(dbAlbum);
    }
}
