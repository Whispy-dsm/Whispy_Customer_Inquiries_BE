package whispy_server.web.whispy.whispy_web_be.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${app.timezone:Asia/Seoul}")
    private String timezone;

    @Transactional
    public TokenResponse reissue(HttpServletRequest request){

        String parseToken = jwtTokenProvider.resolveToken(request);
        if(parseToken == null) throw InvalidRefreshTokenException.EXCEPTION;

        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(parseToken)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String email = refreshToken.getEmail();
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(email);
        refreshToken.update(newRefreshToken, jwtProperties.refreshExp());
        refreshTokenRepository.save(refreshToken);

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(timezone));

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(email))
                .accessExpiredAt(now.plusSeconds(jwtProperties.accessExp()))
                .refreshToken(newRefreshToken)
                .refreshExpiredAt(now.plusSeconds(jwtProperties.refreshExp()))
                .build();
    }
}
