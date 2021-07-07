package Spring.service;

import Spring.domain.board.BoardEntity;
import Spring.domain.board.BoardRepository;
import Spring.domain.boardreply.BoardreplyEntity;
import Spring.domain.boardreply.BoardreplyRepository;
import Spring.web.dto.BoardDto;
import Spring.web.dto.BoardupdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final BoardreplyRepository boardreplyRepository;

    // 게시물 등록 저장
    public void boardsave(BoardDto boardDto) {

        // 인수로 들어온 Dto --> Entity --> save 메소드에 넣어주기
        boardRepository.save(boardDto.toEntity());

    }

    // 모든 게시물 출력(페이징)
    public Map<String, Object> boardlist(int currentPage) {

        // 페이지에 보여줄 행의 개수 ROW_PER_PAGE = 5로 고정
        final int ROW_PER_PAGE = 5;

        // 페이지에 보여줄 첫번째 페이지 번호는 1로 초기화
        int startPageNum = 1;

        // 처음 보여줄 마지막 페이지 번호는 5
        int lastPageNum = ROW_PER_PAGE;

        // 현재 페이지가 ROW_PER_PAGE / 2 보다 클 경우
        if(currentPage > (ROW_PER_PAGE / 2)) {

            // 보여지는 페이지 첫번째 페이지 번호는 현재페이지 - ((마지막 페이지 번호 / 2) - 1)
            // 만약 현재 페이지가 6이라면 첫번째 페이지번호는 2
            startPageNum = currentPage - ((lastPageNum / 2) - 1);

            // 보여지는 마지막 페이지 번호는 현재 페이지 번호 + 현재 페이지 번호 - 1
            lastPageNum += (startPageNum - 1);

        }

        // Map Data Type 객체 참조 변수 map 선언
        // HashMap() 생성자 메서드로 새로운 객체를 생성, 생성된 객체의 주소값을 객체 참조 변수에 할당
        Map<String, Integer> map = new HashMap<String, Integer>();

        // 한 페이지에 보여지는 첫번째 행은 (현재페이지 - 1) * 10
        int startRow = (currentPage - 1) * ROW_PER_PAGE;

        // 값을 map에 던져줌
        map.put("startRow", startRow);
        map.put("rowPerPage", ROW_PER_PAGE);

        // DB 행의 총 개수를 구하는 getBoardAllCount() 메서드를 호출하여 double Date Type의 boardCount 변수에 대입
        Long boardCount = boardRepository.count();

        // 마지막 페이지번호를 구하기 위해 총 개수 / 페이지당 보여지는 행의 개수 -> 올림처리 -> lastPage 변수에 대입
        int lastPage = (int) (Math.ceil(boardCount / ROW_PER_PAGE));

        // 현재 페이지가(마지막 페이지 - 4) 보다 같거나 클 경우
        if(currentPage >= (lastPage - 4)) {

            // 마지막 페이지 번호는 lastPage
            lastPageNum = lastPage;

        }

        // 구성한 값들을 Map Data Type 의 resultMap 객체 참조 변수에 던져주고 return
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 엔티티값 --> Dto
        List<BoardEntity> boardEntityList = boardRepository.findBylist(startRow + 1, startRow + ROW_PER_PAGE);

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
        ////////////////
        List<BoardreplyEntity> boardreplyEntities = boardreplyRepository.findAll();

        for(BoardDto temp : boardDtos) {
            for(BoardreplyEntity temp2 : boardreplyEntities) {

                if(temp.getId() == temp2.getBoardid()) {

                    temp.rcountup();

                }

            }
        }

        resultMap.put("list", boardDtos);
        resultMap.put("currentPage", currentPage);
        resultMap.put("lastPage", lastPage);
        resultMap.put("startPageNum", startPageNum);
        resultMap.put("lastPageNum", lastPageNum);

        return resultMap;

    }

    /*// 모든 게시물 출력(페이징처리o)
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

    }*/

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
