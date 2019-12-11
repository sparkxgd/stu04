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
    	MongoModel m=new MongoModel("student", "userinfo");
    	
    	Document query=new Document();
    	query.append("username", username);
    	
    	List<Document> docs=m.find(query);
    	
    	if(docs.size()>0) {//˵���û�������
    		String pw=docs.get(0).getString("passsword");
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
    /**
     * ��ȡѧ����Ϣ�б�
     */
    public void getstudents() {
    	MongoModel stu=new MongoModel("student", "studentinfo");
    	Document query=new Document();
    	List<Document> list=stu.find(query);//��mongodb����������
    	
    	setAttr("list", list);
    	renderJson();   
    	
    }
    /**
     * �����
     */
    public void openaddstu() {
    	render("addstu.html");
    }
    /**
     * ����ѧ����Ϣ
     */
    public void savestu() {
    	String no=getPara("no");
    	String name=getPara("name");
    	String sex=getPara("sex");
    	String age=getPara("age");
    	
    	MongoModel m=new MongoModel("student", "studentinfo");
    	
    	Document doc=new Document();
    	doc.append("no", no);
    	doc.append("name", name);
    	doc.append("sex", sex);
    	doc.append("age", age);
    	
    	m.save(doc);
    	redirect("openmain");
    }
    /**
     * ɾ��ѧ����Ϣ
     */
    public void delstu() {
    	String no=getPara("no");
    	
    	MongoModel m=new MongoModel("student", "studentinfo");
    	Document doc=new Document();
    	doc.append("no", no);
    	
    	m.delOne(doc);
    	
    	renderJson();
    	
    }
    
    
    
}