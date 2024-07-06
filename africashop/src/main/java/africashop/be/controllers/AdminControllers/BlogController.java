package africashop.be.controllers.AdminControllers;

import africashop.be.services.Admin.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class BlogController {

    private final BlogService blogService;

    
}
