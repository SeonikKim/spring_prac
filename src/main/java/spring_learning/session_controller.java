package spring_learning;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

//Spring Session 사용법!
@Controller

/*@SessionAttributes : 해당 세션이 생성되었을 경우 모든 메서드에 세션값을 Model로 전송가능
(Controller에서 세팅된값이며, DTO(세션 형태의 DTO)가 있어야 정상적으로 핸들링 됨 => API Server에서 주로 사용) 
*/
//@SessionAttributes("uid")
public class session_controller {

	//Session을 의존성 주입형태로 Interface를 필드에 선언하여 모든 메서드에 사용 가능하도록
	//@Autowired 를 사용해서 사용도 가능함
	@Autowired
	HttpSession hs;
	
	@GetMapping("/session1.do")
//	서블릿 방식
//	public String session1(HttpServletRequest rq) {
//		HttpSession se = rq.getSession();
//	return null;
//	}

	public String session1(HttpSession se) { //스프링 방식 HttpSession : HttpServletRequest와 동일함
		String uid = "kim";
		se.setAttribute("uid", uid);
		return null;
	}
	
	//해당 세션을 생성 후 문자열 변수로 변환하여 Model로 전달 => jsp에 출력
	//HttpSession은 Controller, DAO, DTO, VO에도 다 사용됨
	//세션체크 ver1
	@GetMapping("/session2.do")
	public String session2(HttpSession se,Model m) {
		String id = (String)se.getAttribute("uid");
		/*
		System.out.println(id);
		*/
		
		m.addAttribute("uid",id);
		return "session";
	}
	//세션체크 ver2
	//@SessionAttribute = session.getattrubute와 동일 
	//사용 시 주의사항** 세션이 없는(null) 경우 오류 발생, (name="uid",required = false) 와 같이 옵션값을 넣어주면 된다
	@GetMapping("/session3.do")
	public String session3(@SessionAttribute(name="uid",required = false)String mid) {
		String test = (String)this.hs.getAttribute("uid");
		System.out.println(test);
		return null;
	}
	//세션 삭제
	@GetMapping("/session4.do")
	public String session4() {
		this.hs.invalidate(); // 필드에 올려놓은 Session을 로드하여 세션 초기화, 특정 세션을 핸들링할 때에는 매개변수에 HttpSession, 세션을 불러와야할 경우가 많다면 Autowired에 HttpSession!!!
		this.hs.removeAttribute("mid"); //Session의 특정 키만 삭제
		String test = (String)this.hs.getAttribute("uid");
		System.out.println(test);
		return null;
	}
}
