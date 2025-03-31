package spring_learning;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*",allowedHeaders = "*")//Spring, Spring-Boot 방식 CORS 해결
//API 전용 Controller
@RestController
public class api_controller {
	PrintWriter pw = null; //front-end가 값을 가져갈 수 있도록 함
	JSONArray ja2 = null;
	JSONObject jo2 = null;
	JSONObject jo3 = null;
	
	@Resource(name = "macbook_member_DTO")
	macbook_member_DTO dto;
	@Resource(name = "user_DAO")
	user_DAO dao;
	
	//Mysql db 가져와서 API로 생성하기
	@GetMapping("/api_data5.do")
	public String api_data5(HttpServletResponse rs) throws Exception{
		rs.setContentType("text/html;charset=utf-8");
		this.pw=rs.getWriter();
		List<macbook_member_DTO> result = this.dao.all_list();
		System.out.println(result.get(0).mname);
		int w = 0;
		this.jo3 = new JSONObject();
		this.ja2 = new JSONArray();
		
		while (w < result.size()) {
			this.jo2 = new JSONObject();
			this.jo2.put("midx",result.get(w).midx);
			this.jo2.put("mid",result.get(w).mid);
			this.jo2.put("mname",result.get(w).mname);
			this.jo2.put("memail",result.get(w).memail);
			
			this.ja2.put(this.jo2);
			w++;
		}
		this.jo3.put("member",this.ja2);
		this.pw.print(this.jo3);
		this.pw.close();
	return null;
	}
	
	@GetMapping("/api_data4.do")
	public String api_data4(HttpServletResponse rs) throws Exception{
		/*//서블릿 형식 CORS 해결
		rs.addHeader("Access-Control-Allow-Origin", "*");
		rs.addHeader("Access-Control-Allow-Credentials", "true");
		 */
		
		rs.setContentType("text/html;charset=utf-8");
		String[][] db1 = {
				{"모니터","키보드","마우스"},
				{"NEW","BEST","NEW"}};
		this.pw=rs.getWriter();
		int w = 0;
		String keyname= ""; //서브키
		this.jo3=new JSONObject(); //대표키
		this.jo2=new JSONObject();//서브키
		while(w<db1.length) {
			
			this.ja2 = new JSONArray();//데이터배열
			
			for(int f = 0; f<db1[w].length; f++) {
				this.ja2.put(db1[w][f]);
			}
//			System.out.println(this.ja2);
			//데이터 배열에 맞는 보조키 만들려고 if문 함...
			if(w==0) {
				keyname="product_name";
			}else {
				keyname="product_ico";	
			}
			jo2.put(keyname, this.ja2);
			w++;
		}		

		jo3.put("product", jo2); //대표키 생성은 최종 반복문 다음에 코드를 작성함		
		this.pw.print(this.jo3); //Front-End가 출력함 ~ 
		this.pw.close();
		return null;
		
	}
	
	
	
	@GetMapping("/api_data3.do")
	public String api_data3(HttpServletResponse rs) throws Exception{
		rs.setContentType("text/html;charset=utf-8");
		String[] db = {"hong","홍길동","hong@nate.com","서울","01012345678",};
		this.pw=rs.getWriter();
		this.jo2 = new JSONObject();
		this.jo2.put("id", db[0]);
		this.jo2.put("name", db[1]);
		this.jo2.put("email", db[2]);
		this.jo2.put("area", db[3]);
		this.jo2.put("phone", db[4]);
		this.ja2 = new JSONArray();
		this.ja2.put(this.jo2);
		this.jo2 = new JSONObject();
		this.jo2.put("myinfo", ja2);
		
		
		this.pw.print(this.jo2);
		this.pw.close();
		return null;
		
	}
	
	
	@GetMapping("/api_data2.do")
	public String api_data2(HttpServletResponse rs) throws Exception{
		/*
		 [
		 {"멋쟁이":["홍길동","강감찬","유관순","김선익"]}
		 ]	 
		 형식으로 찍기 ~ 
		 */
		rs.setContentType("text/html;charset=utf-8");
		this.pw = rs.getWriter();
		this.ja2 = new JSONArray();
		this.jo2 = new JSONObject();
		this.ja2.put("홍길동");
		this.ja2.put("강감찬");
		this.ja2.put("유관순");
		this.ja2.put("김선익");
		this.jo2.put("멋쟁이", ja2);
		this.ja2 = new JSONArray();
		this.ja2.put(this.jo2);
		
		this.pw.print(this.ja2);//마지막꺼 출력 해주기 ~ 
		
		this.pw.close();
		return null;
	}
	
	@GetMapping("/api_data.do")
	public String api_data(HttpServletResponse rs) throws Exception {
		rs.setContentType("text/html;charset=utf-8");
		this.pw = rs.getWriter();

		/*
		 JSONArray : []
		 JSONObject : {} 키 생성
		 
		 org.json.simple : add
		 org.json : put
		 */
		/*//["a","b","c","d"] 형태 1차원 배열
		JSONArray ja = new JSONArray();
		ja.put("a");
		ja.put("b");
		ja.put("c");
		ja.put("d");
		
		this.pw.print(ja);
		 */
		
		JSONArray ja = new JSONArray();
		ja.put("a");
		ja.put("b");
		ja.put("c");
		ja.put("d");
		JSONObject jo = new JSONObject();
		jo.put("data", ja);
		this.pw.print(jo);
		
		
		this.pw.close();
		return null;
	}
}
