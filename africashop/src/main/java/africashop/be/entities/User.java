package africashop.be.entities;

import africashop.be.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String email;

    private String name;

    private String password;
    private Date createdDate ;

   private UserRole role;

}
