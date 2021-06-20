package Spring.web;

import Spring.service.MemberService;
import Spring.web.dto.MemberDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        String address2 = request.getParameter("address2");

        String address3 = request.getParameter("address3");

        if(address3 == null) {
            address3 = " ";
        }

        String address4 = request.getParameter("address4");

        String address = address1 + "-" + address2 + "-" + address3 + "-" + address4;

        memberDto.setPhone(phone);
        memberDto.setEmail(email);
        memberDto.setAddress(address);

        memberService.membersave(memberDto);

        return "redirect:/login";

    }

    // 로그인 처리
    @PostMapping("/login")
    public String login_c(MemberDto logindto) {

        // 로그인 서비스 연결
        MemberDto memberDto = memberService.memberlogin(logindto);

        if(memberDto != null) {

            // 로그인 성공 => 세션에 담기
            session.setAttribute("loginuser", memberDto);

            return "redirect:/";

        } else {

            // 로그인 실패
            return "redirect:/login";

        }

    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout() {

        session.invalidate(); // 세션 초기화

        return "redirect:/";

    }

}
