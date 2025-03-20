package spring_learning;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

// I/O Controller

@Controller
public class mainpage3 {
	
	//MultipartFile : Spring 전용 I/O, xml과 연결되어있으며, 
	@PostMapping("/fileok.do")
	public String fileupload(MultipartFile mfile) {
		if(mfile.getSize()>2097152) {
			System.out.println("test");
		}
		System.out.println(mfile.getOriginalFilename());
		return "load";
	}
	//여러개의 첨부파일을 받는 메서드
	/*
	 MultipartFile[] : Interface로 파일을 Front-end에서 받을 경우 반복문으로 처리 시 multiple로 전송할 경우는 별도의 조건문 없이 저장 가능
	 단, multiple 없이 같은 name으로 여러개의 파일을 받을 경우 반복문 처리 시 조건문이 없을 경우 500번 에러로 인하여 조건문으로 필터링 해야함...
	 
	 FileCopyUtils.copy : 파일 전송 관련된 I/O 이며, Spring, Spring-boot에서 사용됨
	 */
	@PostMapping("/fileok2.do")
	public String fileupok(MultipartFile[] mfile,HttpServletRequest rq)throws Exception {
		String url = rq.getServletContext().getRealPath("/upload/");
//			System.out.println(url);//경로체크	
		
		int w = 0;
		while (w<mfile.length) {
			FileCopyUtils.copy(mfile[w].getBytes(),new File(url+mfile[w].getOriginalFilename()));  //스프링 전용 파일저장 // IO니까 throws Exception 추가..
			w++;
		}
		
//	public String fileipok(file_DTO dto) { //이런식으로 받아오는것도 가능하나 뒤에서 다시 multipartfile을 사용해서 갱신해야해서 사용은 안함..
//		System.out.println(dto.getMfile());
//	public String fileipok(abc_DTO dto, MultipartFile[] mfile) {//이렇게 dto따로, 파일 따로 받는게 더 나음
		return "load";
	}
	
	//웹 디렉토리에 있는 파일 리스트를 출력하는 Controller
	@GetMapping("/filelist.do")
	public String filelist(HttpServletRequest rq)throws Exception {
		//웹 디렉토리
		String url = rq.getServletContext().getRealPath("/upload/");
		//웹 디렉토리에 있는 모든 파일 명 담을 배열
		File f = new File(url);
		String f_list[] = f.list();
//		System.out.println(f_list[0]);//0번째 파일 출력
//		System.out.println(f_list.length);//파일 수 출력
		ArrayList<String> fnms = new ArrayList<String>(Arrays.asList(f_list));
		
		rq.setAttribute("fnms", fnms);
		
		
		return null;
	}
	
	//@RequestParam : Front-end 전달된 값 request.getParameter()
	@PostMapping("/filedel.do")
	public String filedel(String fnm, HttpServletRequest rq, Model m)throws Exception {//@RequestParam("fnm")  이 생략된거임
		String url = rq.getServletContext().getRealPath("/upload/");
//		System.out.println(fnm);
		File f = new File(url + fnm);
		f.delete();//파일삭제 메서드
		String msg = "alert('정상적으로 삭제 되었습니다.'); location.href='./filelist.do';";
		m.addAttribute("msg",msg);
		return "load";
	}
	
	//JSTL로 로드 후 값 전달
	@GetMapping("/jstl/jstl6.do")
	public String jstl6(Model m) {
		//Model을 이용하여 jstl6.jsp로 값을 전달함.
		//출력 top.jsp에서 ${} 변수를 출력함
		String level = "일반수강생";
		m.addAttribute("level",level);
		
		String corp ="(주)선익";
		String tel ="010-1234-2222";
		String ceo ="김선익";
		String sec ="김선익";
		m.addAttribute("corp",corp);
		m.addAttribute("tel",tel);
		m.addAttribute("ceo",ceo);
		m.addAttribute("sec",sec);
		return null;		
	}
	
}
