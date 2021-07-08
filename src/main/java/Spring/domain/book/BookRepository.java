package Spring.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query(value = "select * from Book where reservation = 0", nativeQuery = true)
    List<BookEntity> findBylist();

}
