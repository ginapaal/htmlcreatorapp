package creator.app.repository;

import creator.app.model.HTMLUrl;
import creator.app.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

}
