package com.project.fofo.entity;

import com.project.fofo.DTO.MemberDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="Memlist")
@Getter
@Setter
@NoArgsConstructor
public class MemlistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer no;
    @Column(length = 15)
    private String userName;
    @Column(length = 40)
    private String uid;
    @Column(length = 100)
    private String password;
    @Column(length = 20)
    private String role;
    private Integer userPoint;
    private Integer ranking;

    @Builder
    public MemlistEntity(String userName, String uid, String password, String role, Integer userPoint, Integer ranking) {
        this.userName = userName;
        this.uid = uid;
        this.password = password;
        this.role = role;
        this.userPoint = userPoint;
        this.ranking = ranking;
    }

    public static MemlistEntity createMember(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        MemlistEntity memlistEntity = MemlistEntity.builder()
                .userName(memberDTO.getUserName())
                .uid(memberDTO.getUid())
                .password(passwordEncoder.encode(memberDTO.getPassword())) // 암호화
                .role("ROLE_USER")
                .userPoint(0)
                .ranking(0)
                .build();

        return memlistEntity;
    }
<<<<<<< HEAD

=======
>>>>>>> e3765f37bea5b8b0f2881f46c8a2339086f83f14
}
