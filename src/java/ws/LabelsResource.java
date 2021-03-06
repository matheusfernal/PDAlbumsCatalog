/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws;

import database.AlbumsDAO;
import database.PDACMongoClient;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.json.JSONArray;
import util.PDACProperties;

/**
 * REST Web Service
 *
 * @author matheusfernal
 */
@Path("labels")
public class LabelsResource
{
    @Context
    private UriInfo context;
    
    //TODO: Maybe I'll introduce another layer to avoid using the DAO in the webservice. Need dependency injection to allow testing.
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
     * Creates a new instance of LabelsResource
     */
    public LabelsResource()
    {
    }

    /**
     * Retrieves representation of an instance of ws.LabelsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getLabels()
    {
        List<String> labels = getDao().findAllLabels();
        
        JSONArray jsonLabels = new JSONArray(labels);
        return  Response.ok(jsonLabels.toString(), MediaType.APPLICATION_JSON).build();
    }
}
