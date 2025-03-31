package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//DAO : 데이터를 Access를 하는 역할

@Repository("macbook_DAO") // @Repository : Model을 Controller에서 호출할 수 있게 해줌 dto도 가능함 Resource<->Repository는 서로 짝임.
public class macbook_DAO implements macbook_mapper {//직접 추가하기 귀찮으면  implements macbook_mapper
	
	
	//Mybatis로 DB연결
	@Resource(name = "template")
	public SqlSessionTemplate st;
	
	@Override
	public int macbook_delete(int midx) {
		int result = this.st.delete("macbook_delete",midx);
		return result;
	}
	@Override
	public List<macbook_DTO> macbook_select(){
		
		//selectOne : 데이터 한 개만 가져올 때 (List배열, ArrayList, DTO)
		//selectList : 데이터 여러개 가져올 때 (List배열로 가져옴)
		List<macbook_DTO> classList = this.st.selectList("macbook_select");
		
		return classList;
		
	}
	//데이터 수정 메서드
	public int macbook_update(macbook_DTO dto) {
		int result = this.st.update("macbook_update",dto);
		System.out.println("Update 결과 : "+result);
		return result;
	}
	
	
	//하나의 데이터만 가져오는 메서드
	@Override
	public macbook_DTO macbook_one(String midx){
		//setter형태로 DB에 있는 데이터를 이관
		//selectOne("mapper.xml에서 사용하는 id명", 매개변수)
		macbook_DTO onedata = this.st.selectOne("macbook_one",midx);
		return onedata;
	}
	@Override
	public int macbook_insert(macbook_DTO dto) {//값 넣는 dao
		int result = this.st.insert("macbook_insert",dto);
		System.out.println("Insert 결과: " + result);
		return result;
	}

}
