package ec.com.blog.application.queries.blog;

import ec.com.blog.api.DTOs.responses.ReadBlogResponse;
import ec.com.blog.domain.entities.Blog;
import ec.com.blog.domain.interfaces.repositories.blog.IBlogRepository;
import ec.com.shared.domain.specification.Criteria;
import ec.com.shared.domain.specification.Filter;
import ec.com.shared.domain.specification.FilterComparer;
import ec.com.shared.domain.specification.FilterOperator;
import io.jkratz.mediator.core.RequestHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Component
public class ReadBlogQueryHandler implements RequestHandler<ReadBlogQuery, ReadBlogResponse> {

    private final IBlogRepository blogRepository;

    public ReadBlogQueryHandler(IBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }


    @Override
    public ReadBlogResponse handle(@NotNull ReadBlogQuery query) {
        var filters = new ArrayList<Filter>();
        filters.add(new Filter("id", FilterOperator.fromName("="), FilterComparer.fromName("and"), query.getId()));
        var listAttribute = blogRepository.find(new Criteria(filters, new ArrayList<>(), 0, 1));
        if (listAttribute.size() > 0) {
            Blog blog = listAttribute.get(0);
            ReadBlogResponse blogResponse = new ReadBlogResponse(
                    blog.getId(),
                    blog.getContent(),
                    blog.getDescription(),
                    blog.getTitle()
            );
            return blogResponse;
        }
        return null;
    }
}
