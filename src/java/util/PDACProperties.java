/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheusfernal
 */
public class PDACProperties
{
    private static PDACProperties instance;
    private final Properties configProperties;
    
    public static PDACProperties getInstance()
    {
        if (instance == null)
        {
            instance = new PDACProperties();
        }
        return instance;
    }
    
    private PDACProperties() 
    { 
        //FIXME
        this.configProperties = new Properties();
//        try
//        {
//            this.configProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
//        } catch (IOException ex)
//        {
//            Logger.getLogger(PDACProperties.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private Properties getConfigProperties()
    {
        return configProperties;
    }
    
    public String getDBHost()
    {
        //FIXME
//        return getConfigProperties().getProperty("dbhost");
        return "localhost";
    }
    
    public String getDBPort()
    {
        //FIXME
//        return getConfigProperties().getProperty("dbport");
        return "27017";
    }
    
    public String getMainDB()
    {
        //FIXME
//        return getConfigProperties().getProperty("maindb");
        return "pdalbumscatalog";
    }
    
    public String getTestDB()
    {
        //FIXME
//        return getConfigProperties().getProperty("testdb");
        return "test";
        
    }
            
}
