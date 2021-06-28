package Spring.web;

import Spring.service.BoardService;
import Spring.web.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시판 페이지 요청
    @GetMapping("/board")
    public String board(Model model) {

        List<BoardDto> boardDtos = boardService.list();

        model.addAttribute("boardDtos", boardDtos);

        return "board";

    }

    // 게시판 등록 화면 요청
    @GetMapping("/boardwrite")
    public String boardwrite() {

        return "boardwrite";

    }

    // 게시물 등록 처리
    @PostMapping("/boardwrite")
    public String boardwrite_c(BoardDto boardDto) {

        System.out.println(boardDto.getName());

        boardService.boardsave(boardDto);

        return "redirect:/board";

    }

}
