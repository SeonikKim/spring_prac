package spring_learning;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mail_controller {
	//정상적인 정보를 넣어야합니다!!!!!!!!!!!!!!!!!!!!!!
	@PostMapping("/contactusok.do")
	public String contactusok(@RequestParam String subject, @RequestParam String mname, @RequestParam String memail,
			@RequestParam String mtext) throws Exception {
		//Map =>Properties(.ini) => 환경설정 파일 형태의 배열
		//smtp, imap, pop3 정도는 알아보자 .. 정처기에도 나오니까
		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.naver.com");//메일 발송 서버
		props.put("mail.smtp.host", "smtp.mail.nate.com");//메일 발송 서버
		props.put("mail.smtp.port", "587");//메일 발송 서버 포트
		props.put("mail.smtp.auth", "true");//메일 발송 서버의 인증(true=> id, pw 확인 후 발송(2차보안은 해지해야함))
		props.put("mail.smtp.starttls.enable", "true");//메일 서버의 TLS를 연결(TLS :Transport Layer Security 데이터 암호화 하여 안전하세 전송 )
//		props.put("mail.smtp.ssl.trust", "smtp.naver.com");//메일서버의 SSL 기능 사용
		props.put("mail.smtp.ssl.trust", "smtp.mail.nate.com");//메일서버의 SSL 기능 사용
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");// TLS 버전 세팅
		
		
		//아래의 세션은 메일세션이고, 메일 발송에 대한 로그인(메일서버 로그인 정보) 사항을 Session으로 등록시킴.
		Session session = Session.getInstance(props,new Authenticator() {
			//로그인 할 아이디, 패스워드 정보를 입력
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("dev_seonik@nate.com", "tjgmlditkfkdgo1!");
			}
			
		});
		
		//Mime : 이메일 전송을 위한 인터넷 표준 포멧
		try {//메일 내용을 발송하는 부분
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(memail,mname,"utf-8")); //송신 세팅(메일, 보낸사람)
			msg.addRecipient(Message.RecipientType.TO , new InternetAddress("dev_seonik@nate.com"));//수신 세팅
			msg.setSubject(subject); //메일 제목
			msg.setContent(mtext,"text/html;charset=utf-8"); // 메일 내용
			
			Transport.send(msg);//메일 송신 메서드~ 
			
		} catch (Exception e) {//메일 발송에 대한 서버 접근오류 발생 시 출력
			e.printStackTrace();//메일 오류 추적용
		}
		
		return null;
	}

}
