/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws;

import database.AlbumsDAO;
import database.PDACMongoClient;
import entities.Album;
import entities.Collection;
import entitiesConverters.AlbumConverter;
import entitiesConverters.CollectionConverter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import org.json.JSONArray;
import org.json.JSONException;
import util.PDACProperties;

/**
 * REST Web Service
 *
 * @author matheusfernal
 */
@Path("albums")
public class AlbumsResource
{
    @Context
    private UriInfo context;

    //TODO: Maybe I'll introduce another layer to avoid using the DAO in the webservice
    private AlbumsDAO dao;
    
    /**
     * Creates a new instance of AlbumsResource
     */
    public AlbumsResource()
    {
        
    }

    private AlbumsDAO getDao()
    {
        if (dao == null)
        {
            dao = new AlbumsDAO(PDACMongoClient.getMongoClientInstance().getDB(PDACProperties.getInstance().getMainDB()));
        }
        return dao;
    }
    
    /**
     * Retrieves representation of an instance of ws.AlbumsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getAlbums()
    {
        return findAlbums(null);
    }

    @GET
    @Produces("application/json")
    @Path("/{collection}")
    public String getAlbumsOfCollection(@PathParam("collection") final String collectionStr)
    {
        Collection collection = CollectionConverter.convertStringToCollection(collectionStr);
        
        return findAlbums(collection);
        
    }

    /**
     * PUT method for updating or creating an instance of AlbumsResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content)
    {
    }
    
    private String findAlbums(Collection collection)
    {
        List<Album> albums = getDao().findAllAlbunsOfCollection(collection);
        
        JSONArray jsonAlbums = new JSONArray();
        try
        {
            for (Album album : albums)
            {
                jsonAlbums.put(AlbumConverter.convertAlbumToJsonObject(album));
            }
        } catch (JSONException ex)
        {
            Logger.getLogger(AlbumsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return jsonAlbums.toString();
    }
}
