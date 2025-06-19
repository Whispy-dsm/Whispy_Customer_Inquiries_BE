package whispy_server.web.whispy.whispy_web_be.domain.bug.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.enums.BugTopic;

public record BugReportRequestDto(
        @NotBlank(message = "제목을 입력해 주세요")
        @Size(max = 50, message = "제목은 50글자 이내로 해주세요.")
        String title,

        BugTopic bugTopic,

        @NotBlank(message = "본문을 입력해 주세요")
        @Size(max = 5000, message = "내용은 5000자 이내로 해주세요.")
        String content,

        @Email(message = "올바른 이메일 형식을 입력해주세요.")
        @NotBlank(message = "이메일은 필수 입력값입니다.")
        String email
) { }
