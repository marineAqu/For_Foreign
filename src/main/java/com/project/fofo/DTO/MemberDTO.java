package com.project.fofo.DTO;

/**
 * 파일명: MemberDTO
 * 작성자: 김민정
 **/

import com.project.fofo.entity.MemlistEntity;
import com.project.fofo.entity.VocalistEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String userName;

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String uid;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=5, message = "비밀번호는 8자 이상입니다.")
    private String password;


    //userPoint 변수 추가 작성자: 김도연 (랭킹때문에 추가)
    private Integer userPoint;

    @Builder
    public MemberDTO(String userName, String uid, String password) {
        this.userName = userName;
        this.uid = uid;
        this.password = password;
    }

    /**
     * 함수: toMemberDTO
     * 작성자: 김도연
     * 설명: 랭킹 페이지에서 사용
     **/
    public static MemberDTO toMemberDTO(MemlistEntity memlistEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserName(memlistEntity.getUserName());
        memberDTO.setUserPoint(memlistEntity.getUserPoint());
        return memberDTO;
    }

}
