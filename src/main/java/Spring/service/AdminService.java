package Spring.service;

import Spring.domain.book.BookRepository;
import Spring.web.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final BookRepository bookRepository;

    // 도서 추가
    public void booksave(BookDto bookDto) {

        bookRepository.save(bookDto.toEntity());

    }

}
