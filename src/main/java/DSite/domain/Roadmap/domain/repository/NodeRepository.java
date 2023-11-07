package DSite.domain.Roadmap.domain.repository;

import DSite.domain.Roadmap.domain.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeRepository extends JpaRepository<NodeEntity, Long> {
    List<NodeEntity>findByCategoryContaining(String what);
}
