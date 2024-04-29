package africashop.be.controllers.AdminControllers;

import africashop.be.entities.User;
import africashop.be.services.Authentication.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class UserController {

    private final AuthService authService;


    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return authService.getAllUsers();
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        authService.deleteUser(id);
    }
}
