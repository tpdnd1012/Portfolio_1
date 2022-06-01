package Spring.web;

import Spring.service.BoardService;
import Spring.service.BoardreplyService;
import Spring.web.dto.BoardDto;
import Spring.web.dto.BoardreplyDto;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
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

        boardreplyService.replysave(boardreplyDto);

        re.addAttribute("id", boardreplyDto.getBoardid());
        re.addAttribute("count", -1);

        return "redirect:/boardview";

    }

    // 게시판 댓글 삭제
    @ResponseBody
    @RequestMapping(value = "/replydel", method = RequestMethod.POST)
    public String replydel(Model model, BoardreplyDto boardreplyDto, RedirectAttributes re) {

        boolean result = boardreplyService.replydelete(boardreplyDto.getId());

        re.addAttribute("id", boardreplyDto.getBoardid());
        re.addAttribute("count", -1);

        List<BoardreplyDto> boardreplyDtos = boardreplyService.boardreplyDtoList(boardreplyDto.getId());
        model.addAttribute("replyDto", boardreplyDtos);

        return result ? "댓글이 삭제되었습니다." : "실패...관리자에게 문의하세요.";
        //return "";

    }

    // 게시판 댓글 수정
    @ResponseBody
    @RequestMapping(value = "/replymodify", method = RequestMethod.POST)
    public String replymodify(Model model, BoardreplyDto boardreplyDto) {

        String contents = boardreplyDto.getReplycontents();

        boardreplyDto.setReplycontents(contents.replace("\r\n", "<br>"));

        boolean result = boardreplyService.replymodify(boardreplyDto);

        JSONObject jo = new JSONObject();

        if(result) {

            String getcontents = boardreplyService.getcontents(boardreplyDto.getId());

            jo.put("msg", "댓글 수정이 완료되었습니다.");
            jo.put("contents", getcontents);

            return jo.toString();

        }
        jo.put("msg", "관리자에게 문의해주세요.");

        return jo.toString();
    }

}
