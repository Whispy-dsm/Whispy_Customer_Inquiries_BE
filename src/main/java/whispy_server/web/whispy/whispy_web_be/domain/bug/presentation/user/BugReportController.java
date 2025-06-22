package whispy_server.web.whispy.whispy_web_be.domain.bug.presentation.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import whispy_server.web.whispy.whispy_web_be.domain.bug.presentation.dto.request.BugReportRequestDto;
import whispy_server.web.whispy.whispy_web_be.domain.bug.service.user.CreateBugReportService;

@RestController
@RequestMapping("/bug-report")
@RequiredArgsConstructor
public class BugReportController {
    private final CreateBugReportService createBugReportService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createBugReport(@RequestBody @Valid BugReportRequestDto dto){
        return createBugReportService.createBugReport(dto);
    }
}
