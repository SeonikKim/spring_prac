package spring_learning;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

//Spring Controller + View 기초

//@Controller : 해당 일반 class를 web에서 작동할 수 있도록 변경하도록 함
@Controller
public class mainpage {
	
	PrintWriter pw = null;
	//@GetMapping == doGet, @PostMapping == doPost, @RequestMapping == doService
	//throws + HttpServletRequest + HttpServletResponse (View 사용 X) => 바로 값 전달
	@GetMapping("/abc.do")
	public void abc(HttpServletRequest rq, HttpServletResponse rp)throws Exception {
		rp.setContentType("text/html;charset=utf-8"); // 이건 넣어줘야 안깨짐.. 프론트 값 rq.~ 는 안해도됨..
		this.pw = rp.getWriter();
		this.pw.write("<script>alert('테스트 페이지 입니다.');</script>");
		this.pw.close();
		System.out.println("ABC페이지");
	}
	
	@PostMapping("/bbb.do")//아무것도 안붙임 -> 무조건 뷰로 전달(bbb.jsp가 없으면 404)
	public void bbb(HttpServletRequest rq) {//void는 같은 이름으로 사용해야함
		//FE값을 받음
		String pdnm = rq.getParameter("pdnm");
		//View(bbb.jsp)로 값을 보냄
		rq.setAttribute("pdnm", pdnm);
//		System.out.println("BBB페이지");
	}
	
	//return형태의 메서드는 view파일명을 다르게 사용할 수 있다.
	//기본은 return null(컨트롤(do) 이름과 동일한 jsp를 찾게됨.)
	//return "" (컨트롤(do)이름과 다른 jsp를 사용 가능)
	@PostMapping("/ccc.do")
	public String ccc(HttpServletRequest rq) {
		String pdnm = rq.getParameter("pdnm");
		rq.setAttribute("pdnm", pdnm);
		return "/product_list";
	}
	
	//request로 view(jsp)로 전달방식 아님
	@PostMapping("/ddd.do")
	public ModelAndView ddd(HttpServletRequest rq) {
		
		String pdnm = rq.getParameter("pdnm");
		String pcode = rq.getParameter("pcode");
		String pmoney = rq.getParameter("pmoney");
		//ModelAndView(Object자료형) : 배열
		ModelAndView mv = new ModelAndView();
		mv.addObject("pdnm",pdnm); //addObject : 키 배열 형태로 값을 저장시킴
		mv.addObject("pcode",pcode);
		mv.addObject("pmoney",pmoney);
		//setView : null은 Mapping 이름과 동일한 jsp를 찾게됩니다.
//		mv.setView(null);
		
		//Mapping과 다른 이름을 사용하고 싶을 경우
		mv.setViewName("bbb");
		
		return mv; // 무조건 ModelAndView 객체명을 사용해야함
	}
	
	@PostMapping("/eee.do")
	public String eee(HttpServletRequest rq, Model m) {
		String pdnm = rq.getParameter("pdnm");
		String pcode = rq.getParameter("pcode");
		String pmoney = rq.getParameter("pmoney");
		
		//Model(interface)을 이용하여 jsp로 값을 전달하는 방식(jstl형태로 값 출력)
		m.addAttribute("pdnm",pdnm);
		m.addAttribute("pcode",pcode);
		m.addAttribute("pmoney",pmoney);
		return "ddd";
	}
	
	
	
}
