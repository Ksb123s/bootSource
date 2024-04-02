package com.example.web1.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDto {

    // @NotEmpty
    @Pattern(regexp = "(?=^[A-z])(?=.+\\d)[A-z\\d]{6,12}$", message = "아이디는 영대소문자,숫자를 사용해서 6~12자리입니다.")
    private String userid;

    // @NotEmpty
    @Pattern(regexp = "(?=^[A-z])(?=.+\\d)(?=.+[!@$%])[A-z\\d!@$%]{8,15}$", message = "비밀번호는 영대소문자,숫자,특수문자(!@$%)를 사용해서 8~15자리입니다.")
    private String password;

    @NotNull(message = "나이는 필수 요소 입니다.")
    @Min(value = 0)
    @Max(value = 120)
    private Integer age;

    @Email(message = "이메일은 필수 요소입니다.") // Email 검증
    @NotEmpty // @NotNull + " "값 불가
    private String email;
    @Length(min = 2, max = 5) // 최소/최대 글자수 설정
    private String name;
}
