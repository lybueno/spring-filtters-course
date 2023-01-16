package io.github.lybueno.filtrosspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
public class PageModel<T> {

    private Long totalElements;
    private Integer currentPage;
    private Integer totalPages;
    private Integer pageSize;
    private List<T> elements;

    public PageModel(Page<T> page){
        this.elements = page.getContent();
        this.totalElements = page.getTotalElements();
        this.currentPage = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.pageSize = page.getSize();
    }

}
