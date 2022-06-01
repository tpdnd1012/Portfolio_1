package Spring.service;

import Spring.domain.board.BoardEntity;
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

    // 게시판 댓글 삭제
    @Transactional
    public boolean replydelete(Long id) {

        List<BoardreplyEntity> boardreplyEntityList = boardreplyRepository.findAll();

        for(BoardreplyEntity temp : boardreplyEntityList) {

            if(temp.getId().equals(id)) {

                // 엔티티 찾기
                Optional<BoardreplyEntity> optionalBoardreplyEntity = boardreplyRepository.findById(id);

                // 엔티티 가져오기
                BoardreplyEntity boardreplyEntity = optionalBoardreplyEntity.get();

                boardreplyRepository.delete(boardreplyEntity);

                return true;

            }

        }

        return false;

    }

    // 댓글 수정
    @Transactional
    public boolean replymodify(BoardreplyDto boardreplyDto) {

        List<BoardreplyEntity> boardreplyEntityList = boardreplyRepository.findAll();

        for(BoardreplyEntity temp : boardreplyEntityList) {

            if(temp.getId().equals(boardreplyDto.getId())) {

                // 1. 해당 엔티티 찾기
                Optional<BoardreplyEntity> optionalBoardreplyEntity = boardreplyRepository.findById(boardreplyDto.getId());

                // 2. 엔티티 가져오기
                BoardreplyEntity boardreplyEntity = optionalBoardreplyEntity.get();

                // 3. 수정 처리
                boardreplyEntity.replymodify(boardreplyDto);

                return true;

            }

        }

        return false;

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

    public String getcontents(Long id) {

        String contents = boardreplyRepository.contents(id);

        return contents;

    }

}
