package Spring.domain.book;

import Spring.domain.BaseTime;
import Spring.web.dto.BookDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Book")
@Getter
@Setter
@NoArgsConstructor
public class BookEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 도서 번호

    @Column(name = "images")
    private String images; // 도서 이미지

    @Column(name = "name")
    private String name; // 도서명

    @Column(name = "author")
    private String author; // 지은이

    @Column(name = "genre")
    private String genre; // 도서 장르

    @Column(name = "publisher")
    private String publisher; // 출판사

    @Column(name = "publishing")
    private String publishing; // 출판일

    @Column(name = "reservation")
    private int reservation; // 대여 여부(0, 1)

    @Column(name = "money")
    private int money; // 대여금액

    @Column(name = "contents")
    private String contents; // 책소개

    // 생성자
    @Builder
    public BookEntity(Long id, String images, String name, String author, String genre, String publisher, String publishing, int reservation, int money, String contents) {
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
    }

    // 업데이트 메소드
    public void modify(BookDto modifyDto) {

        this.images = modifyDto.getImages();
        this.name = modifyDto.getName();
        this.author = modifyDto.getAuthor();
        this.genre = modifyDto.getGenre();
        this.publisher = modifyDto.getPublisher();
        this.publishing = modifyDto.getPublishing();
        this.reservation = modifyDto.getReservation();
        this.money = modifyDto.getMoney();
        this.contents = modifyDto.getContents();

    }

}
