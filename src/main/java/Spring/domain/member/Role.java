package Spring.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    // 열거형 : 한 변수에 -> 두 개 이상 변수를 묶음
    ADMIN("ROLE_ADMIN", "관리자"),
    MEMBER("ROLE_MEMBER", "일반회원");

    private final String key; // 변수
    private final String title; // 변수

    //ROLE_ADMIN, ROLE_USER

}
