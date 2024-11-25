package co.istad.reporting.features.system.authority;

import co.istad.reporting.domain.primary.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository
    extends JpaRepository<Authority, Integer> {
}
