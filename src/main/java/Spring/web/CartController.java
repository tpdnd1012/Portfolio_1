package Spring.web;

import Spring.web.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.h2.engine.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final HttpSession session;

    // 장바구니 이동 페이지
    @GetMapping("/cart")
    public String cart(Model model) {

        ArrayList<CartDto> list = (ArrayList<CartDto>) session.getAttribute("list");

        if(list == null) {

            list = new ArrayList<>();

        }

        int total = 0;

        for(int i = 0; i < list.size(); i++) {

            total += list.get(i).getMoney();

        }

        model.addAttribute("total", total);

        return "cart";

    }

    // 장바구니 담기
    @RequestMapping(value = "/cartadd", method = RequestMethod.POST)
    public String cartadd(CartDto cartDto, Model model) {

        ArrayList<CartDto> list = (ArrayList<CartDto>) session.getAttribute("list");

        if(list == null) {

            list = new ArrayList<>();

        }

        list.add(cartDto);

        session.setAttribute("list", list);

        return "redirect:/booklist";

    }

    // 장바구니 삭제
    @RequestMapping("/cartdelete/{id}")
    public String cartdelete(@PathVariable Long id) {

        ArrayList<CartDto> list = (ArrayList<CartDto>) session.getAttribute("list");

        for(int i = 0; i < list.size(); i++) {

            if(list.get(i).getId() == id) {

                list.remove(i);

                session.setAttribute("list", list);

            }

        }
        return "redirect:/cart";
    }

}
