package ec.com.blog.domain.service.validators;

import ec.com.blog.domain.entities.Blog;
import ec.com.blog.domain.interfaces.repositories.blog.IBlogRepository;
import ec.com.shared.domain.specification.Criteria;
import ec.com.shared.domain.specification.Filter;
import ec.com.shared.domain.specification.FilterComparer;
import ec.com.shared.domain.specification.FilterOperator;
import io.jkratz.mediator.core.Request;
import io.jkratz.mediator.core.RequestHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ValidateBlogServiceHandler implements RequestHandler<ValidateBlogService,Blog> {

    private final IBlogRepository blogRepository;

    public ValidateBlogServiceHandler(IBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog handle(@NotNull ValidateBlogService query) {
        var filters = new ArrayList<Filter>();
        filters.add(new Filter("id", FilterOperator.fromName("="), FilterComparer.fromName("and"), query.getId()));
        var listAttribute = blogRepository.find(new Criteria(filters, new ArrayList<>(), 0, 1));
        if (listAttribute.size() > 0) {
            return listAttribute.get(0);
        }
        return null;
    }
}
