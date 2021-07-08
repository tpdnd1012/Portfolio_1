package Spring.web;

import Spring.service.AdminService;
import Spring.web.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

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

    // 제품 추가 페이지
    @GetMapping("/bookadd")
    public String bookadd() {

        return "/bookadd";

    }

    // 책 등록 처리
    @PostMapping("/bookadd")
    public String bookadd(@RequestParam("images") MultipartFile file, HttpServletRequest request) {

        // 이미지 저장 [ 업로드 ]
        String upload = "/Users/kimse-ung/IdeaProjects/Portfolio_1/src/main/resources/static/bookimages";
        // : / : 제어문 (\n, \t)
        // : \\ : 경로

        String fileupload = upload + "/" + file.getOriginalFilename();

        // 업로드 : 파일경로 해당하는 파일은 객체화
        try{

            file.transferTo(new File(fileupload));

        } catch (IOException e) {
            e.printStackTrace();;
        }

        BookDto bookDto = BookDto.builder()
                .images(file.getOriginalFilename())
                .name(request.getParameter("name"))
                .author(request.getParameter("author"))
                .genre(request.getParameter("genre"))
                .publisher(request.getParameter("publisher"))
                .publishing(request.getParameter("publishing"))
                .reservation(Integer.parseInt(request.getParameter("reservation")))
                .money(Integer.parseInt(request.getParameter("money"))).build();

        adminService.booksave(bookDto);

        return "bookmanagement";

    }

}
