package DSite.domain.Roadmap.domain.repository;

import DSite.domain.Roadmap.domain.NodeEntity;
import DSite.domain.Roadmap.domain.PathEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PathRepository extends JpaRepository<PathEntity, Long> {
    List<PathEntity> findByCategoryContaining(String what);
}
