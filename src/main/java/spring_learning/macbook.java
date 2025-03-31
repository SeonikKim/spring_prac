package spring_learning;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class macbook {

	//@Autowired , @Inject : 의존성 주입하기 위해 사용(XML -> Java, Java->XML 정보를 서로 사용할 수 있게 해줌)
//	@Inject
//	SqlSessionFactory sqlfact;//얘는 지금 ibatis임, factorybean은 mybatis

//	@Autowired(required = false)
//	macbook_ServiceImp si;
	
	@Resource(name = "macbook_DAO")//new 클래스 호출과 동일하게 작동함.,DAO @Repository 이름과 동일하게 해야함.. 
	private macbook_DAO dao;
	//과정 리스트 출력
	@GetMapping("/macbook_list.do")
	public String macbook_list(Model m) {
		//List<macbook_DTO> : DTO형태의 배열로 생성하여, JSP로 전달
		List<macbook_DTO> classList = this.dao.macbook_select();
//		System.out.println("사이즈 : "+classList.size());
//		System.out.println(classList.get(0).class_name);
		m.addAttribute("ea",classList.size());
		m.addAttribute("classList",classList);
		return null;
	}
	
	
	//과정 수정 작동
	@PostMapping("/macbook_modifyok.do")
	public String macbook_modifyok(macbook_DTO dto, Model m) {
		//insert, update, delete 무조건 결과를 int로 받음
		
		int result = this.dao.macbook_update(dto);//DAO로 값을 전송
		System.out.println(result);
		String msg = "";
		if(result>0) {
			 msg = "alert('정상적으로 데이터가 수정되었습니다.'); location.href='./macbook_list.do';";
		}
		m.addAttribute("msg",msg);
		return "load";
	}
	
	
	//과정 수정 페이지(출력)
	@PostMapping("/macbook_modify.do")
	public String macbook_modify(@RequestParam("midx") String midx, Model m) { //이름 동일하면 굳이 @RequestParam("")안해도됨, 배열은 @RequestBody
//		System.out.println(midx);
		//DTO의 setter에 값을 이관한 상황
		macbook_DTO onedata = this.dao.macbook_one(midx);
//		System.out.println(onedata.class_name); //DTO의 getter 메서드를 호출
		m.addAttribute("onedata",onedata);//JSTL로 값을 이관
		return null;
	}
	
	
	PrintWriter pw = null;
	
	//과정 삭제
	@PostMapping("/macbook_delete.do")
	public String macbook_delete(@RequestParam("midx") String midx,HttpServletResponse rs)throws Exception {
//		System.out.println(midx);//값 넘어오는지 확인
		rs.setContentType("text/html; charset=utf-8"); //서블릿으로 찍으니까 언어세팅도 해야함..
//		pw가 있으면 jsp로 안넘어감..
//		=> Model과 HttpServletResponse는 함께 사용하지 못합니다. 두개의 Interface 역할이 같으므로 하나만 사용이 가능합니다..
		this.pw = rs.getWriter();
		int result = this.dao.macbook_delete(Integer.parseInt(midx));
		if(result>0) {
			this.pw.print("<script>alert('올바르게 해당 과정을 삭제하였습니다.'); location.href='./macbook_list.do'</script>");
		}
		this.pw.close();		
		
		
		return null;
	}
	
	//과정 생성
	@PostMapping("/macbook_ok.do")
	public String macbook_ok(macbook_DTO dto, Model m) throws Exception {
//		System.out.println(dto.getClass_name());
		try {

			int result = this.dao.macbook_insert(dto);
//			System.out.println(result);
			String msg = "";
			if(result > 0) {
				msg = "alert('과정 개설이 올바르게 완료되었습니다.'); location.href='./macbook_list.do';";
			}
			m.addAttribute("msg",msg);
			

		}catch (Exception e) {
			
		}
		
		

		
//		System.out.println(this.sqlfact);

		return "load";
	}
}
