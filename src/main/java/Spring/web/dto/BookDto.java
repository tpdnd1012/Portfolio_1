package Spring.web.dto;

import Spring.domain.book.BookEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {

    private Long id; // 도서 번호
    private String images; // 도서 이미지
    private String name; // 도서명
    private String author; // 지은이
    private String genre; // 도서 장르
    private String publisher; // 출판사
    private String publishing; // 출판일
    private int reservation; // 대여 여부(0, 1)
    private int money; // 대여금액
    private String contents; // 책소개
    private LocalDateTime createDate; // 도서 등록 일자

    @Builder
    public BookDto(Long id, String images, String name, String author, String genre, String publisher, String publishing, int reservation, int money, String contents, LocalDateTime createDate) {
        this.id = id;
        this.images = images;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.publishing = publishing;
        this.reservation = reservation;
        this.money = money;
        this.contents = contents;
        this.createDate = createDate;
    }

    // Dto ---> Entity 이동
    public BookEntity toEntity() {

        return BookEntity.builder()
                .images(images)
                .name(name)
                .author(author)
                .genre(genre)
                .publisher(publisher)
                .publishing(publishing)
                .reservation(reservation)
                .money(money)
                .contents(contents).build();

    }

}
