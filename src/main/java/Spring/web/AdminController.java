package Spring.web;

import Spring.service.AdminService;
import Spring.web.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
    public String bookmanagement(Model model) {

        List<BookDto> bookDtos = adminService.booklist();

        model.addAttribute("bookDtos", bookDtos);

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
            e.printStackTrace();
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

        return "redirect:/bookmanagement";

    }

    // 관리자페이지 제품 삭제 메소드
    @GetMapping("/bookdelete/{id}")
    public String bookdelete(@PathVariable Long id) {

        adminService.bookdelete(id);

        return "redirect:/bookmanagement";

    }

    // 관리자페이지 제품 수정 메소드
    @GetMapping("/bookmodify/{id}")
    public String bookmodify(@PathVariable Long id, Model model) {

        // Dto에 수정할 개별 도서 데이터 담기
        BookDto bookDto = adminService.bookget(id);

        // Model로 넘겨주기
        model.addAttribute("bookDto", bookDto);

        return "bookmodify";

    }

    // 관리자 제품 수정 처리
    @RequestMapping(value = "/bookmodify", method = RequestMethod.POST)
    public String bookmodify_c(
            @RequestParam("images") MultipartFile file,
            MultipartFile modifyimg, HttpServletRequest request) {


        String file2 = request.getParameter("images2");

        System.out.println(file2);

        // 이미지 저장 [ 업로드 ]
        String upload = "/Users/kimse-ung/IdeaProjects/Portfolio_1/src/main/resources/static/bookimages";
        // : / : 제어문 (\n, \t)
        // : \\ : 경로

        String fileupload = "";

        // 업로드 : 파일경로 해당하는 파일은 객체화
        try{

            if(file == null) {

                fileupload = upload + "/" + file2;

            } else {

                fileupload = upload + "/" + file.getOriginalFilename();

            }

            modifyimg.transferTo(new File(fileupload));

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*BookDto bookDto = BookDto.builder()
                .id(Long.parseLong(request.getParameter("id")))
                .images(modifyimg.getOriginalFilename())
                .name(request.getParameter("name"))
                .author(request.getParameter("author"))
                .genre(request.getParameter("genre"))
                .publisher(request.getParameter("publisher"))
                .publishing(request.getParameter("publishing"))
                .reservation(Integer.parseInt(request.getParameter("reservation")))
                .money(Integer.parseInt(request.getParameter("money"))).build();*/

        /*adminService.bookmodify(bookDto);*/

        return "redirect:/bookmanagement";

    }

}
