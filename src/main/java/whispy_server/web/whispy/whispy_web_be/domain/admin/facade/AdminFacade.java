package whispy_server.web.whispy.whispy_web_be.domain.admin.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import whispy_server.web.whispy.whispy_web_be.domain.admin.domain.Admin;
import whispy_server.web.whispy.whispy_web_be.domain.admin.domain.repository.AdminRepository;
import whispy_server.web.whispy.whispy_web_be.domain.admin.exception.UserNotFoundException;

@Component
@RequiredArgsConstructor
public class AdminFacade {
    private final AdminRepository adminRepository;

    public Admin getCurrentAdmin(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return adminRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
