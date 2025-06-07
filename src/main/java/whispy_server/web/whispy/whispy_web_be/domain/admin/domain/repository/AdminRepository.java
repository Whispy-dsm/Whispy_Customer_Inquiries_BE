package whispy_server.web.whispy.whispy_web_be.domain.admin.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whispy_server.web.whispy.whispy_web_be.domain.admin.domain.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}
