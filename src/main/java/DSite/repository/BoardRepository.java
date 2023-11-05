package DSite.repository;

import DSite.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByCategoryContaining(String category);
    Optional<BoardEntity> findByUrl(String url);
}
