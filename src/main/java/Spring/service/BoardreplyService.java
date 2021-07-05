package Spring.service;

import Spring.domain.boardreply.BoardreplyEntity;
import Spring.domain.boardreply.BoardreplyRepository;
import Spring.web.dto.BoardreplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardreplyService {

    private final BoardreplyRepository boardreplyRepository;

    private final BoardService boardService;

    // 게시판 댓글 저장
    @Transactional
    public void replysave(BoardreplyDto boardreplyDto) {

        // Repository.save 메소드로 DTO --> 만들어 놓은 toEntity로 저장
        boardreplyRepository.save(boardreplyDto.toEntity());

    }

    // 댓글 출력
    public List<BoardreplyDto> boardreplyDtoList(Long boardid) {

        List<BoardreplyEntity> boardreplyEntities = boardreplyRepository.findByboardid(boardid);

        List<BoardreplyDto> boardreplyDtos = new ArrayList<>();

        for(BoardreplyEntity entity : boardreplyEntities) {

            // Entity ---> Dto
            BoardreplyDto boardreplyDto = BoardreplyDto.builder()
                    .id(entity.getId())
                    .boardid(entity.getBoardid())
                    .no(entity.getNo())
                    .replycontents(entity.getReplycontents())
                    .name(entity.getName())
                    .createDate(entity.getCreateDate()).build();

            // Dto 리스트 담기
            boardreplyDtos.add(boardreplyDto);

        }
        return boardreplyDtos;
    }

}
