/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitiesConverters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import entities.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matheusfernal
 */
public class CollectionConverterTest
{
    
    public CollectionConverterTest()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of convertCollectionToDBObject method, of class CollectionConverter.
     */
    @Test
    public void testConvertCollectionToDBObject()
    {
        Collection collection = new Collection("collection_1");
        DBObject dbCollection = CollectionConverter.convertCollectionToDBObject(collection);
        
        assertEquals(collection.getName(), dbCollection.get("name").toString());
    }

    /**
     * Test of convertStringToCollection method, of class CollectionConverter.
     */
    @Test
    public void testConvertStringToCollection()
    {
        String collection_2 = "collection_2";
        Collection collection = CollectionConverter.convertStringToCollection(collection_2);
        
        assertEquals(collection_2, collection.getName());
    }
    
}
