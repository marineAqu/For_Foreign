package com.project.fofo.DTO;

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
    @Length(min=5, message = "비밀번호는 5자 이상입니다.")
    private String password;

    @Builder
    public MemberDTO(String userName, String uid, String password) {
        this.userName = userName;
        this.uid = uid;
        this.password = password;
    }
}
