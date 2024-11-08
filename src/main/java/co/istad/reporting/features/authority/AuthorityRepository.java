package co.istad.reporting.features.authority;

import co.istad.reporting.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository
    extends JpaRepository<Authority, Integer> {
}
