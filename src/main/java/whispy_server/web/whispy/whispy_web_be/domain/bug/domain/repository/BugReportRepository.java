package whispy_server.web.whispy.whispy_web_be.domain.bug.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whispy_server.web.whispy.whispy_web_be.domain.bug.domain.BugReport;

public interface BugReportRepository extends JpaRepository<BugReport, Long> {
}
