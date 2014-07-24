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
import util.PDACProperties;

/**
 *
 * @author matheusfernal
 */
public class PDACMongoClient
{
    private static MongoClient mongoClientInstance;

    public static MongoClient getMongoClientInstance()
    {
        if (mongoClientInstance == null) 
        {
            try
            {
                try
                {
                    Integer dbPort = Integer.parseInt(PDACProperties.getInstance().getDBPort());
                    mongoClientInstance = new MongoClient(PDACProperties.getInstance().getDBHost(), dbPort);
                } catch (NumberFormatException e)
                {
                    mongoClientInstance = new MongoClient(PDACProperties.getInstance().getDBHost());
                }
                
            } catch (UnknownHostException ex)
            {
                Logger.getLogger(PDACMongoClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mongoClientInstance;
    }
    
    private PDACMongoClient()
    {
        
    }
    
}
