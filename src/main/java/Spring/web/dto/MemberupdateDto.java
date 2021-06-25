package Spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberupdateDto {

    private Long no; // 회원번호
    private String member_id; // 회원 아이디
    private String member_pw; // 회원 비밀번호
    private String name; // 회원 이름
    private String gender; // 회원 성별
    private String birth; // 회원 생년월일

    private String phone1; // 회원 휴대폰 번호1
    private String phone2; // 회원 휴대폰 번호2
    private String phone3; // 회원 휴대폰 번호3

    private String email1; // 회원 이메일1
    private String email2; // 회원 이메일2

    private String address1; // 회원 주소1
    private String address2; // 회원 주소2
    private String address3; // 회원 주소3
    private String address4; // 회원 주소4

    @Builder
    public MemberupdateDto(Long no, String member_id, String member_pw, String name, String gender, String birth, String phone1, String phone2, String phone3, String email1, String email2, String address1, String address2, String address3, String address4) {
        this.no = no;
        this.member_id = member_id;
        this.member_pw = member_pw;
        this.name = name;
        this.gender = gender;
        this.birth = birth;

        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;

        this.email1 = email1;
        this.email2 = email2;

        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.address4 = address4;
    }

}
