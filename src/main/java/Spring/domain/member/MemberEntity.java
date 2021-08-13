package Spring.domain.member;

import Spring.domain.BaseTime;
import Spring.web.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Member") // 작업도중 Repository에서 테이블 못찾는경우 있음 --> 이름설정(이름이 똑같아서 못찾는 경우도 있어서 그땐 다르게 설정해주고 확인)
@Getter
@NoArgsConstructor // 기본(비어있는) 생성자
public class MemberEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 들어오는 값이 NULL일 경우 자동번호 부여
    @Column(name = "no")
    private Long no; // 회원번호

    @Column(name = "member_id")
    private String member_id; // 회원 아이디

    @Column(name = "member_pw")
    private String member_pw; // 회원 비밀번호

    @Column(name = "name")
    private String name; // 회원 이름

    @Column(name = "gender")
    private String gender; // 회원 성별

    @Column(name = "birth")
    private String birth; // 회원 생년월일

    @Column(name = "phone")
    private String phone; // 회원 휴대폰 번호

    @Column(name = "email")
    private String email; // 회원 이메일

    @Column(name = "address")
    private String address; // 회원 주소

    @Column(name = "point")
    private int point; // 회원 포인트

    // 생성자
    @Builder
    public MemberEntity(Long no, String member_id, String member_pw, String name, String gender, String birth, String phone, String email, String address, int point) {
        this.no = no;
        this.member_id = member_id;
        this.member_pw = member_pw;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.point = point;
    }

    // 회원 수정 메소드
    public MemberEntity modify(MemberDto memberDto) {

        this.member_pw = memberDto.getMember_pw();
        this.name = memberDto.getName();
        this.gender = memberDto.getGender();
        this.phone = memberDto.getPhone();
        this.email = memberDto.getEmail();
        this.address = memberDto.getAddress();

        return this;

    }

    public void update(String member_pw) {

        this.member_pw = member_pw;

    }

}
