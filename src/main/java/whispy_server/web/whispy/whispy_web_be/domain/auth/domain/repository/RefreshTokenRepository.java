package whispy_server.web.whispy.whispy_web_be.domain.auth.domain.repository;

import org.springframework.data.repository.CrudRepository;
import whispy_server.web.whispy.whispy_web_be.domain.auth.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
