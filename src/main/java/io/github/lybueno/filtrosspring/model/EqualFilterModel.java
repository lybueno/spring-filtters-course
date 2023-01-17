package io.github.lybueno.filtrosspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EqualFilterModel {

    private String column;
    private String value;
    private Boolean isEqual;
}
