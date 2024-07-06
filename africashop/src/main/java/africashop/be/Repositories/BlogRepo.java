package africashop.be.Repositories;

import africashop.be.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepo extends JpaRepository<Blog,Long> {
}
