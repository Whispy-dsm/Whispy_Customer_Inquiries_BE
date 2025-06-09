package whispy_server.web.whispy.whispy_web_be.global.security.jwt;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.jwt")
public record JwtProperties(
        String secretKey,
        Long accessExp,
        Long refreshExp,
        String header,
        String prefix
) { }

