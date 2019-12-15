package demo;

import com.jfinal.plugin.IPlugin;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * ����mongodb�Ĳ��
 * @author Administrator
 *
 */
public class MongodbPlugin implements IPlugin{
	public static MongoDatabase mongoDatabase;//����mongodb�����ݿ�
	private String host;
	private int port;
	private String dbname;
	private MongoClient mongoClient;
	
	public MongodbPlugin(String host,int port,String dbname) {
		this.host=host;
		this.port=port;
		this.dbname=dbname;
	}
	@Override
	public boolean start() {
		try {
			// ���ӵ� mongodb ����120.79.42.237
			mongoClient= new MongoClient(host,port);
			mongoDatabase=mongoClient.getDatabase(dbname);
	         // ���ӵ����ݿ�
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean stop() {
		mongoClient.close();
		return true;
	}

}
