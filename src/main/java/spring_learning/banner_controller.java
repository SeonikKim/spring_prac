package spring_learning;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class banner_controller {
	List<String> listdata = null;
	Map<String, String> mapdata = null;
	PrintWriter pw = null;
	String result = null;
	int callback = 0;
	ModelAndView mv = null;

	@Resource(name = "banner_DTO")
	banner_DTO dto;

	@Resource(name = "banner_DAO")
	banner_DAO dao;

	@Resource(name = "file_rename")
	file_rename fname; // 파일명을 개발자가 원하는 형태로 변경

	// @ModelAttribute : 1:1매칭 => name과 DTO 자료형 변수가 같은것이 있으면 무조건 setter 발동
	@PostMapping("/banner/bannerok")
	public String bannerok(@ModelAttribute(name = "dto") banner_DTO dto, MultipartFile bfile, HttpServletRequest rq) throws Exception {
//		if(bfile.getOriginalFilename() !="") //이케해도 됨
		String file_new = "";
		if (bfile.getSize() > 0) {
			//파일명 dto에 넣기~
			//실수로 this.dto로 하면 매개변수 dto가 아닌 필드의 dto에 값이 들어가기 때문에 실수 주의 **
			file_new =  this.fname.rename(bfile.getOriginalFilename());
			dto.setFile_ori(bfile.getOriginalFilename());//사용자가 업로드한 파일명
			dto.setFile_new(file_new);//개발자가 다시만든 파일명		
			dto.setFile_url("/upload/"+file_new); //웹디렉토리 경로 + 파일명
			
			//파일 저장 ~
			String url = rq.getServletContext().getRealPath("/upload/");
//			System.out.println(url); //경로확인
			FileCopyUtils.copy(bfile.getBytes(),new File(url+file_new));//새로지은 이름으로 저장
		}
		this.callback = this.dao.new_banner(dto);
		System.out.println(this.callback);
		return null;
	}
	//검색은 필수조건이 아니며 또한 null처리가 되었을 경우 defaultValue 값을 공백처리
	@GetMapping("/banner/bannerlist")
	public String bannerlist(Model m ,@RequestParam(defaultValue = "", required = false)String search,
			@RequestParam(defaultValue = "1",required = false)Integer pageno) {
		//데이터 총 갯수 확인
		Integer total = this.dao.banner_total();
//		System.out.println(total);
		
		int userpage = 0; //사용자가 클릭한 페이지 번호에 맞는 순차번호 계산 값
		if(pageno==1) {
			userpage = 0;
		}else {
			userpage = (pageno-1) * 5;
			
		}
		List<banner_DTO> all = null;
		if(search.equals("")) { //검색어가 없을 경우
			all = this.dao.all_banner(pageno); // 인자값 : 사용자가 누른 페이지 번호
		}else { //검색어가 있을 경우
			all = this.dao.search_banner(search);
		}
		m.addAttribute("total",total);//데이터 전체 수
		m.addAttribute("search",search);//검색어 전달
		m.addAttribute("all",all);
		m.addAttribute("userpage",userpage);//해당 일련번호를 계산하여 jsp에 전달
		
		return null;
	}
	@PostMapping("/banner/bannerdel")
	public String bannerdel(@RequestParam(defaultValue = "", required = false)String ckdel, Model m) {
		String msg = "";
		if(ckdel.intern() =="") {
			msg = "alert('올바른 접근이 아닙니다.'); location.href='./bannerlist';";
		}else {
			String no[] = ckdel.split(",");
			int w = 0;
			this.callback = 0;
			while(w<no.length) { //Front-end에서 체크된 값만큼 반복
				//+=를 사용해 n개의 삭제가 모두 되는지 확인함.
				this.callback += this.dao.banner_del(no[w]);
				
				w++;
			}
			if(this.callback == no.length) {
				msg = "alert('정상적으로 삭제되었습니다.'); location.href='./bannerlist';";				
			}else {
				msg = "alert('비정상적인 데이터가 확인되었습니다.'); location.href='./bannerlist';";				
			}
		}
		m.addAttribute("msg",msg);
		return "load";
	}

}
