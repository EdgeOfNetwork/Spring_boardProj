package com.example.Board.service;

import com.example.Board.dto.BoardDto;
import com.example.Board.model.Board;
import com.example.Board.repository.BoardRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service

//TODO : get rid of the entities from Service layer
public class BoardService {

    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4;       // 한 페이지에 존재하는 게시글 수


    @Autowired
    private BoardRepository boardRepository;



    @Transactional
    public List<BoardDto> findBoardService(Integer pageNum){
        Page<Board> page = boardRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC,"createdDate")));

        //엔티티를 리스트로 꺼낸다.
        List<Board> boardLists = page.getContent();
        List<BoardDto> boardDtoList = new ArrayList<>();

        //변환 후 개별 board, boardDtoList에 저장
        for(Board boardList : boardLists){
            boardDtoList.add(this.convertToDto(boardList));
        }

        return boardDtoList;
    }

    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum) {

        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        //총 게시글 갯수,
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        //총 게시글 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        //현재 페이지를 기준으로 블럭의 마지막 페이지 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT : totalLastPageNum;

        //페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 :curPageNum - 2;

        //페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++){
            pageList[idx] = val;
        }
        return pageList;
    }


    @Transactional
    public BoardDto findBoardDetailService(long id){
        Optional<Board> boardEntityWrapper = boardRepository.findById(id);
        Board boardEntity = boardEntityWrapper.get();
        BoardDto boardDto = convertToDto(boardEntity);
        return boardDto;
    }



    @Transactional
    public Long saveBoard(BoardDto boardDto){
        return boardRepository.save(boardDto.convertToEntity()).getId();
    }



    @Transactional
    public void deleteBoard(Long id){
        boardRepository.deleteById(id);
    }





    @Builder
    public BoardDto convertToDto(Board board) {

        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .build();

    }

}

