package spring_learning;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Mapper에 Interface와 macbook_Service의 Interface를 연결해주는 역할을 함.
@Service
public class macbook_ServiceImp implements macbook_Service {

	
//	@Autowired
//	private macbook_mapper mm;
//	
//	public String selectnow() {
//		return mm.selectnow();
//	}
	
	
	@Override
	public int macbook_insert(macbook_DTO dto) {
		return 0;
	}

	@Override
	public List<macbook_DTO> macbook_select() {
		return null;
	}

	@Override
	public int macbook_delete(int midx) {
		return 0;
	}


}
