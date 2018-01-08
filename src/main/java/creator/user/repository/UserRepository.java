package creator.user.repository;

import creator.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public interface UserRepository extends JpaRepository<User, Integer> {
}
