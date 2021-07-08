package Spring.web;

import Spring.service.BookService;
import Spring.web.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/booklist")
    public String booklist(Model model) {

        List<BookDto> bookDto = bookService.bookDtoList();

        model.addAttribute("bookDto", bookDto);

        return "booklist";

    }

}
