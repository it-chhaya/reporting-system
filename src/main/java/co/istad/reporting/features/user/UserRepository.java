package co.istad.reporting.features.user;

import co.istad.reporting.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
