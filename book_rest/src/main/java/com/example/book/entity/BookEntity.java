package com.example.book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@ToString(exclude = { "publisher", "cartegory" })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookEntity extends BaseEntity {

    @SequenceGenerator(name = "Book_seq_gen", sequenceName = "Book_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Book_seq_gen")
    @Column(name = "Book_id")
    @Id
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer salePrice;

    // fetchtype : EAGER(기본)
    @ManyToOne(fetch = FetchType.LAZY)
    private CartegoryEntity cartegory;

    @ManyToOne(fetch = FetchType.LAZY)
    private PublisherEntity publisher;

}
