package com.project.fofo.controller;

import com.mysql.cj.Session;
import com.project.fofo.DTO.WritingDTO;
import com.project.fofo.entity.MemlistEntity;
import com.project.fofo.service.MemberService;
import com.project.fofo.service.WritingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * 파일명: WritingController
 * 작성자: 김도연
 **/

@Controller
@RequiredArgsConstructor
public class WritingController {
    private final WritingService writingService;
    private final MemberService memberService;
    long miliseconds = System.currentTimeMillis();
    Date date = new Date(miliseconds);

    @GetMapping("/WritingBoard")
    public String WritingBoard(Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uid = ((UserDetails) principal).getUsername();
        MemlistEntity user = memberService.findByMember(uid);

        List<WritingDTO> writingDTOList = writingService.SearchWritingPosts(date, user.getNo());

        model.addAttribute("Writingboard", writingDTOList);

        model.addAttribute("userName", user.getUserName());

        System.out.println(writingDTOList);

        //한 사용자가 같은 날 중복 작성할 수 없도록 이미 작성한 유저는 isDouble을 1로 설정
        for (WritingDTO writingDTO : writingDTOList) {
            if (user.getUserName().equals(writingDTO.getUserName())) {
                model.addAttribute("isDouble", 1);
                System.out.println("isDouble을 1로 설정");
                break;
            }
        }

        return "WritingBoard";
    }

    @PostMapping("saveWritingTop")
    public @ResponseBody void saveWritingTop(@RequestParam("inputValue") String inputValue) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uid = ((UserDetails) principal).getUsername();
        MemlistEntity user = memberService.findByMember(uid);

        System.out.println("saveVocaTit 함수 들어옴 (컨트롤러)");

        writingService.saveWritingTop(user.getNo().longValue(), inputValue, date);
    }

    @PostMapping("saveHeart")
    public @ResponseBody void saveHeart(@RequestParam("noIndex") Long noIndex, @RequestParam("heartChk") int heartChk) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uid = ((UserDetails) principal).getUsername();
        MemlistEntity user = memberService.findByMember(uid);

        writingService.saveIlikeIt(user.getNo(), noIndex, heartChk);

    }
}
