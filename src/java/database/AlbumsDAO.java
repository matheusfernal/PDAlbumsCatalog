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
import entities.Album;
import entitiesConverters.AlbumConverter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.bson.types.ObjectId;
import java.util.Collections;

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
                .append("coverPath", album.getCoverPath())
                .append("collection", album.getCollection());
        
        getAlbums().insert(dbAlbum);
        album.setId(dbAlbum.get("_id").toString());
    }
    
    /**
     * 
     * @param collection The desired collection or null for albums that are not in any collection
     * @return 
     */
    public List<Album> findAllAlbunsOfCollection(String collection)
    {
        getAlbums().createIndex(new BasicDBObject("collection", 1));
        
        DBCursor cursor = getAlbums().find(new BasicDBObject("collection", collection));
        return createAlbumsFromCursor(cursor);
    }

    private List<Album> createAlbumsFromCursor(DBCursor cursor)
    {
        List<Album> _albums = new ArrayList<>();
        while (cursor.hasNext())
        {
            DBObject dbAlbum = cursor.next();
            Album album = AlbumConverter.convertDBObjectToAlbum(dbAlbum);
            _albums.add(album);
        }
        
        return _albums;
    }
    
    public List<Album> findAllAlbums()
    {
        DBCursor cursor = getAlbums().find();
        return createAlbumsFromCursor(cursor);
    }
    
    public Album findAlbumById(String id)
    {
        DBObject dbAlbum = getAlbums().findOne(new BasicDBObject("_id", new ObjectId(id)));
        
        return AlbumConverter.convertDBObjectToAlbum(dbAlbum);
    }
    
    public void updateAlbum(Album album)
    {
        //TODO: Handle albums without id
        if (album.getId() != null)
        {
            DBObject searchQuery = new BasicDBObject("_id", new ObjectId(album.getId()));
            DBObject newDBAlbum = AlbumConverter.convertAlbumToDBObject(album);
            
            getAlbums().update(searchQuery, newDBAlbum);
        }
    }
    
    public List<String> findAllCollections()
    {
        getAlbums().createIndex(new BasicDBObject("collection", 1));
        
        List<String> collections = getAlbums().distinct("collection");
        
        boolean hadNull = collections.removeAll(Collections.singleton(null));
        collections.sort((String o1, String o2) -> Collator.getInstance().compare(o1, o2));
        
        if (hadNull)
        {
            collections.add(0, "<No Collection>");
        }
        return collections;
    }
}
