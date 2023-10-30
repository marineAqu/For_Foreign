package com.project.fofo.service.trans;

/**
 * 파일명: AnalyzeTranslatedText
 * 작성자: 김현지
 * 설명: 형태소 분석 함수
 **/


import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

import java.util.List;


public class AnalyzeTranslatedText {

    public String analyzeTranslatedText(String translatedText) {


        System.out.println("translatedText"+translatedText);
        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);

        KomoranResult analyzeResultList = komoran.analyze(translatedText);
        StringBuilder analysisResult = new StringBuilder();
        System.out.println("analysisResult 값" + analysisResult);
        List<Token> tokenList = analyzeResultList.getTokenList();

        for (Token token : tokenList) {
            if (token.getPos().startsWith("N")) { // NNP인 경우에만 추가.
                analysisResult.append(token.getMorph()).append("\n");
            }
            else analysisResult.append("");
        }

        return analysisResult.toString();

    }


}
