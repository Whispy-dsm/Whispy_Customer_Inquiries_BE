package whispy_server.web.whispy.whispy_web_be.domain.bug.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whispy_server.web.whispy.whispy_web_be.domain.admin.facade.AdminFacade;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.BugReport;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.repository.BugReportRepository;
import whispy_server.web.whispy.whispy_web_be.domain.bug.facade.BugReportFacade;
import whispy_server.web.whispy.whispy_web_be.domain.bug.presentation.dto.response.BugReportResponseDto;

@Service
@RequiredArgsConstructor
public class QueryBugReportAdminService {
    private final AdminFacade adminFacade;
    private final BugReportFacade bugReportFacade;

    @Transactional(readOnly = true)
    public BugReportResponseDto getBugReport(Long id){
        adminFacade.getCurrentAdmin();
        BugReport bugReport =  bugReportFacade.findByBugReport(id);

        return BugReportResponseDto.builder()
                .id(bugReport.getId())
                .title(bugReport.getTitle())
                .bugTopic(bugReport.getBugTopic().getDescription())
                .content(bugReport.getContent())
                .email(bugReport.getEmail())
                .createdAt(bugReport.getCreatedAt())
                .build();
    }

}
