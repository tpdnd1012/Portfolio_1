package Spring.web.dto;

import Spring.domain.member.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자
public class MemberDto {

    private Long no; // 회원번호
    private String member_id; // 회원 아이디
    private String member_pw; // 회원 비밀번호
    private String name; // 회원 이름
    private String gender; // 회원 성별
    private String birth; // 회원 생년월일
    private String phone; // 회원 휴대폰 번호
    private String email; // 회원 이메일
    private String address; // 회원 주소

    // 생성자
    @Builder
    public MemberDto(Long no, String member_id, String member_pw, String name, String gender, String birth, String phone, String email, String address) {
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

    // 멤버 수정 메소드
    public MemberDto(MemberEntity entity) {

        this.member_pw = entity.getMember_pw();
        this.name = entity.getName();
        this.gender = entity.getGender();
        this.phone = entity.getPhone();
        this.email = entity.getEmail();
        this.address = entity.getAddress();

    }

    // DTO 모든 빌드 ===> Entity로 이동
    public MemberEntity toEntity() {

        return MemberEntity.builder()
                .no(no)
                .member_id(member_id)
                .member_pw(member_pw)
                .name(name)
                .gender(gender)
                .birth(birth)
                .phone(phone)
                .email(email)
                .address(address).build();

    }

}
