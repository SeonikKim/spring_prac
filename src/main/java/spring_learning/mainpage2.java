package spring_learning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class mainpage2 {
	
	/*
	 
	 DTO로 Front-End의 값을 받을 수 있습니다(lombok)
	 별도의 값을 받아서 처리해야할 경우는 Servlet 형태의 HttpServletRequest로 받으면 됨
	 ****Font의 name값과 동일하게 DTO가 작성되어야 함.
	 
	 DTO활용 : Front-End 값 이관, Model에 값을 이관, DB에서 사용
	 
	 */
	@GetMapping("/login.do")
	public String login(user_DTO dto,HttpServletRequest rq, Model m) {
		//WEB-INF : Controller, Model만 접근할 수 있는 디렉토리
		//return 사용 시 "WEB-INF/디렉토리명/파일명" 형태로 구성하게 됨.
		String chk = rq.getParameter("mcheck");
		System.out.println(chk);
//		System.out.println(dto.getMid());
//		System.out.println(dto.getMpass());
		System.out.println(dto.getMemail());
		//Model로 해당 jsp에 변수를 이관함 jstl 변수선언으로 출력
		m.addAttribute("mid",dto.getMid());
		return "WEB-INF/view/login";
	}
	
	/*
	 @Autowired : Java에서 사용하는 Class 또는 Interface의 값을 xml에 있는 id 기준으로 대체하는 형태(의존성 주입)
	 */
	@Autowired
	BasicDataSource dbinfo;
	
	//DB Query문 작성 및 데이터를 가져오기 위한 interface
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	//DB + XML + Connection + Controller
	@GetMapping("/event_list.do")
	public String event_list(HttpServletRequest rq) {
		try {
			//db_config.xml에 있는 정보를 Connection으로 연결
			this.con = this.dbinfo.getConnection();
//			System.out.println(this.con);
			String sql = "select * from event order by eidx desc";
			this.ps= this.con.prepareStatement(sql);
			this.rs = this.ps.executeQuery();
			rq.setAttribute("rs", this.rs); //ResultSet을 통쨰로 JSP 전송
			//단점 : this.ps, this.rs close를 못함(View 작동 x)
			
			
		} catch (Exception e) {

		} finally {

		}

		return null;
	}
	//@RequestMapping : GET,POST,PUT ... 모든 통신을 다 받을 수 있음(기본)
	/*
	 value 속성 : 
	 method 속성 : 통신방법(Front-End 데이터 이관 방법)
	 */ 
	@RequestMapping(value="/event_infook.do",method = RequestMethod.POST)
	public String eventok(event_DTO dto) {
//		System.out.println(dto.getEmail());
//		System.out.println(dto.getEtel());
		
		//원래는 Model로 빼야함
		try {
			this.con = this.dbinfo.getConnection();
			String sql = "insert into event values('0',?,?,?,?,?,?,now())";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, dto.getEname());
			this.ps.setString(2, dto.getEtel());
			this.ps.setString(3, dto.getEmail());
			this.ps.setString(4, dto.getInfo1());
			this.ps.setString(5, dto.getInfo2());
			this.ps.setString(6, dto.getEmemo());
			int result = this.ps.executeUpdate();
			System.out.println(result);
			
			
					
		} catch (Exception e) {
			
		}finally {
			try {
				this.ps.close(); //con은 자동 close 설정해둬서 ps만 닫음
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return null;
	}

}
