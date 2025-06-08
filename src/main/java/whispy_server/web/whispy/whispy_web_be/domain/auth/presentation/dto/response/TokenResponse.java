package whispy_server.web.whispy.whispy_web_be.domain.auth.presentation.dto.response;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record TokenResponse(
        String accessToken,
        ZonedDateTime accessExpiredAt,
        String refreshToken,
        ZonedDateTime refreshExpiredAt
) { }
