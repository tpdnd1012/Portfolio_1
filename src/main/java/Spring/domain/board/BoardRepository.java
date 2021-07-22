package Spring.domain.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Query(value = "select count(*) from Board LIMIT 5" ,  nativeQuery = true)
    List<BoardEntity> findBylist();

}
