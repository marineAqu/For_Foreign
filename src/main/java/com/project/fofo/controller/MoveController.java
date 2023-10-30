package com.project.fofo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * 파일명: MoveController
 * 작성자: 정채빈
 **/
@Controller
public class MoveController {
    ///페이지 이동
    @GetMapping("nextcamera")
    public String nextcamera(){
        return "NextCamera";
    }

    @GetMapping("camera")
    public String cameraPage() {
        return "Camera";
    }

    @GetMapping("imgtrans")
    public String imgtransPage() {return "Imgtrans";}
}
