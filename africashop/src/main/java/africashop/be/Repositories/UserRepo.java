package africashop.be.Repositories;

import africashop.be.entities.User;
import africashop.be.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByRole(UserRole admin);

    Optional<User> findFirstByEmail(String username);
}
