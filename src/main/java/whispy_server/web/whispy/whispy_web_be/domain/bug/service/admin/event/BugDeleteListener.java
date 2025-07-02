package whispy_server.web.whispy.whispy_web_be.domain.bug.service.admin.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.repository.BugReportRepository;

@Component
@RequiredArgsConstructor
public class BugDeleteListener {
    private final BugReportRepository bugReportRepository;

    @TransactionalEventListener
    public void handleBugAnswered(BugAnsweredEvent event){
        bugReportRepository.delete(event.bugReport());
    }
}
