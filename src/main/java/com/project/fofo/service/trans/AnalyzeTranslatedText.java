package com.project.fofo.service.trans;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

import java.util.List;

public class AnalyzeTranslatedText {
    public String analyzeTranslatedText(String translatedText) {
        System.out.println("analyzeTranslatedText 시작");
        System.out.println("translatedText"+translatedText);
        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);

        KomoranResult analyzeResultList = komoran.analyze(translatedText);
        StringBuilder analysisResult = new StringBuilder();
        System.out.println("analysisResult 값" + analysisResult);
        List<Token> tokenList = analyzeResultList.getTokenList();

        for (Token token : tokenList) {
            if (token.getPos().startsWith("N")) { // NNP인 경우에만 추가
                analysisResult.append(token.getMorph()).append("\n");
            }
            else analysisResult.append("");
        }
        System.out.println("analyze 빠져나옴");
        return analysisResult.toString();
    }
}
