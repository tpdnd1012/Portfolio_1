package Spring.web;

import Spring.web.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.h2.engine.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final HttpSession session;

    // 장바구니 이동 페이지
    @GetMapping("/cart")
    public String cart(Model model) {

        ArrayList<CartDto> list = (ArrayList<CartDto>) session.getAttribute("list");

        int total = 0;

        for(int i = 0; i < list.size(); i++) {

            total += list.get(i).getMoney();

        }

        model.addAttribute("total", total);

        return "cart";

    }

    // 장바구니 담기
    @RequestMapping(value = "/cartadd", method = RequestMethod.POST)
    public String cartadd(CartDto cartDto, Model model, HttpServletResponse response) {

        ArrayList<CartDto> list = (ArrayList<CartDto>) session.getAttribute("list");

        if(list == null) {

            list = new ArrayList<>();


        } else {

            for (int i = 0; i < list.size(); i++) {

                if (list.get(i).getId() == cartDto.getId()) {

                    response.setContentType("text/html; charset=UTF-8");

                    PrintWriter out = null;

                    try {

                        out = response.getWriter();

                    } catch (IOException e) {

                        e.printStackTrace();

                    }

                    out.println("<script>alert('이미 등록된 제품입니다.'); location.href='booklist';</script>");

                    out.flush();

                    return "redirect:/booklist";

                }
            }
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
