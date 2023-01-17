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
    private String equalFilters;

    public FilterModel(Map<String, String> params){
        this.limit = params.containsKey(LIMIT_KEY) ? Integer.valueOf(params.get(LIMIT_KEY)) : DEFAULT_LIMIT;
        this.page = params.containsKey(PAGE_KEY) ? Integer.valueOf(params.get(PAGE_KEY)) : DEFAULT_PAGE;
        this.sort = params.containsKey(SORT_KEY) ? params.get(SORT_KEY) : DEFAULT_SORT;
        this.equalFilters = params.containsKey(EQUAL_FILTERS_KEY) ? params.get(EQUAL_FILTERS_KEY) :
                DEFAULT_EQUAL_FILTERS;
    }

    public Pageable toSpringPegeable(){
        List<Sort.Order> orders = getOrders();
        return PageRequest.of(page, limit, Sort.by(orders));
    }

    public List<EqualFilterModel> equalFilters(){

        List<EqualFilterModel> filters = new ArrayList<EqualFilterModel>();

        if(equalFilters == null || equalFilters.trim().isEmpty()){
            return filters;
        }

        String[] filtersParams = equalFilters.split(";");

        for(String param : filtersParams) {
            if (param.contains(":")) {
                String[] elements = param.split(":");
                if (elements.length == 2) {
                    String column = elements[0];
                    String value = elements[1];
                    filters.add(new EqualFilterModel(column, value, true));
                }
            }
            if (param.contains("~")) {
                String[] elements = param.split("~");
                if (elements.length == 2) {
                    String column = elements[0];
                    String value = elements[1];
                    filters.add(new EqualFilterModel(column, value, false));
                }
            }
        }
        return filters;
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
