package Spring.web;

import Spring.service.BoardreplyService;
import Spring.web.dto.BoardreplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class BoardreplyController {

    private final BoardreplyService boardreplyService;

    // 게시판 댓글 저장
    @RequestMapping("/replywrite")
    public String replywrite(BoardreplyDto boardreplyDto, HttpServletRequest request, RedirectAttributes re) {

        String replycontents = request.getParameter("replycontents");

        boardreplyDto.setReplycontents(replycontents.replace("\r\n", "<br>"));

        boardreplyService.replysave(boardreplyDto);

        re.addAttribute("id", boardreplyDto.getBoardid());
        re.addAttribute("count", -1);

        return "redirect:/boardview";

    }

}
