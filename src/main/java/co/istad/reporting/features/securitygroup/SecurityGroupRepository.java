package co.istad.reporting.features.securitygroup;

import co.istad.reporting.domain.SecurityGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityGroupRepository
        extends JpaRepository<SecurityGroup, Integer> {
}
