package Spring.web;

import Spring.domain.member.MemberEntity;
import Spring.service.MemberService;
import Spring.web.dto.MemberDto;
import Spring.web.dto.UpdateDto;
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

        String email = email1 + "@" + email2;

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
    public String memberinfo(HttpServletRequest request, Model model) {

        String member_id = request.getParameter("member_id");
        String member_pw = request.getParameter("member_pw");

        MemberEntity infouser = memberService.memberinfo(member_id, member_pw);

        model.addAttribute("infouser", infouser);

        return "memberinfo";

    }

    // 회원탈퇴
    @GetMapping("/memberdelete")
    public String memberdelete() {

        // 세션 가져오기
        MemberDto memberDto = (MemberDto) session.getAttribute("loginuser");

        // 세션의 회원번호 가져오기
        Long no = memberDto.getNo();

        // 삭제 서비스 넘기기
        memberService.memberdelete(no);

        // 세션 초기화
        session.invalidate();

        return "redirect:/";

    }

    // 마지막 수정 페이지 요청
    @RequestMapping(value = "/infowrite", method = RequestMethod.POST)
    public String infowrite(MemberDto memberDto,UpdateDto updateDto, Model model) {

        String[] phone = memberDto.getPhone().split("-");
        String[] email = memberDto.getEmail().split("@");
        String[] address = memberDto.getAddress().split("-");

        updateDto.setPhone1(phone[0]);
        updateDto.setPhone2(phone[1]);
        updateDto.setPhone3(phone[2]);

        updateDto.setEmail1(email[0]);
        updateDto.setEmail2(email[1]);

        updateDto.setAddress1(address[0]);
        updateDto.setAddress2(address[1]);
        updateDto.setAddress3(address[2]);
        updateDto.setAddress4(address[3]);

        model.addAttribute("updateuser", updateDto);

        return "infowrite";

    }

}
