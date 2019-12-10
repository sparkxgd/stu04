package demo;

import java.util.List;

import org.bson.Document;

import com.jfinal.core.Controller;
public class HelloController extends Controller {
    public void index() {
       renderText("Hello JFinal World.");
    }
    /**
     * 打开登录页面
     */
    public void openlogin() {
    	render("login.html");
    }
    /**
     * 登录
     */
    public void login() {
    	String username=getPara("username");
    	String password=getPara("password");
    	
    	//到mongodb数据库用户名和密码
    	MongoModel m=new MongoModel("student", "user");
    	
    	Document query=new Document();
    	query.append("username", username);
    	
    	List<Document> docs=m.find(query);
    	
    	if(docs.size()>0) {//说明用户名存在
    		String pw=docs.get(0).getString("password");
    		if(password.equals(pw)) {
    			setAttr("result", 0);//密码正确
    			renderJson();
    		}else {
    			setAttr("result", 1);//密码错误
    			renderJson();
    		}
    	}else {
    		setAttr("result", -1);//用户名不存在
			renderJson();
    	}
    }
    /**
     * 进入主页面
     */
    public void openmain() {
    	render("main.html");
    }
}