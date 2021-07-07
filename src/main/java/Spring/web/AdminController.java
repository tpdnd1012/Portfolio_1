package Spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    // 관리자페이지
    @GetMapping("/admin")
    public String admin() {

        return "admin";

    }

    // 제품관리 페이지
    @GetMapping("/bookmanagement")
    public String bookmanagement() {

        return "bookmanagement";

    }

}
