package pl.glegdev.wallet.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.glegdev.wallet.model.User;

/**
 *  Spring generates implementation automatically.
 *  For more details:
 *  https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
