package Spring.service;

import Spring.domain.book.BookEntity;
import Spring.domain.book.BookRepository;
import Spring.web.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // 대여가능한 책 출력
    public List<BookDto> bookDtoList() {

        List<BookEntity> bookEntities = bookRepository.findBylist();

        List<BookDto> bookDtoList = new ArrayList<>();

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
                    .money(temp.getMoney()).build();

            bookDtoList.add(bookDto);

        }
        return bookDtoList;
    }

    // 제품 개별 상세페이지 출력
    public BookDto bookget(Long id) {

        // 1. 해당 ID의 엔티티 찾기
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(id);

        // 2. 찾은 엔티티 가져오기
        BookEntity temp = optionalBookEntity.get();

        // 3. DTO 변환
        return BookDto.builder()
                .id(temp.getId())
                .images(temp.getImages())
                .name(temp.getName())
                .author(temp.getAuthor())
                .genre(temp.getGenre())
                .publisher(temp.getPublisher())
                .publishing(temp.getPublishing())
                .reservation(temp.getReservation())
                .money(temp.getMoney())
                .contents(temp.getContents())
                .createDate(temp.getCreateDate()).build();

    }

}
