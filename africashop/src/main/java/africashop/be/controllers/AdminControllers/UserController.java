package africashop.be.controllers.AdminControllers;

import africashop.be.Repositories.UserRepo;
import africashop.be.dtos.UserDto;
import africashop.be.entities.User;
import africashop.be.services.Authentication.AuthService;
import africashop.be.services.Member.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class UserController {

    private final AuthService authService;

    private final UserService userService;



    public UserController(AuthService authService, UserRepo userRepo, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return authService.getAllUsers();
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        authService.deleteUser(id);
    }

    @PutMapping("updateProfile/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }
}
