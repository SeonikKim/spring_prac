package spring_learning;

import org.springframework.stereotype.Repository;

import lombok.Data;

//@Data = @Getter + @Setter
//DTO 생성 시 무조건 config.xml에 추가
@Data
@Repository("banner_DTO")
public class banner_DTO {
	int bidx;
	String file_url,file_new,file_ori,bname,bdate;

}
