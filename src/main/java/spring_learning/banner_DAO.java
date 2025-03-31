package spring_learning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("banner_DAO")
public class banner_DAO {
	
	@Resource(name = "template")
	public SqlSessionTemplate st;
	Integer page_ea = 5; //한 페이지당 출력될 게시물 수
	
	public Integer banner_total() {
		Integer total = this.st.selectOne("macbook_user.banner_total") ;
		System.out.println(total);
		return total;
	}
	
	//신규 배너 등록 메서드
	public int new_banner(banner_DTO dto) {
		int result = this.st.insert("macbook_user.banner_new",dto);
		
		return result;
	}
	
	//배너 전체 리스트 가져오기 + 페이지네이션
	//Integer spage(매개변수) : Controller에서 사용자가 클릭한 페이지 번호
	public List<banner_DTO> all_banner(Integer spage){
		//limit 핸들링 하기 위한 Map형태로 구성하여 Mapper로 전달
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("spage", (spage-1)*5);//limit 첫 한목
		data.put("epage", this.page_ea);//limit 두번째 항목
		List<banner_DTO> all = this.st.selectList("macbook_user.banner_all",data);
		return all;
	}
	
	//배너 검색(배너명)
	public List<banner_DTO> search_banner(String search){
		List<banner_DTO> all = this.st.selectList("macbook_user.banner_search",search);
		return all;
	}
	//배너 삭제
	public int banner_del(String no) {
		int result = this.st.delete("macbook_user.banner_del",no);
		return result;
	}

}
