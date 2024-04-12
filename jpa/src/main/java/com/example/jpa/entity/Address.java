package com.example.jpa.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Embeddable // 다른 엔티티에서 포함 된다 정의
public class Address {
    private String city;
    private String street;
    private String zipcode;

}
