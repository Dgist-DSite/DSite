package DSite.domain.Roadmap.domain.repository;

import DSite.domain.Roadmap.domain.NodeBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeBoardRepository extends JpaRepository<NodeBoardEntity, Long> {
    List<NodeBoardEntity> findNodeBoardEntityByNodeIdContaining(String id);
}
