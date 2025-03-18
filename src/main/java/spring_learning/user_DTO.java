package spring_learning;

import lombok.Getter;
import lombok.Setter;
//lombok : pom.xml에 라이브러리 등록하면 @Setter, @Getter 등 사용 가능
// 단 lombok.jar 미설치 시 메서드가 만들어지지 않음 => 다른 환경에서 코드 가져가도 안됨 lombok 설치 해야함 Ctrl+O 눌러서 확인 해야함
@Setter
@Getter
public class user_DTO {
	String userid, username;
	
}
