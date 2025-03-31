package spring_learning;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;



public interface macbook_Service {	
	public int macbook_insert(macbook_DTO dto);
	List<macbook_DTO> macbook_select();
	public int macbook_delete(int midx);
}
