package spring_learning;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

//@Mapper : mapper.xml과 연동하는 Interface입니다.
//*** mapper.xml에서 사용하는 id기준으로 메서드 이름을 설정하게됩니다.
@Mapper
public interface macbook_mapper {
	public int macbook_update(macbook_DTO dto);// 데이터 수정

	public int macbook_insert(macbook_DTO dto);// 신규 데이터 입력

	List<macbook_DTO> macbook_select();// 전체 데이터 출력

	macbook_DTO macbook_one(String midx); // 강의 하나 출력, mapper.xml과 자료형, 파라미터 명 동일하게

	public int macbook_delete(int midx);// 데이터 삭제(숫자값으로 처리)
}
