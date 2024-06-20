package spring.mobilele.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.mobilele.models.entities.Model;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

}
