package whispy_server.web.whispy.whispy_web_be.domain.bug.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whispy_server.web.whispy.whispy_web_be.domain.admin.facade.AdminFacade;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.BugReport;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.repository.BugReportRepository;
import whispy_server.web.whispy.whispy_web_be.domain.bug.facade.BugReportFacade;
import whispy_server.web.whispy.whispy_web_be.domain.bug.service.admin.event.BugAnsweredEvent;
import whispy_server.web.whispy.whispy_web_be.global.email.MailSenderUtil;

@Service
@RequiredArgsConstructor
public class AnswerBugReportService {
    private final BugReportFacade bugReportFacade;
    private final AdminFacade adminFacade;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void answerAndDelete(Long id, String answer){
        adminFacade.getCurrentAdmin();
        BugReport bugReport = bugReportFacade.findByBugReport(id);

        eventPublisher.publishEvent(new BugAnsweredEvent(bugReport, answer));

    }
}
