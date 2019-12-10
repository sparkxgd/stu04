package demo;

import java.util.List;

import org.bson.Document;

import com.jfinal.core.Controller;
public class HelloController extends Controller {
    public void index() {
       renderText("Hello JFinal World.");
    }
    /**
     * �򿪵�¼ҳ��
     */
    public void openlogin() {
    	render("login.html");
    }
    /**
     * ��¼
     */
    public void login() {
    	String username=getPara("username");
    	String password=getPara("password");
    	
    	//��mongodb���ݿ��û���������
    	MongoModel m=new MongoModel("student", "user");
    	
    	Document query=new Document();
    	query.append("username", username);
    	
    	List<Document> docs=m.find(query);
    	
    	if(docs.size()>0) {//˵���û�������
    		String pw=docs.get(0).getString("password");
    		if(password.equals(pw)) {
    			setAttr("result", 0);//������ȷ
    			renderJson();
    		}else {
    			setAttr("result", 1);//�������
    			renderJson();
    		}
    	}else {
    		setAttr("result", -1);//�û���������
			renderJson();
    	}
    }
    /**
     * ������ҳ��
     */
    public void openmain() {
    	render("main.html");
    }
}