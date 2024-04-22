package com.example.club.entity;

import java.util.HashSet;
import java.util.Set;

import com.example.club.constant.ClubMemberRole;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubMember extends BaseEntity {

    @Id
    private String email;

    private String pwd;

    private String name;

    private boolean fromSocial; // 소셜가입

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ClubMemberRole> rolSet = new HashSet<>();

    public void addMemberRole(ClubMemberRole memberRole) {
        rolSet.add(memberRole);
    }
}
