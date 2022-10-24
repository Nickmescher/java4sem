package cats2.repository;

import cats2.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cats, Integer> {
}
