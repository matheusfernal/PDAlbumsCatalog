/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import database.AlbumsDAO;
import database.PDACMongoClient;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.PDACProperties;

/**
 * REST Web Service
 *
 * @author matheusfernal
 */
@Path("collections")
public class CollectionsResource
{
    @Context
    private UriInfo context;
    
    //TODO: Maybe I'll introduce another layer to avoid using the DAO in the webservice
    private AlbumsDAO dao;
    
    private AlbumsDAO getDao()
    {
        if (dao == null)
        {
            dao = new AlbumsDAO(PDACMongoClient.getMongoClientInstance().getDB(PDACProperties.getInstance().getMainDB()));
        }
        return dao;
    }

    /**
     * Creates a new instance of CollectionsResource
     */
    public CollectionsResource()
    {
    }

    /**
     * Retrieves representation of an instance of ws.CollectionsResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getCollections()
    {       
        List<String> collections = getDao().findAllCollections();
        
        JSONArray jsonCollections = new JSONArray(collections);
        return Response.ok(jsonCollections.toString(), MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Produces("application/json")
    @Path("/all")
    public Response getCollectionsWithAll()
    {  
        List<String> collections = getDao().findAllCollections();
        collections.add(0, "<All Collections>");
        
        JSONArray jsonCollections = new JSONArray(collections);
        return Response.ok(jsonCollections.toString(), MediaType.APPLICATION_JSON).build();
    }
}
