package ec.com.blog.application.commands.blog;

import ec.com.blog.api.DTOs.responses.CreateBlogResponse;
import ec.com.blog.domain.entities.Blog;
import ec.com.blog.domain.interfaces.repositories.blog.IBlogRepository;
import io.jkratz.mediator.core.RequestHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Component
public class CreateBlogCommandHandler implements RequestHandler<CreateBlogCommand, CreateBlogResponse> {

    private final IBlogRepository repository;

    public CreateBlogCommandHandler(IBlogRepository repository) {
        this.repository = repository;
    }


    @Override
    public CreateBlogResponse handle(@NotNull CreateBlogCommand request) {
        Blog newBlog = new Blog();
        newBlog.setContent(request.getContent());
        newBlog.setDescription(request.getDescription());
        newBlog.setTitle(request.getTitle());
        repository.save(newBlog);
        return null;
    }
}
