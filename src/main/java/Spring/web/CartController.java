package Spring.web;

import Spring.service.BookService;
import Spring.web.dto.BookDto;
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
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final HttpSession session;
    private final BookService bookService;

    // 장바구니 이동 페이지
    @GetMapping("/cart")
    public String cart(Model model) {

        /*ArrayList<CartDto> list = (ArrayList<CartDto>) session.getAttribute("list");

        if(list == null) {

            list = new ArrayList<>();

        }

        int total = 0;

        for(int i = 0; i < list.size(); i++) {

            total += list.get(i).getMoney();

        }

        model.addAttribute("total", total);*/

        return "member/cart";

    }

    // 장바구니 담기
    @RequestMapping(value = "/cartadd", method = RequestMethod.POST)
    public String cartadd(CartDto cartDto, Model model, HttpServletResponse response) {

        ArrayList<CartDto> list = (ArrayList<CartDto>) session.getAttribute("list");

        if(list == null) { // 장바구니 세션이 비었을 경우 새로운 ArrayList 생성 후 제품 담기

            list = new ArrayList<>();

            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = null;

            try {

                out = response.getWriter();

            } catch (IOException e1) {

                e1.printStackTrace();

            }

            out.println("<script>alert('장바구니에 제품을 담았습니다.'); location.href='booklist';</script>");

            out.flush();


        } else { // 장바구니 세션에 똑같은 제품이 있을 경우 ArrayList x

            for (int i = 0; i < list.size(); i++) {

                if (list.get(i).getId() == cartDto.getId()) {

                    response.setContentType("text/html; charset=UTF-8");

                    PrintWriter out = null;

                    try {

                        out = response.getWriter();

                    } catch (IOException e2) {

                        e2.printStackTrace();

                    }

                    out.println("<script>alert('이미 등록된 제품입니다.'); location.href='booklist';</script>");

                    out.flush();

                    return "redirect:/booklist";

                }
            }
        }

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = null;

        try {

            out = response.getWriter();

        } catch (IOException e1) {

            e1.printStackTrace();

        }

        out.println("<script>alert('장바구니에 제품을 담았습니다.'); location.href='booklist';</script>");

        out.flush();

        list.add(cartDto);

        session.setAttribute("list", list);

        return "redirect:/booklist";

    }

    // 장바구니 삭제
    @RequestMapping("/cartdelete/{id}")
    public String cartdelete(@PathVariable Long id, HttpServletResponse response) {

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
