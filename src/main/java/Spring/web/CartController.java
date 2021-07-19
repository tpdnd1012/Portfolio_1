package Spring.web;

import Spring.web.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.h2.engine.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final HttpSession session;

    @GetMapping("/cart")
    public String cart() {

        return "cart";

    }

    // 장바구니 담기
    @RequestMapping(value = "/cartadd", method = RequestMethod.POST)
    public String cartadd(CartDto cartDto) {

        ArrayList<CartDto> list = (ArrayList<CartDto>) session.getAttribute("list");

        if(list == null) {

            list = new ArrayList<>();

        }

        list.add(cartDto);

        session.setAttribute("list", list);

        return "cart";

    }

}
