/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import org.json.JSONException;
import org.json.JSONObject;

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
    public String getCollections()
    {       
        JSONObject jsonObj = new JSONObject();
        try
        {
            jsonObj.put("idade", 29);
            jsonObj.put("nome", "Matheus");
        } catch (JSONException ex)
        {
            Logger.getLogger(CollectionsResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jsonObj.toString();
    }

    /**
     * PUT method for updating or creating an instance of CollectionsResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content)
    {
    }

}
