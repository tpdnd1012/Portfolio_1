package Spring.domain.book;

import javax.persistence.*;

@Entity
public class BookorderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long member; // 도서 대여 아이디

    @Column
    private String startdate; // 도서대여시작

    @Column
    private String finaldate; // 도서대여끝

    @Column
    private int reservation; // 반납 여부(0, 1)

    @Column
    private int money; // 최종 결제금액

    @Column
    private int unpaid; // 미납금액

}
