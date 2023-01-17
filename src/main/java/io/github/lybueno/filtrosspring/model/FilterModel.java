package io.github.lybueno.filtrosspring.model;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.github.lybueno.filtrosspring.constant.ApiConstants.*;

@Data
public class FilterModel {

    private Integer limit;
    private Integer page;
    private String sort;

    public FilterModel(Map<String, String> params){
        this.limit = params.containsKey(LIMIT_KEY) ? Integer.valueOf(params.get(LIMIT_KEY)) : DEFAULT_LIMIT;
        this.page = params.containsKey(PAGE_KEY) ? Integer.valueOf(params.get(PAGE_KEY)) : DEFAULT_PAGE;
        this.sort = params.containsKey(SORT_KEY) ? params.get(SORT_KEY) : DEFAULT_SORT;
    }

    public Pageable toSpringPegeable(){
        List<Sort.Order> orders = getOrders();
        return PageRequest.of(page, limit, Sort.by(orders));
    }

    private List<Sort.Order> getOrders(){

        List<Sort.Order> orders = new ArrayList<>();
        String[] properties = sort.split(",");
        for(String property : properties){
            if(!property.trim().isEmpty()){
                String column = "";
                if(property.startsWith("-")){
                    column = property.replace("-", "").trim();
                    orders.add(Sort.Order.desc(column));
                } else {
                    column = property.trim();
                    orders.add(Sort.Order.asc(column));
                }
            }
        }
        return orders;
    }
}
