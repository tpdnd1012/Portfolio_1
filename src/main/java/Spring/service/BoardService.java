package Spring.service;

import Spring.domain.board.BoardEntity;
import Spring.domain.board.BoardRepository;
import Spring.web.dto.BoardDto;
import Spring.web.dto.BoardupdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시물 등록 저장
    public void boardsave(BoardDto boardDto) {

        // 인수로 들어온 Dto --> Entity --> save 메소드에 넣어주기
        boardRepository.save(boardDto.toEntity());

    }

    // 모든 게시물 출력(페이징처리o)
    public Page<BoardEntity> boardlist(Pageable pageable, String keyword, String search) {

        // 현재 페이지
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
                                           // 논리 ? 참[T] : 거짓[F]

        // 현재 페이지 설정
        pageable = PageRequest.of(page, 5, new Sort(Sort.Direction.DESC, "id"));
                    // PageRequest.of(현재페이지, 페이지당 게시글수, Sort)

        //현재 페이지의 게시물 찾기
        if(keyword != null || search != null) {

            if(keyword.equals("title")) {
                return boardRepository.findAlltitle(search, pageable );
            }

            if(keyword.equals("contents")) {
                return boardRepository.findAllcontents(search, pageable);
            }

            if(keyword.equals("name")) {
                return boardRepository.findAllname(search, pageable);
            }

            if(keyword.equals("id")) {
                return boardRepository.findAllid(Long.parseLong(search), pageable);
            }

        }

        return boardRepository.findAll(pageable);

    }

    // 모든 게시물 출력(페이징처리x)
    /*public List<BoardDto> list() {

        List<BoardEntity> boardEntityList = boardRepository.findAll();

        List<BoardDto> boardDtos = new ArrayList<>();

        for(BoardEntity temp : boardEntityList) {

            BoardDto boardDto = BoardDto.builder()
                    .id(temp.getId())
                    .title(temp.getTitle())
                    .name(temp.getName())
                    .contents(temp.getContents())
                    .count(temp.getCount())
                    .createDate(temp.getCreateDate()).build();

            boardDtos.add(boardDto);

        }
        return boardDtos;
    }*/

    // 게시물 개별 출력
    public BoardDto boardget(Long id) {

        // 1. 해당 ID의 엔티티 찾기
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);

        // 2. 찾은 엔티티 가져오기
        BoardEntity temp = optionalBoardEntity.get();

        // 3. DTO 변환
        return BoardDto.builder()
                .id(temp.getId())
                .no(temp.getNo())
                .title(temp.getTitle())
                .name(temp.getName())
                .contents(temp.getContents())
                .count(temp.getCount())
                .createDate(temp.getCreateDate()).build();

    }

    // 게시물 클릭시 조회수 처리
    @Transactional
    public void countup(Long id) {

        // 1. 엔티티 찾기
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);

        // 2. 찾은 엔티티 가져오기
        BoardEntity boardEntity = optionalBoardEntity.get();

        // 3. 조회수 증가 메소드 호출
        boardEntity.countup();

    }

    // 게시물 삭제 처리
    @Transactional
    public void boarddelete(Long id) {

        // 1. 엔티티 찾기
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);

        // 2. 찾은 엔티티 가져오기
        BoardEntity boardEntity = optionalBoardEntity.get();

        // 3. 삭제처리
        boardRepository.delete(boardEntity);

    }

    // 게시물 수정 처리
    @Transactional
    public void boardmodify(BoardupdateDto modifyDto) {

        // 1. 해당 엔티티 찾기
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(modifyDto.getId());

        // 2. 엔티티 가져오기
        BoardEntity boardEntity = optionalBoardEntity.get();

        boardEntity.modify(modifyDto);

    }

}
