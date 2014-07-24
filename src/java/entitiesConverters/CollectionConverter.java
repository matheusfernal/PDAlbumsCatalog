/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitiesConverters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import entities.Collection;

/**
 *
 * @author matheusfernal
 */
public abstract class CollectionConverter
{
    public static DBObject convertCollectionToDBObject(Collection collection)
    {
        BasicDBObject dbCollection = new BasicDBObject("name", collection.getName());
        
        return dbCollection;
    }
    
    public static Collection convertStringToCollection(String collectionName)
    {
        Collection collection = new Collection(collectionName);
        return collection;
    }
}
