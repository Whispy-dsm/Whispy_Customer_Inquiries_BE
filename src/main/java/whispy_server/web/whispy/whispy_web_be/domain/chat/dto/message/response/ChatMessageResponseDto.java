package whispy_server.web.whispy.whispy_web_be.domain.chat.dto.message.response;

import lombok.Builder;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.ChatMessage;
import whispy_server.web.whispy.whispy_web_be.domain.chat.domain.enums.Sender;

import java.time.LocalDateTime;

@Builder
public record ChatMessageResponseDto(
        Sender sender,
        String content,
        LocalDateTime sentAt
) {
    public static ChatMessageResponseDto from(ChatMessage chatMessage){
        return ChatMessageResponseDto.builder()
                .sender(chatMessage.getSender())
                .content(chatMessage.getContent())
                .sentAt(chatMessage.getSentAt())
                .build();
    }
}
