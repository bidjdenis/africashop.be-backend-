package africashop.be.services.Member;

import africashop.be.dtos.UserDto;
import africashop.be.entities.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long userId);

    User updateUser(Long userId, UserDto userDto);
}
