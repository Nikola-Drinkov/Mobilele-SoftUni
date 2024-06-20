package spring.mobilele.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.mobilele.models.entities.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    Offer findFirstById(Long id);
}
