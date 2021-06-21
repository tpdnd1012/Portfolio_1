package Spring.web;

import Spring.domain.member.MemberEntity;
import Spring.service.MemberService;
import Spring.web.dto.MemberDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
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

        if (address3 == null) {
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
    public String login_c(MemberDto logindto, Model model) {

        // 로그인 서비스 연결
        MemberDto memberDto = memberService.memberlogin(logindto);

        String c = "0";

        if (memberDto != null) {

            // 로그인 성공 => 세션에 담기
            session.setAttribute("loginuser", memberDto);

            return "index";

        } else {
            // 로그인 실패
            model.addAttribute("c", "1");
            return "login";
        }

    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout() {

        session.invalidate(); // 세션 초기화

        return "redirect:/login";

    }

//    // 아이디 중복 검사
//    @PostMapping("/ID_Check/{id}")
//    public String memberIdChkPOST( @PathVariable("id") String id , Model model ) {
//
//        int re = memberService.memberfind(id);
//        System.out.println( re );
//
//        return "/testPage :: #signup";
//
//
////        String c = "0";
////        if( re == 1 ){ // 중복 아이디
////            model.addAttribute("c" , "1");
////            System.out.println( re );
////            return "signup";
////        }else{ // 중복x
////            model.addAttribute("c" , "0");
////            System.out.println( re );
////            return "signup";
////        }
//    } // memberIdChkPOST() 종료
//
//}
    // 회원가입 아이디 중복체크
    @RequestMapping(value = "/dataSend", method = RequestMethod.POST)
    public String dataSend(Model model, MemberDto dto) {
        int result = memberService.memberfind( dto.getMember_id() );
        model.addAttribute("msg", result);
        return "signup :: #resultDiv";
    }

    // 회원수정 전 페이지 요청
    @GetMapping("/info")
    public String info() {

        return "info";

    }

    // 회원수정 전 페이지 비밀번호 입력후 수정페이지
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public String memberinfo(HttpServletRequest request) {

        String member_id = request.getParameter("member_id");
        String member_pw = request.getParameter("member_qw");

        memberService.memberinfo(member_id, member_pw);

        return "index";

    }

}
