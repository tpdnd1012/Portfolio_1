package Spring.web.dto;

import Spring.domain.member.MemberEntity;
import Spring.domain.member.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class AttributeDto {

    // 회원정보 반환 => JSON 타입 => Map컬렉션
        // JSON : 키, 값 => 한 쌍

    private Map<String, Object> attribute; // 회원정보
    private String nameAttributekey;
    private String name;
    private String email;

    @Builder
    public AttributeDto(Map<String, Object> attribute, String nameAttributekey, String name, String email) {
        this.attribute = attribute;
        this.nameAttributekey = nameAttributekey;
        this.name = name;
        this.email = email;
    }

    // SNS 구분 메소드
    public static AttributeDto of(String registrationId, String userNameAttributeName, Map<String, Object> attribute) {

        if(registrationId.equals("naver")) {

            return ofNaver(userNameAttributeName, attribute, registrationId);

        } else if(registrationId.equals("kakao")) {

            return ofKakao(userNameAttributeName, attribute, registrationId);

        } else {

            return ofGoogle(userNameAttributeName, attribute, registrationId);

        }

    }

    // 네이버 회원 가져오기 메소드
    public static AttributeDto ofNaver(String userNameAttributeName, Map<String, Object> attribute, String registrationId) {

        Map<String, Object> response = ((Map<String, Object>) attribute.get("response"));

        return AttributeDto.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attribute(attribute)
                .nameAttributekey(userNameAttributeName).build();

    }

    // 카카오 회원 가져오기 메소드
    public static AttributeDto ofKakao(String userNameAttributeName, Map<String, Object> attribute, String registrationId) {

        Map<String, Object> kakaoAccount = (Map<String, Object>) attribute.get("kakao_account"); // JSON 이라서 Map으로 형변황
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        return AttributeDto.builder()
                .name((String) profile.get("nickname"))
                .email((String) profile.get("email"))
                .attribute(attribute)
                .nameAttributekey(userNameAttributeName).build();

    }

    // 구글 회원 가져오기 메소드
    public static AttributeDto ofGoogle(String userNameAttributeName, Map<String, Object> attribute, String registrationId) {

        return AttributeDto.builder()
                .name((String) attribute.get("name"))
                .email((String) attribute.get("email"))
                .attribute(attribute)
                .nameAttributekey(userNameAttributeName).build();

    }

    // Dto ---> Entity
    public MemberEntity toEntity() {

        return MemberEntity.builder()
                .name(name)
                .email(email)
                .role(Role.MEMBER).build();

    }

}
