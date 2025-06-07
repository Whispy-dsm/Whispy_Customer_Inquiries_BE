package whispy_server.web.whispy.whispy_web_be.global.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import whispy_server.web.whispy.whispy_web_be.domain.admin.domain.repository.AdminRepository;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return adminRepository.findByEmail(email)
                .map(AuthDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
