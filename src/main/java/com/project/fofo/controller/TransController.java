package com.project.fofo.controller;

/**
 * 파일명: TransController
 * 작성자: 김현지
 **/

import com.project.fofo.service.trans.AnalyzeTranslatedText;
import com.project.fofo.service.trans.PapagoAPI;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;
@Controller
@Slf4j
public class TransController {

    @GetMapping("/translator")
    public String translator() {
        return "Trans";
    }

    // "/analyze" 엔드포인트에서 POST 요청을 처리하는 컨트롤러 메서드
    @RequestMapping(value = "/analyze", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, String>> analyzeText(@RequestBody String translatedText) {
        AnalyzeTranslatedText analyzer = new AnalyzeTranslatedText();
        String analysisResult = analyzer.analyzeTranslatedText(translatedText);
        System.out.println("analysisResult의 값" + analysisResult);

        String translatedText2 = new PapagoAPI().translator(analysisResult, "ko", "en");
        System.out.println("형태소 분석 시작");
        System.out.println("형태소는\n" + analysisResult + "영어로 번역\n" + translatedText2);

        // JSON 응답 객체 생성
        // analysisResult와 translatedText2 배열을 콤마로 구분된 문자열로 변환
        StringBuilder analysisResultBuilder = new StringBuilder();
        String[] words = analysisResult.split("\\s+"); // 형태소 분석 결과를 공백으로 분리
        for (String word : words) {
            analysisResultBuilder.append(word).append(", ");
        }
        String analysisResultString = analysisResultBuilder.toString();
        // 마지막 콤마와 공백 제거
        analysisResultString = analysisResultString.substring(0, analysisResultString.length() - 2);


        // 콤마로 구분된 항목을 배열에 저장
        StringBuilder translatedText2Builder = new StringBuilder();
        String[] Ewords = translatedText2.split("\\s+"); // translatedText2를 공백으로 분리
        for (String word : Ewords) {
            translatedText2Builder.append(word).append(", ");
        }
        String translatedText2String = translatedText2Builder.toString();
        // 마지막 콤마,공백 제거
        translatedText2String = translatedText2String.substring(0, translatedText2String.length() - 2);


        // Map에 넣기
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("first", analysisResultString);
        responseMap.put("second", translatedText2String);
        out.println("responseMap의 값 "+ responseMap);

        return ResponseEntity.ok(responseMap);

    }
    @RequestMapping(value = "/transword", method = RequestMethod.GET)
    public void sendData(HttpServletResponse res, HttpServletRequest req) throws IOException {
        System.out.println("/transword");
        // ajax 에서 넘어온 값을 확인 후 변수에 저장
        String text = req.getParameter("text");
        String source = req.getParameter("source");
        String target = req.getParameter("target");

        // resp 객체의 문자 타입을 utf-8 로 선언 후 요청 request 가 있었던 web 으로 전달
        res.setContentType("text/plain;charset=utf-8");
        PrintWriter out = res.getWriter();

        // 저장된 변수를 PapagoAPI 의 translator 메소드에 넣기 -> 번역된 내용을 trans 변수 안에 저장
        System.out.println("source 언어는 "+source);
        System.out.println("target 언어는 "+target);
        String trans = new PapagoAPI().translator(text, source, target);

        out.println(trans);
    }
}
