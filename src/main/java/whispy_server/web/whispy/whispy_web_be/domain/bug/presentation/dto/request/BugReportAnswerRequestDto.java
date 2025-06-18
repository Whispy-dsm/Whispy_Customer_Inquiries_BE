package whispy_server.web.whispy.whispy_web_be.domain.bug.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record BugReportAnswerRequestDto(
        @NotBlank(message = "사용자를 위해 답변을 입력해주세요")
        String answer
) { }
