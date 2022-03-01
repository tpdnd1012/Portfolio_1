package Spring.web;

import Spring.service.BookService;
import Spring.web.dto.BookDto;
import Spring.web.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final HttpSession session;

    // 제품 리스트 이동
    @GetMapping("/booklist")
    public String booklist(Model model) {

        List<BookDto> bookDto = bookService.bookDtoList();

        model.addAttribute("bookDto", bookDto);

        return "book/booklist";

    }

    // 제품 개별 상세페이지 이동
    @RequestMapping(value = "/bookview")
    public String bookview(Model model, @RequestParam("id") Long id) {

        BookDto bookDto = bookService.bookget(id);

        model.addAttribute("bookDto", bookDto);

        return "book/bookview";

    }

    // 제품 상세페이지 장바구니
    @RequestMapping(value = "/bookviewcart", method = RequestMethod.POST)
    public String bookviewcart(CartDto cartDto, @RequestParam("id") Long id,
                               RedirectAttributes re, HttpServletResponse response) {

        ArrayList<CartDto> list = (ArrayList<CartDto>) session.getAttribute("list");

        if (list == null) {

            list = new ArrayList<>();

        } else {

            for(int i = 0; i < list.size(); i++) {

                if(list.get(i).getId() == cartDto.getId()) {

                    response.setContentType("text/html; charset=UTF-8");

                    PrintWriter out = null;

                    try{

                        out = response.getWriter();

                    }catch (IOException e) {

                        e.printStackTrace();

                    }

                    out.println("<script>alert('이미 등록된 제품입니다.'); history.back()</script>");

                    out.flush();

                    return "redirect:/bookview";

                }

            }

        }

        list.add(cartDto);

        session.setAttribute("list", list);

        re.addAttribute("id", id);

        return "redirect:/bookview";
    }

}
