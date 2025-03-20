package spring_learning;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class macbook {
	@PostMapping("macbook_ok.do")
	public String macbook_ok(macbook_DTO dto) {
		System.out.println(dto.getClass_name());
		return "load";
	}
}
