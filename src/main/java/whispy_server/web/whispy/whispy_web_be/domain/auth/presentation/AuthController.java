package whispy_server.web.whispy.whispy_web_be.domain.auth.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whispy_server.web.whispy.whispy_web_be.domain.auth.presentation.dto.request.LoginRequestDto;
import whispy_server.web.whispy.whispy_web_be.domain.auth.presentation.dto.response.TokenResponse;
import whispy_server.web.whispy.whispy_web_be.domain.auth.service.LoginService;
import whispy_server.web.whispy.whispy_web_be.domain.auth.service.ReissueService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final LoginService loginService;
    private final ReissueService reissueService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequestDto dto){
        return loginService.login(dto);
    }

    @PostMapping("/reissue")
    public TokenResponse reissue(HttpServletRequest request){
        return reissueService.reissue(request);
    }
}
