package africashop.be.Repositories;

import africashop.be.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country,Long> {
}
