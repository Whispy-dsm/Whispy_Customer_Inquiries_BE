package whispy_server.web.whispy.whispy_web_be.domain.bug.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.BugReport;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.repository.BugReportRepository;
import whispy_server.web.whispy.whispy_web_be.domain.bug.exception.BugReportNotFoundException;

@Component
@RequiredArgsConstructor
public class BugReportFacade {
    private final BugReportRepository bugReportRepository;

    public BugReport findByBugReport(Long id){
        return bugReportRepository.findById(id)
                .orElseThrow(() -> BugReportNotFoundException.EXCEPTION);
    }
}
