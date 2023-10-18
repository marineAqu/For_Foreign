package com.project.fofo.controller;



import com.project.fofo.entity.WordsEntity;
import com.project.fofo.repository.BoardRepository;
import com.project.fofo.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class BoardController {


    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    @GetMapping("/post")
    public String post() {
        return "Post.html";
    }

    @Autowired
    private BoardService boardService;

    @RequestMapping(value="/board", method = RequestMethod.POST)
    public String handlePostRequest(@ModelAttribute WordsEntity boardDto) {
        // 1. Dto를 Entity로 변환
        WordsEntity board = boardDto.toEntity();

        // 2. Repository에게 Entity를 DB 안에 저장
        WordsEntity saved = boardRepository.save(board);

        // 리다이렉트를 사용하여 GET 요청으로 이동
        return "redirect:/board";
    }

    @RequestMapping(value="/board", method = RequestMethod.GET)
    public String showBoard(Model model) {
        // 1. 모든 단어를 가져온다
        List<WordsEntity> boardEntityList = boardRepository.findAll();

        // 2. 가져온 단어 묶음을 뷰로 전달
        model.addAttribute("boardList", boardEntityList);

        // 3. 뷰 페이지를 설정
        return "Index";
    }


    @GetMapping("/Detail")      //상세보기 화면 localhost:8080/board/detail?id=1
    public String show2(Model model, Long num){

        model.addAttribute("board",boardService.getPost(num));

        //3.보여줄 페이지 설정
        return "Detail";
    }
    @PostMapping("/updateBookmark")
    public void updateBookmark(@RequestParam("bookmarked") String bookmarked, @RequestParam("postId") Long postId) {
        // 문자열을 char로 변환하여 BoardService로 전달
        char status = bookmarked.charAt(0);
        boardService.updateBookmarkStatus(postId, status);
    }

}
