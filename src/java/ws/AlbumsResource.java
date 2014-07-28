/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import database.AlbumsDAO;
import database.PDACMongoClient;
import entities.Album;
import entitiesConverters.AlbumConverter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getAlbums()
    {
        try
        {
            String albums = findAllAlbums();
            return Response.ok(albums, MediaType.APPLICATION_JSON).build();
        } catch (JSONException ex)
        {
            Logger.getLogger(AlbumsResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
        
    }

    @GET
    @Produces("application/json")
    @Path("/{collection}")
    public Response getAlbumsOfCollection(@PathParam("collection") final String collectionStr)
    {
        try {
            String albumsJsonStr;
            if (collectionStr.equals("<No Collection>"))
            {
                albumsJsonStr = findAlbums(null);
            } else
            {
                albumsJsonStr = findAlbums(collectionStr);
            }
            return Response.ok(albumsJsonStr, MediaType.APPLICATION_JSON).build();
        } catch (JSONException ex)
        {
            Logger.getLogger(AlbumsResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }

    @GET
    @Produces("application/json")
    @Path("/album/{albumId}")
    public Response getAlbumById(@PathParam("albumId") final String albumId)
    {
        try
        {
            Album album = getDao().findAlbumById(albumId);
            
            return album != null ? Response.ok(AlbumConverter.convertAlbumToJsonObject(album).toString(), MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.NOT_FOUND).build();
        } catch (JSONException ex)
        {
            Logger.getLogger(AlbumsResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }

    /**
     * PUT method for updating or creating an instance of AlbumsResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    @Path("/insert")
    public Response putNewAlbum(String content)
    {
        try
        {
            JSONObject jsonAlbum = new JSONObject(content);
            Album album = AlbumConverter.convertJsonObjectToAlbum(jsonAlbum);
            if (album.getArtist() != null && album.getTitle() != null)
            {
                getDao().insertAlbum(album);
                return Response.ok().build();
            } else 
            {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (JSONException ex)
        {
            Logger.getLogger(AlbumsResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }
    
    @POST
    @Consumes("application/json")
    @Path("/update")
    public Response postAlbumUpdate(String content)
    {
        try
        {
            JSONObject jsonAlbum = new JSONObject(content);
            Album album = AlbumConverter.convertJsonObjectToAlbum(jsonAlbum);
            getDao().updateAlbum(album);
            return Response.ok().build();
        } catch (JSONException ex)
        {
            Logger.getLogger(AlbumsResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }

    @DELETE
    @Consumes("text/plain")
    @Path("/delete/{id}")
    public Response deleteAlbum(@PathParam("id") final String id)
    {
        return getDao().removeAlbum(id) ? Response.ok().build() : Response.status(Response.Status.BAD_REQUEST).build();
    }

    private String findAlbums(String collection) throws JSONException
    {
        List<Album> albums = getDao().findAllAlbunsOfCollection(collection);

        JSONArray jsonAlbums = createAlbumsJson(albums);

        return jsonAlbums.toString();
    }

    private JSONArray createAlbumsJson(List<Album> albums) throws JSONException
    {
        JSONArray jsonAlbums = new JSONArray();
        for (Album album : albums)
        {
            jsonAlbums.put(AlbumConverter.convertAlbumToJsonObject(album));
        }
        return jsonAlbums;
    }

    private String findAllAlbums() throws JSONException
    {
        List<Album> albums = getDao().findAllAlbums();

        JSONArray jsonAlbums = createAlbumsJson(albums);

        return jsonAlbums.toString();
    }
}
