package creator.app.repository;

import creator.app.model.HTMLUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HTMLRepository extends JpaRepository<HTMLUrl, Integer> {
}
