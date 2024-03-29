package com.project.fofo.controller;

/**
 * 파일명: BoardController
 * 작성자: 김현지
 **/

import com.project.fofo.entity.VocalistEntity;
import com.project.fofo.entity.WordsEntity;
import com.project.fofo.repository.BoardRepository;
import com.project.fofo.repository.VocaRepository;
import com.project.fofo.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@Slf4j
public class BoardController {


    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private VocaRepository vocaRepository;

    @Autowired
    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping("/post")
    public String post() {
        return "Post";
    }

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public String handlePostRequest(@ModelAttribute WordsEntity boardDto, @RequestParam("vocaNo") Long vocaNo) {
        // 1. Dto를 Entity로 변환
        WordsEntity board = boardDto.toEntity();

        // 2. vocaNo 값 설정
        board.setVocaNo(vocaNo);

        // 3. Repository에게 Entity를 DB 안에 저장
        WordsEntity saved = boardRepository.save(board);

        System.out.println("vocaNo: " + vocaNo);

        // 리다이렉트를 사용하여 GET 요청으로 이동
        return "redirect:/board?vocaNo=" + vocaNo;
    }


    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public String showBoard(@RequestParam("vocaNo") Long vocaNo, Model model) {
        // 1. vocaNo에 해당하는 단어들만 가져온다
        List<WordsEntity> boardEntityList = boardRepository.findByVocaNo(vocaNo);

        // 2. 가져온 단어 묶음을 뷰로 전달
        model.addAttribute("boardList", boardEntityList);

        // 3. 뷰 페이지를 설정
        return "BoardList";
    }

    @GetMapping("/getBookTitle")
    public ResponseEntity<Map<String, String>> getBookTitle(@RequestParam Long vocaNo) {
        Optional<VocalistEntity> vocalistEntityOptional = vocaRepository.findByNo(vocaNo);

        if (vocalistEntityOptional.isPresent()) {
            VocalistEntity vocalistEntity = vocalistEntityOptional.get();
            String bookTitle = vocalistEntity.getBookTitle();

            Map<String, String> response = new HashMap<>();
            response.put("bookTitle", bookTitle);

            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/Detail")      //상세보기 화면 localhost:8080/board/detail?id=1
    public String show2(Model model, Long no){

        model.addAttribute("board",boardService.getPost(no));

        //3.보여줄 페이지 설정
        return "Detail";
    }

    @GetMapping("/DetailModi")
    public String detailModi(Model model, Long no){

       model.addAttribute("board",boardService.getPost(no));

       return "Detail_modi";
    }

    @RequestMapping(value = "/saveWord", method = RequestMethod.POST)
    public String saveWord(@RequestParam("no") Long no,
                           @RequestParam("korWord") String korWord,
                           @RequestParam("pronSymbol") String pronSymbol,
                           @RequestParam("engWord") String engWord,
                           @RequestParam("koSentence") String koSentence,
                           @RequestParam("enSentence") String enSentence,
                           Model model) {
        boardService.updateWord(no, korWord, pronSymbol, engWord, koSentence, enSentence);

        return "redirect:/Detail?no=" + no;
    }


    @PostMapping(value = "/updateBookmark", consumes = "application/json")
    public ResponseEntity<String> updateBookmark(@RequestBody Map<String, String> payload) {
        String bookmarked = payload.get("bookmarked");
        String noString = payload.get("no");

        Long no = Long.parseLong(noString);
        char status = bookmarked.charAt(0);

        boardService.updateBookmarkStatus(no, status);

        return ResponseEntity.ok("업데이트 성공");
    }

}
