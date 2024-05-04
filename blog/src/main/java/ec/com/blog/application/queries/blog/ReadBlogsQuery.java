package ec.com.blog.application.queries.blog;

import ec.com.shared.application.dto.response.GetEntitiesResponse;
import ec.com.shared.domain.models.FilterPaginationQueryModel;
import ec.com.shared.domain.specification.Filter;
import ec.com.shared.domain.specification.OrderBy;
import ec.com.shared.domain.specification.OrderType;
import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Getter
@Setter
public class ReadBlogsQuery implements Request<GetEntitiesResponse> {
    private List<OrderBy> orders;
    private List<Filter> filters;
    private Integer page;
    private Integer pageSize;
    private String name;
    private String sortBy;

    public ReadBlogsQuery(MultiValueMap<String, String> requestParams) {

        var queryModel = new FilterPaginationQueryModel(
                requestParams.get("filters"),
                requestParams.get("ordersBy"),
                requestParams.get("page"),
                requestParams.get("pageSize")
        );

        this.orders = queryModel.getOrders();
        this.filters = queryModel.getFilters();
        this.page = queryModel.getPage();
        this.pageSize = queryModel.getPageSize();

        if(requestParams.get("sortBy")!=null){
            this.sortBy = requestParams.get("sortBy").get(0);
            if(sortBy.charAt(0)=='-'){
                this.orders.add(new OrderBy(sortBy.substring(1), OrderType.fromName("desc")));
            }else {
                this.orders.add(new OrderBy(sortBy, OrderType.fromName("asc")));
            }
        }
        else
        {
            this.orders.add(new OrderBy("id", OrderType.fromName("asc")));
        }
    }

}
