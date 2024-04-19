package africashop.be.services.Authentication;

import africashop.be.Repositories.UserRepo;
import africashop.be.dtos.SignupRequest;
import africashop.be.dtos.UserDto;
import africashop.be.entities.User;
import africashop.be.enums.UserRole;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService{
    public AuthServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(UserRole.MEMBER);

        User createdUser = userRepo.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());

        return userDto;
    }

    @PostConstruct
    public void createAdmin(){
        User adminAccount = userRepo.findByRole(UserRole.ADMIN);

        if(null == adminAccount){
            User user = new User();
            user.setName("admin");
            user.setEmail("admin@shop.com");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRole(UserRole.ADMIN);
            userRepo.save(user);
        }
    }
    @Override
    public Boolean hasUserWithEmail(String email) {
        return userRepo.findFirstByEmail(email).isPresent();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
