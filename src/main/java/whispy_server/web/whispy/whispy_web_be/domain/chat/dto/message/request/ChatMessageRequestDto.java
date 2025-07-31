package whispy_server.web.whispy.whispy_web_be.domain.chat.dto.message.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChatMessageRequestDto(
        @NotBlank(message = "메세지를 작성해 주세요.")
        @Size(max = 1000, message = "1000자 이하로 작성해 주세요.")
        String content
) {
}
