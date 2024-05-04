package ec.com.blog.application.commands.blog;

import io.jkratz.mediator.core.Command;

import java.util.UUID;

public class UpdateBlogCommand implements Command {

    private UUID blogId;

    private String title;

    private String description;

    private String content;

    public UpdateBlogCommand(UUID blogId, String title, String description, String content) {
        this.blogId = blogId;
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public UUID getBlogId() {
        return blogId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }
}
