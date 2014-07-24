/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import database.AlbumsDAO;
import database.MongoClientSingleton;
import java.util.Set;

/**
 *
 * @author matheusfernal
 */
public class Main
{
    public static void main(String[] args)
    {
        DB db = MongoClientSingleton.getMongoClientInstance().getDB("test");
        
        Set<String> colls = db.getCollectionNames();

        for (String s : colls) 
        {
            System.out.println(s);
        }
        
        AlbumsDAO albumsDAO = new AlbumsDAO(db);
        albumsDAO.insertAlbum("Lotus", "Christina Aguilera", 2013, "pop", "my favorites");
    }
}
