package Spring.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    @Query("")
    Optional<MemberEntity> findByinfo(String member_id, String member_pw);
}
