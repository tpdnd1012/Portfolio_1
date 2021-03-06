package Spring.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class CartDto {

    private Long no; // 회원번호
    private Long id; // 도서 번호
    private String name; // 도서 이름
    private String images; // 도서 이미지
    private int money; // 초기 대여금액
    private String author; // 지은이
    private String genre; // 도서 장르
    private String publisher; // 출판사
    private String period; // 대여일

}
