package com.example.Board.controller;

import com.example.Board.dto.BoardDto;
import com.example.Board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    /*
     * showAllBoards : 전체 건수 조회
     * showOneDetail : 개별 건수 조회
     * insertForm : 생성
     * update : 수정
     * delete : 삭제
     * */

    @GetMapping("/list") //naming 명사형으로 변경
    public String showAllBoards(Model model) {
        List<BoardDto> boardList = boardService.findBoardService();
        //System.out.println(boardList);
        model.addAttribute("boardList",boardList);
        return "/board/list"; //리턴값 제대로
    }

    @GetMapping("/list/detail/{id}")
    public String showOneDetail(@PathVariable("id") long id,Model model){
        model.addAttribute("detailList",boardService.findBoardDetailService(id));
        return "/board/list";
    }




//    @PostMapping("/insert")
//    public String insertForm(@RequestParam BoardDto boardDto){
//        return "/board/insert";
//    }



    //    @GetMapping("/list") //naming 명사형으로 변경
//    public String boardList(Model model) {
//        List<BoardDto> boardList = boardService.findBoardService();
//        model.addAttribute("boardList",boardList);
//        return "/list"; //리턴값 제대로
//    }

    //    @GetMapping("hello")
//    public String hello(Model model){
//        model.addAttribute("data", "hello");
//        return "hello";
//    }

//    @GetMapping("/list")
//    public String list(@PageableDefault Pageable pageable, Model model) {
//        model.addAttribute("boardList", boardService.findBoardList(pageable));
//        return "/board/list";
//
//        //페이징 기능
//    }

}
