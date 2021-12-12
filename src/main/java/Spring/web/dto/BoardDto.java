package Spring.web.dto;

import Spring.domain.board.BoardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {

    private Long id; // 게시물 번호
    private Long no;// 회원식별용
    private String title; // 게시물 제목
    private String name; // 게시물 작성자
    private String contents; // 게시물 내용
    private int count; // 게시물 조회수
    private int rcount; // 게시물 댓글 개수
    private LocalDateTime createDate; // 게시물 생성일

    // 생성자
    @Builder
    public BoardDto(Long id, Long no, String title, String name, String contents, int count, int rcount, LocalDateTime createDate) {
        this.id = id;
        this.no = no;
        this.title = title;
        this.name = name;
        this.contents = contents;
        this.count = count;
        this.rcount = rcount;
        this.createDate = createDate;
    }

    // Dto ==> Entity 이동
    public BoardEntity toEntity() {

        return BoardEntity.builder()
                .no(no)
                .title(title)
                .name(name)
                .contents(contents)
                .count(count).build();

    }

    public void rcountup() {

        this.rcount++;
    }
    
}
