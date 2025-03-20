package spring_learning;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//DTO로 파일 핸들링
public class file_DTO {
	MultipartFile[] mfile;
	
}
