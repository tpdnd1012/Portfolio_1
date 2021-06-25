package Spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    // 게시판 페이지 요청
    @GetMapping("/board")
    public String board() {

        return "board";

    }

    // 게시판 등록 화면 요청
    @GetMapping("/boardwrite")
    public String boardwrite() {

        return "boardwrite";

    }

}
