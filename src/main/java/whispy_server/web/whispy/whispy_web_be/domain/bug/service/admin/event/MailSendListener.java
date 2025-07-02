package whispy_server.web.whispy.whispy_web_be.domain.bug.service.admin.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import whispy_server.web.whispy.whispy_web_be.global.email.MailSenderUtil;

@Component
@RequiredArgsConstructor
public class MailSendListener {
    private final MailSenderUtil mailSenderUtil;

    @Async
    @EventListener
    public void handleBugAnswered(BugAnsweredEvent event){
        mailSenderUtil.send(
                event.bugReport().getEmail(),
                "[Whispy] 버그 문의 답변입니다!",
                event.answer()
        );
    }
}

