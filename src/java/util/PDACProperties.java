/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        this.configProperties = new Properties();
        try
        {
            String fileName = "config.properties";
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null)
            {
                is = new FileInputStream(fileName);
            }
            this.configProperties.load(is);
        } catch (IOException ex)
        {
            Logger.getLogger(PDACProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Properties getConfigProperties()
    {
        return configProperties;
    }
    
    public String getDBHost()
    {
        return getConfigProperties().getProperty("dbhost");
    }
    
    public String getDBPort()
    {
        return getConfigProperties().getProperty("dbport");
    }
    
    public String getMainDB()
    {
        return getConfigProperties().getProperty("maindb");
    }
    
    public String getTestDB()
    {
        return getConfigProperties().getProperty("testdb");
        
    }
            
}
