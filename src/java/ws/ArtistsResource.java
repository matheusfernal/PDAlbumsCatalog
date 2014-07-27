/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws;

import database.AlbumsDAO;
import database.PDACMongoClient;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import org.json.JSONArray;
import util.PDACProperties;

/**
 * REST Web Service
 *
 * @author matheusfernal
 */
@Path("artists")
public class ArtistsResource
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
     * Creates a new instance of ArtistsResource
     */
    public ArtistsResource()
    {
    }

    /**
     * Retrieves representation of an instance of ws.ArtistsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getArtists()
    {
        List<String> artists = getDao().findAllArtists();
        
        JSONArray jsonArtists = new JSONArray(artists);
        return jsonArtists.toString();
    }
}
