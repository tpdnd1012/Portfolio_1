package Spring.web;

import Spring.domain.member.MemberEntity;
import Spring.service.MemberService;
import Spring.web.dto.MemberDto;
import Spring.web.dto.MemberupdateDto;
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
    @GetMapping("signup")
    public String signup() {

        return "member/signup";

    }

    // 로그인 페이지 요청
    @GetMapping("/login")
    public String login() {

        return "member/login";

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
        memberDto.setPoint(1000);

        memberService.membersave(memberDto);

        return "member/completesignup";

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
            return "member/login";
        }

    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout() {

        session.invalidate(); // 세션 초기화

        return "member/login";

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
        return "member/signup :: #resultDiv";
    }

    // 회원수정 전 페이지 요청
    @GetMapping("info")
    public String info() {

        return "member/info";

    }

    // 회원 수정 비밀번호 변경 페이지 요청
    @GetMapping("/updatepw/{no}")
    public String updatepw(@PathVariable("no") Long no, Model model) {

        MemberEntity memberEntity = memberService.updatepw(no);

        model.addAttribute("member",memberEntity);

        return "member/updatepw";

    }

    // 비밀번호 수정 처리
    @PostMapping("updatepwcomplete")
    public String updatepwcomplete(HttpServletRequest request) {

        Long no = Long.parseLong(request.getParameter("no"));
        String member_pw = request.getParameter("member_pw");

        memberService.updatepwcomplete(no, member_pw);

        session.invalidate();

        return "member/updatecompletepw";
        
    }

    // 회원수정 전 페이지 비밀번호 입력후 수정 전 확인페이지
    @PostMapping("info")
    public String memberinfo(HttpServletRequest request, Model model) {

        Long no = Long.parseLong(request.getParameter("no"));
        String member_pw = request.getParameter("member_pw");

        MemberEntity infouser = memberService.memberinfo(no, member_pw);

        String c = "0";

        if(infouser != null) {

            model.addAttribute("infouser", infouser);

            return "member/memberinfo";

        } else {

            model.addAttribute("c", "1");

            return "member/info";

        }

    }

    // 마지막 수정 페이지 요청
    @RequestMapping(value = "/updateinfo", method = RequestMethod.POST)
    public String updateinfo(MemberupdateDto updateDto, HttpServletRequest request, Model model) {

        String updatephone = request.getParameter("phone");
        String updateemail = request.getParameter("email");
        String updateaddress = request.getParameter("address");


        String[] phone = updatephone.split("-");
        String[] email = updateemail.split("@");
        String[] address = updateaddress.split("-");

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

        return "member/updateinfo";

    }

    // 회원정보 수정 처리 완료
    @RequestMapping(value = "/updatecomplete", method = RequestMethod.POST)
    public String updatecomplete(MemberDto memberDto, HttpServletRequest request) {

        String phone1 = request.getParameter("phone1");
        String phone2 = request.getParameter("phone2");
        String phone3 = request.getParameter("phone3");

        String email1 = request.getParameter("email1");
        String email2 = request.getParameter("email2");

        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String address3 = request.getParameter("address3");
            if (address3 == null) {
                address3 = " ";
            }
        String address4 = request.getParameter("address4");

        String phone = phone1 + "-" + phone2 + "-" + phone3;
        String email = email1 + "@" + email2;
        String address = address1 + "-" + address2 + "-" + address3 + "-" + address4;

        memberDto.setPhone(phone);
        memberDto.setEmail(email);
        memberDto.setAddress(address);

        memberService.updateinfo(memberDto);

        session.invalidate(); // 업데이트후 세션 초기화

        session.setAttribute("loginuser", memberDto); // 초기화 후 로그인 메소드처럼 세션 다시 부여

        return "index";

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

    // 아이디 찾기 페이지요청
    @GetMapping("findid")
    public String findid() {

        return "member/findid";

    }

    // 비밀번호 찾기 페이지요청
    @GetMapping("findpw")
    public String findpw() {

        return "member/findpw";

    }

    // 아이디 찾기
    @PostMapping("findid")
    public String findid_c(HttpServletRequest request, Model model) {

        String name = request.getParameter("name");
        String birth = request.getParameter("birth");

        String phone1 = request.getParameter("phone1");
        String phone2 = request.getParameter("phone2");
        String phone3 = request.getParameter("phone3");

        String phone = phone1 + "-" + phone2 + "-" + phone3;

        MemberEntity findid = memberService.findid(name, birth, phone);

        if(findid != null) {

            model.addAttribute("findid", findid);

            return "member/findidinfo";

        } else {

            String result = "동일한 정보의 회원이 없습니다.";

            model.addAttribute("result", result);

            return "member/findid";

        }

    }

    // 비밀번호 찾기
    @PostMapping("findpw")
    public String findpw_c(HttpServletRequest request, Model model) {

        String member_id = request.getParameter("member_id");
        String birth = request.getParameter("birth");

        String email1 = request.getParameter("email1");
        String email2 = request.getParameter("email2");

        String email = email1 + "@" + email2;

        MemberEntity findpw = memberService.findpw(member_id, birth, email);

        if(findpw != null) {

            model.addAttribute("findpw", findpw);

            return "member/modifypw";

        } else {

            String result = "동일한 정보의 회원이 없습니다.";

            model.addAttribute("result", result);

            return "member/findpw";

        }

    }

    // 비밀번호 찾기 후 바로 새 비밀번호로 변경
    @PostMapping("/modifypw")
    public String modifypw(HttpServletRequest request) {

        Long no = Long.parseLong(request.getParameter("no"));
        String member_pw = request.getParameter("member_pw");

        memberService.modifypw(no, member_pw);

        return "member/completepw";

    }

}
