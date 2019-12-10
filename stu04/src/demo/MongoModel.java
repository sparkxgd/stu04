package demo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class MongoModel {

	private MongoCollection<Document> collection;//���϶���
	
	
	public MongoModel(String dbname,String setname) {
		//���ӵ����ݿ��еļ���
		this.collection=MongodbPlugin.client.getDatabase(dbname).getCollection(setname);
		
	}
	
	/*
	 * ��ѯ
	 */
	public List<Document> find(Document query) {
		FindIterable<Document> findIterable= this.collection.find(query);
	    MongoCursor<Document> mongoCursor = findIterable.iterator(); 
	    
	    List<Document> docs=new ArrayList<Document>();
	    
	    while(mongoCursor.hasNext()){ 
	    	docs.add(mongoCursor.next());
	    } 
		return docs;
	}
}
