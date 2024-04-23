package com.example.club.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClubAuthMemberDto extends User implements OAuth2User {

    // db에서 이증된 정보를 담을 객체

    private String email;
    private String name;
    private String password;
    private boolean fromSocial;

    // 소셜 로그인에서 넘어오는 값을 담는 겍체
    private Map<String, Object> attr;

    public ClubAuthMemberDto(String username, String password, boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.fromSocial = fromSocial;
        this.password = password;

    }

    public ClubAuthMemberDto(String username, String password, boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
        this(username, password, fromSocial, authorities);
        this.attr = attr;

    }

    @Override
    public Map<String, Object> getAttributes() {

        return this.attr;
    }

}
