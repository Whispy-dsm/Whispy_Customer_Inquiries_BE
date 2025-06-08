package whispy_server.web.whispy.whispy_web_be.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whispy_server.web.whispy.whispy_web_be.domain.auth.domain.RefreshToken;
import whispy_server.web.whispy.whispy_web_be.domain.auth.domain.repository.RefreshTokenRepository;
import whispy_server.web.whispy.whispy_web_be.domain.auth.exception.InvalidRefreshTokenException;
import whispy_server.web.whispy.whispy_web_be.domain.auth.exception.RefreshTokenNotFoundException;
import whispy_server.web.whispy.whispy_web_be.domain.auth.presentation.dto.response.TokenResponse;
import whispy_server.web.whispy.whispy_web_be.global.security.jwt.JwtProperties;
import whispy_server.web.whispy.whispy_web_be.global.security.jwt.JwtTokenProvider;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class ReissueService {
    public final JwtTokenProvider jwtTokenProvider;
    public final JwtProperties jwtProperties;
    public final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokenResponse reissue(HttpServletRequest request){
        String refreshToken = request.getHeader("Authorization");
        if(refreshToken == null) throw InvalidRefreshTokenException.EXCEPTION; // TODO: 헤더 누락 시 MissingAuthorizationHeaderException 으로 수정 예정

        String parseToken = jwtTokenProvider.parseToken(refreshToken);
        if(parseToken == null) throw InvalidRefreshTokenException.EXCEPTION; // TODO: 형식 오류 시 MalformedAuthorizationHeaderException 으로 분리 예정

        RefreshToken redisRefreshToken = refreshTokenRepository.findByRefreshToken(parseToken)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String email = redisRefreshToken.getEmail();
        refreshTokenRepository.delete(redisRefreshToken);

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(email))
                .accessExpiredAt(now.plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(jwtTokenProvider.generateRefreshToken(email))
                .refreshExpiredAt(now.plusSeconds(jwtProperties.getRefreshExp()))
                .build();
    }
}
