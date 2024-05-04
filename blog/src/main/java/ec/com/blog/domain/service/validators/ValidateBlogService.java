package ec.com.blog.domain.service.validators;

import ec.com.blog.domain.entities.Blog;
import io.jkratz.mediator.core.Request;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ValidateBlogService implements Request<Blog> {

    private UUID id;

    public ValidateBlogService(UUID id) {
        this.id = id;
    }
}
