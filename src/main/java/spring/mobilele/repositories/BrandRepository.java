package spring.mobilele.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.mobilele.models.entities.Brand;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
