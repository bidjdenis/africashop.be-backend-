package africashop.be.services.Member;

import africashop.be.Repositories.UserRepo;
import africashop.be.dtos.UserDto;
import africashop.be.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;


    public Optional<User> getUserById(Long userId) {
        return userRepo.findById(userId);
    }

    public User updateUser(Long userId, UserDto userDto) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(userDto.getEmail());
            user.setName(userDto.getName());
            return userRepo.save(user);
        }
        throw new RuntimeException("User not found");
    }
}
