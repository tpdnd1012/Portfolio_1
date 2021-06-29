package Spring.domain.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    // 타이틀 검색
    @Query(value = "select p from Board p where p.title Like %:search%",
            countQuery = "select count(p.id) from Board p where p.title Like %:search%")
    Page<BoardEntity> findAlltitle(String search, Pageable pageable);

    // 내용 검색
    @Query(value = "select p from Board p where p.contents Like %:search%",
            countQuery = "select count(p.id) from Board p where p.contents Like %:search%")
    Page<BoardEntity> findAllcontents(String search, Pageable pageable);

    // 작성자 검색
    @Query(value = "select p from Board p where p.name Like %:search%",
            countQuery = "select count(p.id) from Board p where p.name Like %:search%")
    Page<BoardEntity> findAllname(String search, Pageable pageable);

    // 번호 검색
    @Query(value = "select p from Board p where p.id = :search",
            countQuery = "select count(p.id) from Board p where p.id = :search")
    Page<BoardEntity> findAllid(Long search, Pageable pageable);

}
