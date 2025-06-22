package whispy_server.web.whispy.whispy_web_be.domain.bug.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.BugReport;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.repository.BugReportRepository;
import whispy_server.web.whispy.whispy_web_be.domain.bug.presentation.dto.request.BugReportRequestDto;
import whispy_server.web.whispy.whispy_web_be.domain.bug.service.user.event.BugReportCreatedEvent;

@Service
@RequiredArgsConstructor
public class CreateBugReportService {
    private final BugReportRepository bugReportRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Long createBugReport(BugReportRequestDto dto){
        BugReport bugReport = BugReport.create(dto.title(), dto.content(), dto.bugTopic(), dto.email());
        bugReportRepository.save(bugReport);

        eventPublisher.publishEvent(new BugReportCreatedEvent(bugReport)); //커밋 이후에 처리될 이벤트
        return bugReport.getId();
    }
}
