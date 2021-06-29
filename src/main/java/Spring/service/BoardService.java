package Spring.service;

import Spring.domain.board.BoardEntity;
import Spring.domain.board.BoardRepository;
import Spring.web.dto.BoardDto;
import Spring.web.dto.BoardupdateDto;
import lombok.RequiredArgsConstructor;
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

    // 모든 게시물 출력
    public List<BoardDto> list() {

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
    }

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
