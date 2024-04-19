package africashop.be.services.Authentication;

import africashop.be.dtos.SignupRequest;
import africashop.be.dtos.UserDto;
import africashop.be.entities.User;

import java.util.List;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);

    Boolean hasUserWithEmail(String email);

    List<User> getAllUsers();

    void deleteUser(Long id);
}
