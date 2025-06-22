package whispy_server.web.whispy.whispy_web_be.domain.bug.service.user.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import whispy_server.web.whispy.whispy_web_be.global.webhook.discord.DiscordMessage;
import whispy_server.web.whispy.whispy_web_be.global.webhook.discord.DiscordMessageProvider;

@Slf4j
@RequiredArgsConstructor
@Component
public class BugReportDiscordNotifier {
    private final DiscordMessageProvider discordMessageProvider;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendDiscordMessage(BugReportCreatedEvent event){
        var report = event.bugReport();

        String message = """
        🐞 **새로운 버그 리포트가 도착했어요!**

        **📌 제목:** %s

        **📂 카테고리:** %s

        **📝 내용:**
        >>> %s

        **📧 이메일:** `%s`
        """.formatted(
                report.getTitle(),
                report.getBugTopic().getDescription(),
                report.getContent(),
                report.getEmail()
        );
        try{
            discordMessageProvider.sendMessage(new DiscordMessage(message));
        } catch (Exception e){
            log.error("❌ Discord 메시지 전송 실패", e);
        }
    }
}
