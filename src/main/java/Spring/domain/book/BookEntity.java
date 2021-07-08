package Spring.domain.book;

import Spring.domain.BaseTime;
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
    private Long id; // 도서 번호

    @Column
    private String images; // 도서 이미지

    @Column
    private String name; // 도서명

    @Column
    private String author; // 지은이

    @Column
    private String genre; // 도서 장르

    @Column
    private String publisher; // 출판사

    @Column
    private String publishing; // 출판일

    @Column
    private int reservation; // 대여 여부(0, 1)

    @Column
    private int money; // 대여금액

    @Builder
    public BookEntity(Long id, String images, String name, String author, String genre, String publisher, String publishing, int reservation, int money) {
        this.id = id;
        this.images = images;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.publishing = publishing;
        this.reservation = reservation;
        this.money = money;
    }
}
