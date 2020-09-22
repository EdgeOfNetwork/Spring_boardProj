package com.example.Board.service;


import com.example.Board.dto.BoardDto;
import com.example.Board.model.Board;
import com.example.Board.repository.BoardRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Builder
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<BoardDto> findBoardService(){
        List<Board> boardList = boardRepository.findAll();
        System.out.println(boardList);
        List<BoardDto> testList = new ArrayList<>();
        for(int i = 0 ; i < boardList.size() ; i++){ testList.add(convertToDto(boardList.get(i))); }
        return testList;
    }

    //@Transactional
    public BoardDto findBoardDetailService(long id){
        System.out.println("path id :" + id);
        Optional<Board> boardEntityWrapper = boardRepository.findById(id);
        Board boardEntity = boardEntityWrapper.get();

        BoardDto boardDto = convertToDto(boardEntity);

//        BoardDto boardDto = BoardDto.builder()
//                .id(boardEntity.getId())
//                .title(boardEntity.getTitle())
//                .content(boardEntity.getContent())
//                .build();

        return boardDto;
        //return board.map(this::convertToDto);
    }

    public Long saveBoard(BoardDto boardDto){
        return boardRepository.save(convertToBoard(boardDto)).getId();
    }

    public void deleteBoard(Long id){
        boardRepository.deleteById(id);
    }


    public Board convertToBoard(BoardDto boardDto){
        Board board = new Board();
        board.setId(boardDto.getId());
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        return board;
    }

    public BoardDto convertToDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        return boardDto;
    }
}

