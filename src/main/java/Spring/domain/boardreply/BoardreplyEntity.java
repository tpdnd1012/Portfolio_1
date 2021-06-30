package Spring.domain.boardreply;

import Spring.domain.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class BoardreplyEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 들어오는 값이 NULL일 경우 자동번호 부여
    private Long id; // 기본키

    @Column
    private Long boardid; // 게시물 번호

    @Column
    private Long no; // 회원식별용

    @Column
    private String replycontents; // 댓글내용

    @Column
    private String name; // 댓글 작성자

    // 생성자
    @Builder
    public BoardreplyEntity(Long id, Long boardid, Long no, String replycontents, String name) {
        this.id = id;
        this.boardid = boardid;
        this.no = no;
        this.replycontents = replycontents;
        this.name = name;
    }

    // 댓글내용 수정 메소드
    public void replymodify(String replycontents) {

        this.replycontents = replycontents;

    }
}
