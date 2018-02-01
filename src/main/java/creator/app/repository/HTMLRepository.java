package creator.app.repository;

import creator.app.model.HTMLUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HTMLRepository extends JpaRepository<HTMLUrl, Integer> {

}
