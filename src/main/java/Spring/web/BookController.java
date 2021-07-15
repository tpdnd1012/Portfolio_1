package Spring.web;

import Spring.service.BookService;
import Spring.web.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 제품 리스트 이동
    @GetMapping("/booklist")
    public String booklist(Model model) {

        List<BookDto> bookDto = bookService.bookDtoList();

        model.addAttribute("bookDto", bookDto);

        return "booklist";

    }

    // 제품 개별 상세페이지 이동
    @GetMapping("/bookview/{id}")
    public String bookview(@PathVariable Long id, Model model) {

        BookDto bookDto = bookService.bookget(id);

        model.addAttribute("bookDto", bookDto);

        return "bookview";

    }

}
