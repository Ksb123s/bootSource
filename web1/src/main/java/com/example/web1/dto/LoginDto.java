package com.example.web1.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {

    @Email(message = "이메일을 확인해 주세요") // Email 검증
    @NotEmpty // @NotNull + " "값 불가
    private String email;

    @Length(min = 2, max = 6) // 최소/최대 글자수 설정
    // @NotBlank // @NotEmpty + ""값 불가
    private String name;

}
