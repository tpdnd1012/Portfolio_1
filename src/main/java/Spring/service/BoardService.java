package Spring.service;

import Spring.domain.board.BoardEntity;
import Spring.domain.board.BoardRepository;
import Spring.web.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

}
