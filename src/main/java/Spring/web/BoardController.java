package Spring.web;

import Spring.domain.board.BoardEntity;
import Spring.domain.boardreply.BoardreplyRepository;
import Spring.service.BoardService;
import Spring.service.BoardreplyService;
import Spring.web.dto.BoardDto;
import Spring.web.dto.BoardreplyDto;
import Spring.web.dto.BoardupdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardreplyService boardreplyService;
    private final BoardreplyRepository boardreplyRepository;
    private final HttpSession session;

    // 게시판 페이지 요청[페이징처리o]
    @GetMapping("/board")
    public String board(Model model, @PageableDefault Pageable pageable, HttpServletRequest request) {

        String keyword = request.getParameter("keyword");
        String search = request.getParameter("search");

        if(search != null) {

            session.setAttribute("se", search);
            session.setAttribute("se2", keyword);

        } else {

            session.setAttribute("se", search);
            session.setAttribute("se2", keyword);

        }

        Page<BoardEntity> boardEntities = boardService.boardlist(pageable, keyword, search);


        model.addAttribute("boardDtos", boardEntities);

        return "board";

    }

    // 게시판 페이지 요청[페이징처리x]
    /*@GetMapping("/board")
    public String board(Model model) {

        List<BoardDto> boardDtos = boardService.list();

        model.addAttribute("boardDtos", boardDtos);

        return "board";

    }*/

    // 게시판 등록 화면 요청
    @GetMapping("/boardwrite")
    public String boardwrite() {

        return "boardwrite";

    }

    // 게시물 등록 처리
    @PostMapping("/boardwrite")
    public String boardwrite_c(BoardDto boardDto, HttpServletRequest request) {

        String contents = request.getParameter("contents");

        boardDto.setContents(contents.replace("\r\n", "<br>"));

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

        // 댓글 출력하기
        List<BoardreplyDto> boardreplyDtos = boardreplyService.boardreplyDtoList(boardDto.getId());
        model.addAttribute("replyDto", boardreplyDtos);

        return "boardview";

    }

    // 게시글 삭제
    @RequestMapping(value = "/boarddelete")
    public String boarddelete(@RequestParam("id") Long id) {

        boardService.boarddelete(id);

        return "redirect:/board";

    }

    // 게시글 수정 페이지 요청
    @GetMapping("/boardmodify/{id}")
    public String boardmodify(@PathVariable Long id, Model model) {

        BoardDto boardDto = boardService.boardget(id);

        String contents = boardDto.getContents().replace("<br>", "\r\n");

        boardDto.setContents(contents);

        model.addAttribute("boardDto", boardDto);

        return "boardmodify";
    }

    // 게시글 수정 처리
    @PostMapping("/boardmodify")
    public String boardmodify_c(BoardupdateDto modifyDto, HttpServletRequest request, RedirectAttributes re) {

        String contents = request.getParameter("contents");

        modifyDto.setContents(contents.replace("\r\n", "<br>"));

        boardService.boardmodify(modifyDto);

        re.addAttribute("id", modifyDto.getId());
        re.addAttribute("count", -1);

        return "redirect:/boardview";

    }

    // 게시물 검색 처리
    @PostMapping("/boardsearch")
    public String boardsearch_c(HttpServletRequest request, @PageableDefault Pageable pageable, Model model) {

        String keyword = request.getParameter("keyword");
        String search = request.getParameter("search");

        // 검색이 없으면
        if(search.equals("")) {

            return "redirect:/board";

        }

        Page<BoardEntity> boardEntities = boardService.boardlist(pageable, keyword, search);

        model.addAttribute("boardDtos", boardEntities);

        return "board";

    }

}
