package spring_learning;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class test implements Controller{
	//Controller에서는 html을 불러오지 못함
	@Override
	public ModelAndView handleRequest(HttpServletRequest rq, HttpServletResponse rp) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		
		
		String search = rq.getParameter("search");
		System.out.println(search);
//		System.out.println("연습 Spring Controller");
		mv.setViewName("search.jsp");
		
		return mv;
	}
}
