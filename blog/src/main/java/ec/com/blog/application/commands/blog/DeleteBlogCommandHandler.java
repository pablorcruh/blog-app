package ec.com.blog.application.commands.blog;

import ec.com.blog.domain.interfaces.repositories.blog.IBlogRepository;
import ec.com.blog.domain.service.validators.ValidateBlogService;
import io.jkratz.mediator.core.CommandHandler;
import io.jkratz.mediator.core.Mediator;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotNull;

@Component
public class DeleteBlogCommandHandler implements CommandHandler<DeleteBlogCommand> {

    private final Mediator mediator;

    private final IBlogRepository repository;

    public DeleteBlogCommandHandler(Mediator mediator, IBlogRepository repository) {
        this.mediator = mediator;
        this.repository = repository;
    }

    @Override
    public void handle(@NotNull DeleteBlogCommand deleteBlogCommand) {
        var blog = this.mediator.dispatch(new ValidateBlogService(deleteBlogCommand.blogId));
        if(blog != null){
            this.repository.delete(blog);
        }
    }
}
