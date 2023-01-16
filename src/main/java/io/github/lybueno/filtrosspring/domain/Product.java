package io.github.lybueno.filtrosspring.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity(name = "tb_product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(name = "qty_stk", nullable = false)
    private Integer qtdStk;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;
}
