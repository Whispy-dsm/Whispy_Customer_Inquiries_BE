package whispy_server.web.whispy.whispy_web_be.domain.bug.presentation.dto.response;

import lombok.Builder;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.enums.BugTopic;

import java.time.LocalDateTime;

@Builder
public record BugReportResponseDto(
    Long id,
    String title,
    String bugTopic,
    String content,
    String email,
    LocalDateTime createdAt
) { }
