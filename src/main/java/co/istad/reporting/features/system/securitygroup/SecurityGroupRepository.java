package co.istad.reporting.features.system.securitygroup;

import co.istad.reporting.domain.primary.SecurityGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityGroupRepository
        extends JpaRepository<SecurityGroup, Integer> {
}
