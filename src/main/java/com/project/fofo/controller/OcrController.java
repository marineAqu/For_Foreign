package com.project.fofo.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.lang.*;
@RestController
@RequestMapping("/api")
public class OcrController {
    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        System.out.println("컨트롤러 들어옴");
        // 저장할 경로 설정
        Path uploadDir = Path.of("\\uploads");
        Path filePath = uploadDir.resolve(image.getOriginalFilename());

        // 이미지 저장
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        String result = "오류 발생";
        // EasyOCR  호출
        try{
            System.out.println("트라이");
            result = runEasyOCR(filePath.toString());
        } catch (InterruptedException ex){
            System.out.println("캐치");
            result = "catch문";
        }

        System.out.println("결과: "+result);
        return result;
    }

    private String runEasyOCR(String imagePath) throws IOException, InterruptedException {
        // Python 스크립트 실행
        System.out.println("런이지오씨알 함수 실행");
        //ProcessBuilder processBuilder = new ProcessBuilder("python3", "easyocr_script.py", imagePath);
        /*ProcessBuilder processBuilder = new ProcessBuilder("python3", "easyocr_script.py");
        Process process = processBuilder.start();
        process.waitFor();
        */
        Resource pythonScriptResource = new ClassPathResource("OCR/easyocr_script.py");
        Path scriptFilePath = Files.createTempFile("easyocr_script", ".py");
        Files.copy(pythonScriptResource.getInputStream(), scriptFilePath, StandardCopyOption.REPLACE_EXISTING);

        // Python 스크립트 실행
        ProcessBuilder processBuilder = new ProcessBuilder("\\Python39\\python", scriptFilePath.toString(), imagePath);
        // 입력 스트림 처리
        //processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);



        // 출력 및 오류 스트림 처리
        /*
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        System.out.println("69번째 줄");
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT).toString();
        */
        Process process = processBuilder.start();
        process.waitFor();


        System.out.println("70줄");

        StringBuilder output = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line =reader.readLine()) != null) {
                output.append(line);
                output.append("\n");
            }
        }

        System.out.println("83번째 줄");

        // 임시 파일 삭제
        Files.deleteIfExists(scriptFilePath);

        return output.toString();
    }
}
