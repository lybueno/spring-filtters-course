package io.github.lybueno.filtrosspring.model;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;

import static io.github.lybueno.filtrosspring.constant.ApiConstants.*;

@Data
public class FilterModel {

    private Integer limit;
    private Integer page;

    public FilterModel(Map<String, String> params){
        this.limit = params.containsKey(LIMIT_KEY) ? Integer.valueOf(params.get("limit")) : DEFAULT_LIMIT;
        this.page = params.containsKey(PAGE_KEY) ? Integer.valueOf(params.get("page")) : DEFAULT_PAGE;
    }

    public Pageable toSpringPegeable(){
        return PageRequest.of(page, limit);
    }
}
