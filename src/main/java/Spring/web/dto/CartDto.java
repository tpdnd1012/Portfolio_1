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
    private int money; // 대여금액
    private String author; // 지은이
    private String genre; // 도서 장르
    private String publisher; // 출판사

    public Long getNo() {
        return no;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImages() {
        return images;
    }

    public int getMoney() {
        return money;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }


    public void setNo(Long no) {
        this.no = no;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
