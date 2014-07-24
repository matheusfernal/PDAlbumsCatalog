/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheusfernal
 */
public class MongoClientSingleton
{
    private static MongoClient mongoClientInstance;

    public static MongoClient getMongoClientInstance()
    {
        if (mongoClientInstance == null) 
        {
            try
            {
                mongoClientInstance = new MongoClient("localhost");
            } catch (UnknownHostException ex)
            {
                Logger.getLogger(MongoClientSingleton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mongoClientInstance;
    }
    
}
