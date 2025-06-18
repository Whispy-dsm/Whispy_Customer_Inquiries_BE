package whispy_server.web.whispy.whispy_web_be.domain.bug.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.BugReport;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.repository.BugReportRepository;
import whispy_server.web.whispy.whispy_web_be.domain.bug.presentation.dto.request.BugReportRequestDto;
import whispy_server.web.whispy.whispy_web_be.global.webhook.discord.DiscordMessage;
import whispy_server.web.whispy.whispy_web_be.global.webhook.discord.DiscordMessageProvider;

@Service
@RequiredArgsConstructor
public class CreateBugReportService {
    private final BugReportRepository bugReportRepository;
    private final DiscordMessageProvider discordMessageProvider;

    @Transactional
    public Long createBugReport(BugReportRequestDto dto){
        BugReport bugReport = BugReport.create(dto.title(), dto.content(), dto.bugTopic(), dto.email());
        bugReportRepository.save(bugReport);

        String message = """
        🐞 **새로운 버그 리포트가 도착했어요!**
    
        **📌 제목:** %s
        
        **📂 카테고리:** %s
        
        **📝 내용:**
        >>> %s
    
    
        **📧 이메일:** `%s`
        """.formatted(
                    dto.title(),
                    dto.bugTopic().getDescription(),
                    dto.content(),
                    dto.email()
            );

        discordMessageProvider.sendMessage(new DiscordMessage(message));
        return bugReport.getId();
    }
}
