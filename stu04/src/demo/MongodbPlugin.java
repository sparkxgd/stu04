package demo;

import com.jfinal.plugin.IPlugin;
import com.mongodb.MongoClient;

public class MongodbPlugin implements IPlugin{
	public static MongoClient client;
	private String ip;
	private int port;
	
	public MongodbPlugin(String ip,int port) {
		this.port=port;
		this.ip=ip;
	}
	
	@Override
	public boolean start() {
		client=new MongoClient(this.ip,this.port);
		return true;
	}

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		return false;
	}

}
