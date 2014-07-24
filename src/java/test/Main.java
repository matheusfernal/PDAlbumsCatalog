/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import database.AlbumsDAO;
import database.PDACMongoClient;
import java.util.Set;
import util.PDACProperties;

/**
 *
 * @author matheusfernal
 */
public class Main
{
    public static void main(String[] args)
    {
        DB db = PDACMongoClient.getMongoClientInstance().getDB(PDACProperties.getInstance().getMainDB());
        
        Set<String> colls = db.getCollectionNames();

        for (String s : colls) 
        {
            System.out.println(s);
        }
        
        AlbumsDAO albumsDAO = new AlbumsDAO(db);
        albumsDAO.insertAlbum("Bionic", "Christina Aguilera", 2010, "pop", "my favorites");
    }
}
