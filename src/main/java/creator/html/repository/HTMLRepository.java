package creator.html.repository;

import creator.html.model.HTMLCreator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HTMLRepository extends JpaRepository<HTMLCreator, Integer> {
}
