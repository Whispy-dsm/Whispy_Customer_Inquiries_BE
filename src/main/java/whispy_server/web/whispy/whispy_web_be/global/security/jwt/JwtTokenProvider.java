package whispy_server.web.whispy.whispy_web_be.global.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import whispy_server.web.whispy.whispy_web_be.domain.auth.domain.RefreshToken;
import whispy_server.web.whispy.whispy_web_be.domain.auth.domain.repository.RefreshTokenRepository;
import whispy_server.web.whispy.whispy_web_be.global.security.auth.AuthDetailsService;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthDetailsService authDetailsService;
    private final SecretKeySpec secretKeySpec;

    public JwtTokenProvider(JwtProperties jwtProperties, RefreshTokenRepository refreshTokenRepository, AuthDetailsService authDetailsService){
        this.jwtProperties = jwtProperties;
        this.refreshTokenRepository = refreshTokenRepository;
        this.authDetailsService = authDetailsService;
        this.secretKeySpec = new SecretKeySpec(jwtProperties.getSecretKey().getBytes(), SignatureAlgorithm.RS256.getJcaName());
    }

    public String generateToken(String email, String type, Long exp){
        return Jwts.builder()
                .signWith(secretKeySpec)
                .setSubject(email)
                .setHeaderParam("type", type)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    public String generateAccessToken(String email){
        return generateToken(email, "access", jwtProperties.getAccessExp());
    }

    public String generateRefreshToken(String email){
        String refreshToken = generateToken(email, "refresh", jwtProperties.getRefreshExp());
        refreshTokenRepository.save(RefreshToken.builder()
                .email(email)
                .refreshToken(refreshToken)
                .ttl(jwtProperties.getRefreshExp())
                .build());
        return refreshToken;
    }

    public String parseToken(String bearerToken){
        if(bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())){
            return bearerToken.replace(jwtProperties.getPrefix(), "").trim();
        }
        return null;
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(jwtProperties.getHeader());
        return parseToken(bearerToken);
    }

}
