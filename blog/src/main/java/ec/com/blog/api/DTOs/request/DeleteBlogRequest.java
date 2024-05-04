package ec.com.blog.api.DTOs.request;

import java.util.UUID;

public class DeleteBlogRequest {

    private UUID blogId;

    public UUID getBlogId() {
        return blogId;
    }

    public DeleteBlogRequest(UUID blogId) {
        this.blogId = blogId;
    }
}
