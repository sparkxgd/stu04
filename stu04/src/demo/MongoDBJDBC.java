package demo;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBJDBC{
   public static void main( String args[] ){
      try{   
         // ���ӵ� mongodb ����
         MongoClient mongoClient = new MongoClient( "192.168.76.134" , 27017 );
         
         // ���ӵ����ݿ�
         MongoDatabase mongoDatabase = mongoClient.getDatabase("stu03");  
         System.out.println("Connect to database successfully");
         
         MongoCollection<Document> collection = mongoDatabase.getCollection("stuinfo");
         System.out.println("���� test ѡ��ɹ�");
         
         //���������ĵ�  
         /** 
         * 1. ��ȡ������FindIterable<Document> 
         * 2. ��ȡ�α�MongoCursor<Document> 
         * 3. ͨ���α�������������ĵ����� 
         * */  
         FindIterable<Document> findIterable = collection.find();  
         MongoCursor<Document> mongoCursor = findIterable.iterator();  
         while(mongoCursor.hasNext()){  
            System.out.println(mongoCursor.next());  
         }  
      
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
   }
}
