package Spring.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookorderDto {

    private Long id; // 도서번호
    private Long member; // 도서 대여 아이디(PK)
    private String startdate; // 도서대여시작
    private String finaldate; // 도서대여끝

}
