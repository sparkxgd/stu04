package demo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

public class MongoModel {
	private MongoCollection<Document> collection;
	
	public MongoModel(String collname) {
		collection=MongodbPlugin.mongoDatabase.getCollection(collname);
	}
	/**
	 *	  ���
	 */
	public void insert(Document data) {
		  collection.insertOne(data); 
	}
	/**
	 *	  ɾ��
	 */
	public void delete(Document query) {
		collection.deleteOne(query);
	}
	/**
	 *	  ����
	 */
	public List<Document> find(Document query){
		FindIterable<Document> findIterable = collection.find(query);  
        MongoCursor<Document> mongoCursor = findIterable.iterator();  
        List<Document> list=new ArrayList<Document>();
        while(mongoCursor.hasNext()){  
        	list.add(mongoCursor.next());  
        }  
        return list;
	}
	/**
	 *	  ����
	 */
	public void updata(Document data) {
        collection.updateMany(Filters.eq("no", data.get("no")), new Document("$set",data)); 
	}
	/**
	 *	  ����
	 */
	public void updataOne(Document data) {
        collection.updateOne(Filters.eq("no", data.get("no")), new Document("$set",data)); 
	}
	
	/**
	 *	 ģ�� ����
	 */
	public List<Document> query(String queryword){
		Document query=new Document();
		if(!"".equals(queryword)) {
			query.append("no", new Document("$regex",queryword));
		}
		FindIterable<Document> findIterable = collection.find(query);  
        MongoCursor<Document> mongoCursor = findIterable.iterator();  
        List<Document> list=new ArrayList<Document>();
        while(mongoCursor.hasNext()){  
        	list.add(mongoCursor.next());  
        }  
        return list;
	}

}
