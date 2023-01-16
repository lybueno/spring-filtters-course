package io.github.lybueno.filtrosspring.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "tb_category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = false)
    private String name;
}
