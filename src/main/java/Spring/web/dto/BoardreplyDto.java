package Spring.web.dto;

import Spring.domain.boardreply.BoardreplyEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardreplyDto {

    private Long id; // 기본키
    private Long boardid; // 게시물 번호
    private Long no; // 회원식벽용
    private String replycontents; // 댓글 내용
    private String name; // 댓글 작성자
    private LocalDateTime createDate; // 작성일

    // 생성자
    @Builder
    public BoardreplyDto(Long id, Long boardid, Long no, String replycontents, String name, LocalDateTime createDate) {
        this.id = id;
        this.boardid = boardid;
        this.no = no;
        this.replycontents = replycontents;
        this.name = name;
        this.createDate = createDate;
    }

    // DTO --> Entity 이동
    public BoardreplyEntity toEntity() {

        return BoardreplyEntity.builder()
                .id(id)
                .boardid(boardid)
                .no(no)
                .replycontents(replycontents)
                .name(name).build();

    }

}
