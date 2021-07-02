package Spring.domain.boardreply;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardreplyRepository extends JpaRepository<BoardreplyEntity, Long> {

    List<BoardreplyEntity> findByboardid(Long boardid);

}
