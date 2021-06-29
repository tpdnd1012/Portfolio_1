package Spring.web;

import Spring.service.BoardService;
import Spring.web.dto.BoardDto;
import Spring.web.dto.BoardupdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        boardService.boardsave(boardDto);

        return "redirect:/board";

    }

    // 게시물 클릭 시 상세페이지 요청
    @RequestMapping(value = "/boardview", method = RequestMethod.GET)
    public String boardview(@RequestParam("id") Long id ,@RequestParam("count") int count, Model model) {

        // 조회수 처리
        if(count != -1) {
            boardService.countup(id);
        }

        // 해당 게시물 출력
        BoardDto boardDto = boardService.boardget(id);
        model.addAttribute("boardDto", boardDto);

        return "boardview";

    }

    @GetMapping("/boarddelete/{id}")
    public String boarddelete(@PathVariable Long id) {

        boardService.boarddelete(id);

        return "redirect:/board";

    }

    // 게시글 수정 페이지 요청
    @GetMapping("/boardmodify/{id}")
    public String boardmodify(@PathVariable Long id, Model model) {

        BoardDto boardDto = boardService.boardget(id);

        model.addAttribute("boardDto", boardDto);

        return "boardmodify";
    }

    // 게시글 수정 처리
    @PostMapping("/boardmodify")
    public String boardmodify_c(BoardupdateDto modifyDto) {

        boardService.boardmodify(modifyDto);

        return "redirect:/board";

    }

}
