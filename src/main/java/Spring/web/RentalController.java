package Spring.web;

import Spring.web.dto.CartDto;
import Spring.web.dto.MemberDto;
import Spring.web.dto.RentalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class RentalController {

    private final HttpSession session;

    // 장바구니에서 대여하기 페이지 이동
    @GetMapping("/rental")
    public String rental(Model model, RentalDto rentalDto) {

        // 주문자 세션 넘겨주기
        MemberDto memberDto = (MemberDto) session.getAttribute("loginuser");

        String phone = memberDto.getPhone();
        String email = memberDto.getEmail();
        String address = memberDto.getAddress();

        String[] rentalphone = phone.split("-");
        String[] rentalemail = email.split("@");
        String[] rentaladdress = address.split("-");

        rentalDto.setNo(memberDto.getNo());
        rentalDto.setName(memberDto.getName());

        rentalDto.setPhone1(rentalphone[0]);
        rentalDto.setPhone2(rentalphone[1]);
        rentalDto.setPhone3(rentalphone[2]);

        rentalDto.setEmail1(rentalemail[0]);
        rentalDto.setEmail2(rentalemail[1]);

        rentalDto.setAddress1(rentaladdress[0]);
        rentalDto.setAddress2(rentaladdress[1]);
        rentalDto.setAddress3(rentaladdress[2]);
        rentalDto.setAddress4(rentaladdress[3]);


        ArrayList<CartDto> list = (ArrayList<CartDto>) session.getAttribute("list");

        int total = 0; // 총 금액
        int number = 0;

        for(int i = 0; i < list.size(); i++) {

            total += list.get(i).getMoney();
            number++;

        }

        int point = 0;

        point = memberDto.getPoint();

        model.addAttribute("rentalDto", rentalDto);
        model.addAttribute("total", total);
        model.addAttribute("number", number);
        model.addAttribute("point", point);

        return "payment/rental";

    }

    // 대여완료
    @PostMapping("/rental")
    public String rental() {

        return "/";

    }

}
