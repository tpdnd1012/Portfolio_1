package Spring.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookorderDto {

    private Long id; // 도서번호
    private int reservation; // 대여 여부(0, 1)
    private int review; // 리뷰 개수
    private int count; // 도서대여횟수
    private Long member; // 도서 대여 아이디
    private String startdate; // 도서대여시작
    private String finaldate; // 도서대여끝

}
