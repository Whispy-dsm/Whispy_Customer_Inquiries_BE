package whispy_server.web.whispy.whispy_web_be.domain.bug.service.admin.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.BugReport;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.repository.BugReportRepository;
import whispy_server.web.whispy.whispy_web_be.domain.bug.exception.BugReportNotFoundException;

@Component
@RequiredArgsConstructor
public class BugDeleteListener {
    private final BugReportRepository bugReportRepository;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleBugAnswered(BugAnsweredEvent event){
        Long bugId = event.bugReport().getId();
        BugReport bugReport = bugReportRepository.findById(bugId)
                        .orElseThrow(() -> BugReportNotFoundException.EXCEPTION);
        bugReportRepository.delete(bugReport);
    }
}
