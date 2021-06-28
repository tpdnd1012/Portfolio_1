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
    private String title; // 게시물 제목
    private String name; // 게시물 작성자
    private String contents; // 게시물 내용
    private int count; // 게시물 조회수
    private LocalDateTime createDate; // 게시물 생성일

    // 생성자
    @Builder
    public BoardDto(Long id, String title, String name, String contents, int count, LocalDateTime createDate) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.contents = contents;
        this.count = count;
        this.createDate = createDate;
    }

    // Dto ==> Entity 이동
    public BoardEntity toEntity() {

        return BoardEntity.builder()
                .title(title)
                .name(name)
                .contents(contents)
                .count(count).build();

    }
    
}
