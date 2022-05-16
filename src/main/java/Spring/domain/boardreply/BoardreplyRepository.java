package Spring.domain.boardreply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardreplyRepository extends JpaRepository<BoardreplyEntity, Long> {

    List<BoardreplyEntity> findByboardid(Long boardid);
}
