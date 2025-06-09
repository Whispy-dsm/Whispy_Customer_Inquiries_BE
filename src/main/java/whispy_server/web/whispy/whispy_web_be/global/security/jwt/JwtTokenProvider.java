package whispy_server.web.whispy.whispy_web_be.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import whispy_server.web.whispy.whispy_web_be.domain.auth.domain.RefreshToken;
import whispy_server.web.whispy.whispy_web_be.domain.auth.domain.repository.RefreshTokenRepository;
import whispy_server.web.whispy.whispy_web_be.global.security.auth.AuthDetailsService;
import whispy_server.web.whispy.whispy_web_be.global.security.jwt.exception.ExpiredJwtException;
import whispy_server.web.whispy.whispy_web_be.global.security.jwt.exception.InvalidJwtException;

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
        this.secretKeySpec = new SecretKeySpec(jwtProperties.secretKey().getBytes(), SignatureAlgorithm.HS256.getJcaName());
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
        return generateToken(email, "access", jwtProperties.accessExp());
    }

    public String generateRefreshToken(String email){
        String refreshToken = generateToken(email, "refresh", jwtProperties.refreshExp());
        refreshTokenRepository.save(RefreshToken.builder()
                .email(email)
                .refreshToken(refreshToken)
                .ttl(jwtProperties.refreshExp())
                .build());
        return refreshToken;
    }

    public String parseToken(String bearerToken){
        if(bearerToken != null && bearerToken.startsWith(jwtProperties.prefix())){
            return bearerToken.replace(jwtProperties.prefix(), "").trim();
        }
        return null;
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(jwtProperties.header());
        return parseToken(bearerToken);
    }
    public Claims getTokenBody(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(secretKeySpec)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (io.jsonwebtoken.ExpiredJwtException e){
            throw ExpiredJwtException.EXCEPTION;
        } catch (Exception e){
            throw InvalidJwtException.EXCEPTION;
        }
    }

    public String getTokenSubject(String token){
        return getTokenBody(token).getSubject();
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
