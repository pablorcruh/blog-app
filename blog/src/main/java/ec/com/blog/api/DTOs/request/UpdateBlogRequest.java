package ec.com.blog.api.DTOs.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class UpdateBlogRequest {
    @NotNull
    @NotBlank
    private String content;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String title;

    public UpdateBlogRequest(String content, String description, String title) {
        this.content = content;
        this.description = description;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
