package com.example.book.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@ToString(exclude = "books")
@AllArgsConstructor
@Setter
@Getter
public class CartegoryEntity extends BaseEntity {

    @SequenceGenerator(name = "book_category_seq_gen", sequenceName = "category_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_category_seq_gen")
    @Column(name = "category_Id")
    @Id
    private Long id;

    @Column(nullable = false, name = "category_Name")
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "cartegory")
    private List<BookEntity> books = new ArrayList<>();
}
