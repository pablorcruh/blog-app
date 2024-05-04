package ec.com.blog.application.commands.blog;

import io.jkratz.mediator.core.Command;

import java.util.UUID;

public class DeleteBlogCommand implements Command {

    public UUID blogId;

    public DeleteBlogCommand(UUID blogId) {
        this.blogId = blogId;
    }

    public UUID getBlogId() {
        return blogId;
    }
}
