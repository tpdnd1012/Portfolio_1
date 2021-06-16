package Spring.domain.member;

import Spring.web.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor // 기본(비어있는) 생성자
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 들어오는 값이 NULL일 경우 자동번호 부여
    @Column
    private Long no; // 회원번호

    @Column
    private String member_id; // 회원 아이디

    @Column
    private String member_pw; // 회원 비밀번호

    @Column
    private String name; // 회원 이름

    @Column
    private String gender; // 회원 성별

    @Column
    private String birth; // 회원 생년월일

    @Column
    private String phone; // 회원 휴대폰 번호

    @Column
    private String email; // 회원 이메일

    @Column
    private String address; // 회원 주소

    // 생성자
    @Builder
    public MemberEntity(Long no, String member_id, String member_pw, String name, String gender, String birth, String phone, String email, String address) {
        this.no = no;
        this.member_id = member_id;
        this.member_pw = member_pw;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // 회원 수정 메소드
    public MemberEntity modify(MemberDto memberDto) {

        this.member_pw = member_pw;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;

        return this;

    }

}
