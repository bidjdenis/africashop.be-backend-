package africashop.be.Repositories;

import africashop.be.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findFirstByEmail(String username);
}
