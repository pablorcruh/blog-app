package ec.com.blog.application.commands.blog;

import ec.com.blog.domain.entities.Blog;
import ec.com.blog.domain.interfaces.repositories.blog.IBlogRepository;
import ec.com.blog.domain.service.validators.ValidateBlogService;
import io.jkratz.mediator.core.CommandHandler;
import io.jkratz.mediator.core.Mediator;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class UpdateBlogCommandHandler implements CommandHandler<UpdateBlogCommand> {

    private final IBlogRepository repository;

    private final Mediator mediator;

    public UpdateBlogCommandHandler(IBlogRepository repository, Mediator mediator) {
        this.repository = repository;
        this.mediator = mediator;
    }

    @Override
    public void handle(@NotNull UpdateBlogCommand updateBlogCommand) {
            if(updateBlogCommand.getBlogId()!= null){
                var blog = this.mediator.dispatch(new ValidateBlogService(updateBlogCommand.getBlogId()));
                if(blog != null){
                    blog.setContent(updateBlogCommand.getContent());
                    blog.setTitle(updateBlogCommand.getTitle());
                    blog.setDescription(updateBlogCommand.getDescription());
                    this.repository.save(blog);
                }
            }
    }
}
