package ec.com.shared.domain.models;


import ec.com.shared.domain.specification.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
public class FilterPaginationQueryModel {
    @Valid
    private List<String> filters;
    @Valid
    private List<String> ordersBy;
    private Integer page;
    private Integer pageSize;
    private Boolean group;

    public FilterPaginationQueryModel(List<String> filters, List<String> ordersBy, List<String> page, List<String> pageSize) {
        this.filters = filters;
        this.ordersBy = ordersBy;
        if (page != null && page.stream().count() > 0) {
            this.page = Integer.valueOf(page.get(0));
        } else {
            this.page = null;
        }

        if (pageSize != null && pageSize.stream().count() > 0) {
            this.pageSize = Integer.valueOf(pageSize.get(0));
        } else {
            this.pageSize = null;
        }
    }

    public FilterPaginationQueryModel(List<String> filters, List<String> ordersBy, List<String> page, List<String> pageSize,Boolean group) {
        this.filters = filters;
        this.ordersBy = ordersBy;
        this.group=group;
        if (page != null && page.stream().count() > 0) {
            this.page = Integer.valueOf(page.get(0));
        } else {
            this.page = null;
        }

        if (pageSize != null && pageSize.stream().count() > 0) {
            this.pageSize = Integer.valueOf(pageSize.get(0));
        } else {
            this.pageSize = null;
        }
    }

    public List<Filter> getFilters() {

        if (filters != null && filters.stream().count() > 0) {
            return constructFilters(filters);
        }
        return new ArrayList<>();
    }

    public List<OrderBy> getOrders() {
        if (ordersBy != null && ordersBy.stream().count() > 0) {
            return constructOrders(ordersBy);
        }
        return new ArrayList<>();
    }

    public List<OrderBy> constructOrders(List<String> ordersIn) {
        List<OrderBy> result = new ArrayList<>();

        if (ordersIn == null) return result;

        for (String orderString : ordersIn) {
            List<String> parameters = Arrays.asList(orderString.split(","));
            if (parameters.stream().count() > 2) {
                //TODO throw exception
            }

            String orderName = parameters.stream().count()==1 ? "asc" : parameters.get(1);
            OrderBy orderBy = new OrderBy(parameters.get(0), OrderType.fromName(orderName));
            result.add(orderBy);
        }
        return result;
    }

    public List<Filter> constructFilters(List<String> filtersIn) {
        List<Filter> result = new ArrayList<>();

        if (filtersIn == null) return result;

        for (String filterString : filtersIn) {
            List<String> parameters = Arrays.asList(filterString.split(","));

            if (parameters.stream().count() != 4) {
                //TODO throw exception
            }

            Filter filter = new Filter(parameters.get(0), FilterOperator.fromName(parameters.get(2)),
                    FilterComparer.fromName(parameters.get(3)), parameters.get(1));
            result.add(filter);
        }

        return result;
    }
}
