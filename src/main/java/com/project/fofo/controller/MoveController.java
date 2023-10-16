package com.project.fofo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MoveController {


    @GetMapping("camera")
    public String cameraPage() {
        return "Camera";
    }

    @GetMapping("imgtrans")
    public String imgtransPage() {return "Imgtrans";}
}
