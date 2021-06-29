package Spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardupdateDto {

    private Long id; // 게시물 번호
    private Long no;// 회원식별용
    private String title; // 게시물 제목
    private String name; // 게시물 작성자
    private String contents; // 게시물 내용

    @Builder
    public BoardupdateDto(Long id, Long no, String title, String name, String contents) {
        this.id = id;
        this.no = no;
        this.title = title;
        this.name = name;
        this.contents = contents;
    }

}
