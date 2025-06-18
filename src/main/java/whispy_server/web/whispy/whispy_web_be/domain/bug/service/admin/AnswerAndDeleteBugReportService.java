package whispy_server.web.whispy.whispy_web_be.domain.bug.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whispy_server.web.whispy.whispy_web_be.domain.admin.facade.AdminFacade;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.BugReport;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.repository.BugReportRepository;
import whispy_server.web.whispy.whispy_web_be.domain.bug.facade.BugReportFacade;
import whispy_server.web.whispy.whispy_web_be.global.email.MailSenderUtil;

@Service
@RequiredArgsConstructor
public class AnswerAndDeleteBugReportService {
    private final BugReportFacade bugReportFacade;
    private final AdminFacade adminFacade;
    private final BugReportRepository bugReportRepository;
    private final MailSenderUtil mailSenderUtil;

    @Transactional
    public void answerAndDelete(Long id, String answer){
        adminFacade.getCurrentAdmin();
        BugReport bugReport = bugReportFacade.findByBugReport(id);
        mailSenderUtil.send(
                bugReport.getEmail(),
                "[Whispy] 버그 문의 답변입니다!",
                answer
        );

        bugReportRepository.delete(bugReport);
    }
}
