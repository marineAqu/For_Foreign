package com.project.fofo.controller;

/**
 * 파일명: AboutRankController
 * 작성자: 김도연
 **/

import com.project.fofo.entity.MemlistEntity;
import com.project.fofo.service.AboutRankService;
import com.project.fofo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AboutRankController {
    private final MemberService memberService;
    private final AboutRankService aboutRankService;

    @GetMapping("/overallRank")
    public String overallRank(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uid = ((UserDetails) principal).getUsername();
        MemlistEntity user = memberService.findByMember(uid);

        //랭킹
        model.addAttribute("rankList", aboutRankService.getRankingList());

        return "OverallRank";
    }
}
