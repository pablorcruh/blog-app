package ec.com.blog.application.queries.blog;

import ec.com.blog.domain.exceptions.BlogDomainException;
import ec.com.blog.domain.interfaces.repositories.blog.IBlogRepository;
import ec.com.shared.application.dto.PaginationResponse;
import ec.com.shared.application.dto.response.GetEntitiesResponse;
import ec.com.shared.domain.specification.Criteria;
import io.jkratz.mediator.core.RequestHandler;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Component
public class ReadBlogsQueryHandler implements RequestHandler<ReadBlogsQuery, GetEntitiesResponse> {

    private final IBlogRepository repository;

    public ReadBlogsQueryHandler(IBlogRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetEntitiesResponse handle(@NotNull ReadBlogsQuery query) {
        if (query.getPage() == null) {
            query.setPage(0);
        }
        if (query.getPageSize() == null) {
            query.setPageSize(Integer.MAX_VALUE);
        }
        var criteria = new Criteria(query.getFilters(),
                query.getOrders(),
                Integer.valueOf(query.getPage().toString()),
                Integer.valueOf(query.getPageSize().toString()));

        Integer totalAmount = Math.toIntExact(repository.count(criteria));
        if (totalAmount == 0) {
            return new GetEntitiesResponse(new ArrayList(), new PaginationResponse(0, 0, 0));
        }
        return getPaginatedEntities(query, totalAmount, criteria);
    }

    @SneakyThrows
    private GetEntitiesResponse getPaginatedEntities(ReadBlogsQuery query,
                                                     Integer totalAmount,
                                                     Criteria criteria
    ) {

        if (query.getPageSize() == null || query.getPage() == null || totalAmount <= query.getPageSize()) {
            criteria.setPage(0);
            criteria.setPageSize(totalAmount);
        } else if (query.getPage() > totalAmount / query.getPageSize()) {
            throw new BlogDomainException("The page number is incorrect");
        }

        var entitiesList = repository.find(criteria);

        System.out.println("entitiesList..   "+entitiesList);
        var paginationResponse = new PaginationResponse(criteria.getPage(),
                criteria.getPageSize(), totalAmount);

        return new GetEntitiesResponse(entitiesList, paginationResponse);
    }
}
