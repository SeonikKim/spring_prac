package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import etc_model.md5_pass;

//md5_pass : abstract(추상클래스 이며, 해당 Controller에 직접 핸들링하여 사용 가능함.)
@Controller
//public class macbook_controller extends md5_pass {
	public class macbook_controller  {
	@Resource(name="macbook_member_DTO")
	public macbook_member_DTO dto;
	
	@Resource(name="user_DAO")
	public user_DAO dao;
	
	
//	@Resource(name = "md5_pass")
	private md5_pass md;
	
	
	//MD5로 데이터를 변환하는 형태의 Controller
	@GetMapping("/macbook_login.do")
	public String macbook_login() {
		String pw = "a123456";
		//@resource형태
		String result = this.md.md5_make(pw);
		
		//abstract형태
//		String result = this.md5_make(pw);
		System.out.println(result);
		return null;
	}

	//전체 데이터 리스트 가져오기(회원정보)
	@GetMapping("/macbook_user.do")
	public String macbook_user() {
//		System.out.println("test");
		List<macbook_member_DTO> all = this.dao.all_list();
		System.out.println(all.get(0).memail);
		return null;
	}
	
	//아이디찾기 (체크박스 : 체크 => Y, 체크X => N 처리)
	/*
	 @RequestParam : DTO에 없는 name값을 처리할 때 주로 사용함.
	 defaultValue : null name값이 전송되었을 경우 발동되는 속성
	 required(true) : 필수로 무조건 name을 처리하게 함
	 required(false) : Front에서 name값을 보내지 않아도 처리가 되도록 설정
	 */
	@PostMapping("/idsearch.do")
	public String idsearch(macbook_member_DTO dto,@RequestParam(defaultValue = "N",required = false) String mcheck, Model m) {
//		System.out.println(dto.memail);
//		System.out.println(mcheck);
		
		macbook_member_DTO data = this.dao.user_search(dto.mname, dto.memail);
//		System.out.println(data);
		String msg = ""; //결과 관련 사항
		if(data==null) {
			msg= "일치하는 아이디가 없습니다.";
		}else {
			msg = data.mid;
		}
		
		m.addAttribute("msg",msg);
		return "/WEB-INF/info/idsearch";
		
	}
}
