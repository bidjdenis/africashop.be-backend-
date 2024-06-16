package africashop.be.controllers.MemberControllers;

import africashop.be.dtos.UserDto;
import africashop.be.entities.User;
import africashop.be.services.Member.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/member")
@AllArgsConstructor
public class UserProfileController {

    private final UserService userService;

    @GetMapping("profile/{userId}")
    public User getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PutMapping("updateProfile/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }
}
