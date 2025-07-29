package whispy_server.web.whispy.whispy_web_be.domain.chat.dto.room;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChatRoomSummaryDto(
        Long roomId,
        String sessionId,
        LocalDateTime lastMessageAt
) {
}
