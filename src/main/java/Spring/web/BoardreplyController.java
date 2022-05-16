package Spring.web;

import Spring.service.BoardService;
import Spring.service.BoardreplyService;
import Spring.web.dto.BoardDto;
import Spring.web.dto.BoardreplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardreplyController {

    private final BoardreplyService boardreplyService;

    private final BoardService boardService;

    // 게시판 댓글 저장
    @RequestMapping("/replywrite")
    public String replywrite(BoardreplyDto boardreplyDto, HttpServletRequest request, RedirectAttributes re,
                             @RequestParam Long boardid, @RequestParam int rcount) {

        String replycontents = request.getParameter("replycontents");

        boardreplyDto.setReplycontents(replycontents.replace("\r\n", "<br>"));

        if(rcount != -1) {

            boardService.rcountup(boardid);

        }

        boardreplyService.replysave(boardreplyDto);

        re.addAttribute("id", boardreplyDto.getBoardid());
        re.addAttribute("count", -1);

        return "redirect:/boardview";

    }

    // 게시판 댓글 삭제
    /*@RequestMapping(value = "/replydelete")
    public String replydelete (@RequestParam("id") Long id, @RequestParam("boardid") Long boardid,
                               @RequestParam("rcount") int rcount, RedirectAttributes re) {

        if(rcount != -1) {
            boardService.rcountdown(boardid);
        }

        boardreplyService.replydelete(id);

        re.addAttribute("id", boardid);
        re.addAttribute("count", -1);

        return "redirect:/boardview";

    }*/

    @ResponseBody
    @RequestMapping(value = "/replydel", method = RequestMethod.POST)
    public String replydel(Model model, BoardreplyDto boardreplyDto, BoardDto boardDto, RedirectAttributes re) {

        if(boardDto.getRcount() != -1) {
            boardService.rcountdown(boardreplyDto.getBoardid());
        }

        boolean result = boardreplyService.replydelete(boardreplyDto.getId());

        re.addAttribute("id", boardreplyDto.getBoardid());
        re.addAttribute("count", -1);

        List<BoardreplyDto> boardreplyDtos = boardreplyService.boardreplyDtoList(boardDto.getId());
        model.addAttribute("replyDto", boardreplyDtos);

        return result ? "댓글이 삭제되었습니다." : "실패...관리자에게 문의하세요.";
        //return "";

    }

}
