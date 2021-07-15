package Spring.web;

import Spring.web.dto.CartDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String cart() {

        return "cart";

    }

    @RequestMapping(value = "/cartadd", method = RequestMethod.POST)
    public String cartadd(CartDto cartDto) {

        System.out.println("회원번호 : " + cartDto.getNo());
        System.out.println("도서번호 : " + cartDto.getId());
        System.out.println("도서이름 : " + cartDto.getName());
        System.out.println("도서이미지 : " + cartDto.getImages());
        System.out.println("대여금액 : " + cartDto.getMoney());

        return "redirect:booklist";

    }

}
