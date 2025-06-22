package whispy_server.web.whispy.whispy_web_be.domain.bug.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whispy_server.web.whispy.whispy_web_be.domain.admin.facade.AdminFacade;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.repository.BugReportRepository;
import whispy_server.web.whispy.whispy_web_be.domain.bug.presentation.dto.response.BugReportResponseDto;
import org.springframework.data.domain.Pageable;


@Service
@RequiredArgsConstructor
public class QueryBugReportListAdminService {
    private final AdminFacade adminFacade;
    private final BugReportRepository bugReportRepository;

    @Transactional(readOnly = true)
    public Page<BugReportResponseDto> getBugReportList(Pageable pageable){
        adminFacade.getCurrentAdmin();
        return bugReportRepository.findAll(pageable).map(report -> BugReportResponseDto.builder()
                .id(report.getId())
                .title(report.getTitle())
                .bugTopic(report.getBugTopic().getDescription())
                .content(report.getContent())
                .email(report.getEmail())
                .createdAt(report.getCreatedAt())
                .build());
    }

}
