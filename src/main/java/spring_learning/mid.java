package spring_learning;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

//@SessionAttributes("uid") 사용 근데 그냥 알고만 있으면 됩니다 ~ DTO형태로 또로로로록 넣으면 됨 나중에 보여주신대요
public class mid {
	@ModelAttribute("mid")
	public macbook_DTO mid_data(HttpSession se) {
		return null;
	}
}
