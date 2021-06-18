package Spring.web;

import Spring.service.MemberService;
import Spring.web.dto.MemberDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final HttpSession session; // 세션 객체 생성

    // 회원가입 페이지 요청
    @GetMapping("/signup")
    public String signup() {

        return "signup";

    }

    // 로그인 페이지 요청
    @GetMapping("/login")
    public String login() {

        return "login";

    }

    // 회원가입 처리
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String save(MemberDto memberDto, HttpServletRequest request) {


        String phone1 = request.getParameter("phone1");
        String phone2 = request.getParameter("phone2");
        String phone3 = request.getParameter("phone3");

        String phone = phone1 + "-" + phone2 + "-" + phone3;

        String email1 = request.getParameter("email1");
        String email2 = request.getParameter("email2");

        String email = email1 + email2;

        String address1 = request.getParameter("address1");

        return "";

    }

}
