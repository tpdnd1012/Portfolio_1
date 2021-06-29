package Spring.domain.board;

import Spring.domain.BaseTime;
import Spring.web.dto.BoardupdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Board")
@Getter
@NoArgsConstructor
public class BoardEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 들어오는 값이 NULL 일 경우 자동 번호 부여
    @Column(name = "id")
    private Long id; // 게시물 번호, 기본키
    
    @Column(name = "no" , unique = true)
    private Long no; // 회원 식별용(중복 방지)

    @Column(name = "title", length = 20)
    private String title; // 게시물 제목

    @Column(name = "name")
    private String name; // 작성자

    @Column(name = "contents", columnDefinition = "TEXT")
    private String contents; // 게시물 내용

    @Column(name = "count")
    private int count; // 게시물 조회수

    // 생성자
    @Builder
    public BoardEntity(Long id, Long no, String title, String name, String contents, int count) {
        this.id = id;
        this.no = no;
        this.title = title;
        this.name = name;
        this.contents = contents;
        this.count = count;
    }

    // 게시판 수정 메소드
    public BoardEntity modify(BoardupdateDto boardupdateDto) {

        this.title = boardupdateDto.getTitle();
        this.name = boardupdateDto.getName();
        this.contents = boardupdateDto.getContents();

        return this;

    }

    // 조회수 증가 메소드
    public void countup() {

        this.count++;

    }
    
}
