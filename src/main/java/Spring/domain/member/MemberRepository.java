package Spring.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    // 회원 수정 전 인증단계 -> 아이디, 비밀번호 받아서 Entity 찾기
    @Query(value = "select * from Member where member_id=?1 and member_pw=?2", nativeQuery = true)
    Optional<MemberEntity> findByinfo(String member_id, String member_pw);

    @Query(value = "select * from Member where name=?1 and birth=?2 and phone=?3", nativeQuery = true)
    Optional<MemberEntity> findid(String name, String birth, String phone);

    @Query(value = "select * from Member where member_id=?1 and birth=?2 and email=?3", nativeQuery = true)
    Optional<MemberEntity> findpw(String member_id, String birth, String email);
}
