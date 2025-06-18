package whispy_server.web.whispy.whispy_web_be.global.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import whispy_server.web.whispy.whispy_web_be.global.email.exception.EmailSendFailedException;

@Component
@RequiredArgsConstructor
@Slf4j
public class MailSenderUtil {
    private final JavaMailSender mailSender;

    public void send(String to, String subject, String content){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            mailSender.send(message);
        } catch (EmailSendFailedException e){
            log.error("메일 전송 실패: to={}, subject={}, reason={}", to, subject, e.getMessage(), e);
            throw e;
        }
    }
}
