package Spring.service;

import Spring.domain.book.BookEntity;
import Spring.domain.book.BookRepository;
import Spring.web.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final BookRepository bookRepository;

    // 도서 추가
    public void booksave(BookDto bookDto) {

        bookRepository.save(bookDto.toEntity());

    }

    // 모든 도서리스트
    public List<BookDto> booklist() {

        List<BookEntity> bookEntities = bookRepository.findAll();

        List<BookDto> bookDtos = new ArrayList<>();

        for(BookEntity temp : bookEntities) {

            BookDto bookDto = BookDto.builder()
                    .id(temp.getId())
                    .images(temp.getImages())
                    .name(temp.getName())
                    .author(temp.getAuthor())
                    .genre(temp.getGenre())
                    .publisher(temp.getPublisher())
                    .publishing(temp.getPublishing())
                    .reservation(temp.getReservation())
                    .money(temp.getMoney())
                    .createDate(temp.getCreateDate()).build();

            bookDtos.add(bookDto);

        }
        return bookDtos;
    }

}
