package Spring.service;

import Spring.domain.book.BookEntity;
import Spring.domain.book.BookRepository;
import Spring.web.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // 도서 삭제
    @Transactional
    public void bookdelete(Long id) {

        // 1. 엔티티 찾기
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(id);

        // 2. 엔티티 가져오기
        BookEntity bookEntity = optionalBookEntity.get();

        // 3. 삭제 처리
        bookRepository.delete(bookEntity);

    }

    // 수정할 개별 도서 찾기
    public BookDto bookget(Long id) {

        // 1. 엔티티 찾기
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(id);

        // 2. 엔티티 가져오기
        BookEntity bookEntity = optionalBookEntity.get();

        // 3. Entity --> Dto
        return BookDto.builder()
                .id(bookEntity.getId())
                .images(bookEntity.getImages())
                .name(bookEntity.getName())
                .author(bookEntity.getAuthor())
                .genre(bookEntity.getGenre())
                .publisher(bookEntity.getPublisher())
                .publishing(bookEntity.getPublishing())
                .reservation(bookEntity.getReservation())
                .money(bookEntity.getMoney())
                .contents(bookEntity.getContents())
                .createDate(bookEntity.getCreateDate()).build();

    }

    // 제품 수정 처리
    @Transactional
    public void bookmodify(BookDto modifyDto) {

        // 1. 해당 엔티티 찾기
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(modifyDto.getId());

        // 2. 엔티티 가져오기
        BookEntity bookEntity = optionalBookEntity.get();

        // 3. 업데이트 처리
        bookEntity.modify(modifyDto);

    }

}