package whispy_server.web.whispy.whispy_web_be.domain.bug.presentation.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import whispy_server.web.whispy.whispy_web_be.domain.bug.presentation.dto.request.BugReportAnswerRequestDto;
import whispy_server.web.whispy.whispy_web_be.domain.bug.presentation.dto.response.BugReportResponseDto;
import whispy_server.web.whispy.whispy_web_be.domain.bug.service.admin.AnswerAndDeleteBugReportService;
import whispy_server.web.whispy.whispy_web_be.domain.bug.service.admin.QueryBugReportAdminService;
import whispy_server.web.whispy.whispy_web_be.domain.bug.service.admin.QueryBugReportListAdminService;

@RestController
@RequestMapping("/admin-bug-report")
@RequiredArgsConstructor
public class AdminReportController {
    private final QueryBugReportAdminService queryBugReportAdminService;
    private final QueryBugReportListAdminService queryBugReportListAdminService;
    private final AnswerAndDeleteBugReportService answerAndDeleteBugReportService;

    @GetMapping
    public Page<BugReportResponseDto> getBugReportList(
        @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
        Pageable pageable
    ){
        return queryBugReportListAdminService.getBugReportList(pageable);
    }

    @GetMapping("/{id}")
    public BugReportResponseDto getBugReport(@PathVariable("id") Long id){
        return queryBugReportAdminService.getBugReport(id);
    }

    @PatchMapping("/{id}/answer-and-delete") //자원의 부분상태를 수정 후 삭제진행
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void answerAndDelete(
            @RequestBody @Valid BugReportAnswerRequestDto dto,
            @PathVariable("id") Long id
    ){
        answerAndDeleteBugReportService.answerAndDelete(id, dto.answer());
    }
}
