package com.project.fofo.DTO;

import com.project.fofo.entity.VocalistEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VocaDTO {
    private Long no; //단어장 번호
    private String bookTitle; //단어장 이름
    private Long userNo; //유저 번호

    public static VocaDTO toVocaDTO(VocalistEntity vocalistEntity){
        VocaDTO vocaDTO = new VocaDTO();

        //영단어, 한국어 단어, 체크 여부를 set
        vocaDTO.setNo(vocalistEntity.getNo());
        vocaDTO.setBookTitle(vocalistEntity.getBookTitle());
        vocaDTO.setUserNo(vocalistEntity.getUserNo());

        return vocaDTO;
    }
}
