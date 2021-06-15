package Spring.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public String index() {

        UserEntity userEntity = UserEntity.builder()
                .userid("qwe")
                .password("123").build();

        userRepository.save(userEntity);

        return "index";

    }

}
