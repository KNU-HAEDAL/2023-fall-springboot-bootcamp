package com.example.assignment_1.dto;

import com.example.assignment_1.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

//3. DTO 생성
@AllArgsConstructor
@ToString

public class MemberForm {

    private String email;
    private String password;

    public Member toEntity() {
        return new Member(null,email,password); //7. toEntity() 작성
    }
}
