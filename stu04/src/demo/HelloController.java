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
    	//1���ж��û����Ƿ���ڣ�
    	//2��������ڣ����ж����룿
    	//3�����������ȷ����������ҳ��
    	//4�����������󣬾ͻص���¼ҳ�棬��ʾ�����λ
    	//5������û������ڣ��ͻص���¼ҳ�棬��ʾ�û�������
    	
    	String username=getPara("username");
    	String password=getPara("password");
    	
    	//ģ��mongodb���ݿ���û���������
    	StudentModel stus=new StudentModel();
    	Document query=new Document();
    	query.append("username", username);
    	
    	List<Document> docs=stus.find(query);
    	
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
    public void main() {
    	render("main.html");
    }
    /**
     * ��ȡѧ����Ϣ�б�
     */
    public void getstudents() {
    	String no=getPara("no","");
    	StudentModel stu=new StudentModel();
    	List<Document> list=stu.query(no);//��mongodb����������
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
    	
    	StudentModel m=new StudentModel();
    	
    	Document doc=new Document();
    	doc.append("no", no);
    	doc.append("name", name);
    	doc.append("sex", sex);
    	doc.append("age", age);
    	
    	m.insert(doc);
    	redirect("main");
    }
    /**
     * ɾ��ѧ����Ϣ
     */
    public void delstu() {
    	String no=getPara("no");
    	
    	StudentModel m=new StudentModel();
    	Document doc=new Document();
    	doc.append("no", no);
    	
    	m.delete(doc);
    	
    	renderJson();
    	
    }
    
	/**
	 * �򿪱༭ҳ��
	 */
	public void openedit() {
		String no = getPara("no");
		setAttr("no", no);
		renderFreeMarker("stuedit.html");
	}

	/**
	 * ��ȡѧ����Ϣ�������༭
	 */
	public void getstubyno() {
		String no = getPara("no");
		StudentModel m = new StudentModel();
		List<Document> list = m.find(new Document("no", no));
		setAttr("m", list.get(0));
		renderJson();
	}

	public void updatastu() {

		String no = getPara("no");
		String name = getPara("name");
		String sex = getPara("sex");
		int age = getParaToInt("age");

		StudentModel m = new StudentModel();
		Document data = new Document();
		data.append("no", no);
		data.append("sex", sex);
		data.append("age", age);
		data.append("name", name);
		m.updataOne(data);
		redirect("main");

	}
}
