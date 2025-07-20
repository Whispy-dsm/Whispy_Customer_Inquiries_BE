package whispy_server.web.whispy.whispy_web_be.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import whispy_server.web.whispy.whispy_web_be.global.exception.facade.ExceptionFacade;
import whispy_server.web.whispy.whispy_web_be.global.exception.handler.GlobalExceptionFilter;
import whispy_server.web.whispy.whispy_web_be.global.security.jwt.JwtTokenFilter;
import whispy_server.web.whispy.whispy_web_be.global.security.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;
    private final ExceptionFacade exceptionFacade;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(CsrfConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> {
                    //admin-auth
                    authorizeRequests
                            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                            .requestMatchers(HttpMethod.POST, "/auth/reissue").authenticated();

                    //user-bug-report
                    authorizeRequests
                            .requestMatchers(HttpMethod.POST, "/bug-report").permitAll();

                    //admin-bug-reports
                    authorizeRequests
                            .requestMatchers(HttpMethod.GET, "/admin/bug-reports").authenticated()
                            .requestMatchers(HttpMethod.GET, "/admin/bug-reports/{id}").authenticated()
                            .requestMatchers(HttpMethod.PATCH, "/admin/bug-reports/{id}").authenticated();
                })
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new GlobalExceptionFilter(objectMapper, exceptionFacade), JwtTokenFilter.class)
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }
}
