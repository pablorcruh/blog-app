package ec.com.blog.application.commands.blog;

import ec.com.blog.api.DTOs.responses.CreateBlogResponse;
import io.jkratz.mediator.core.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBlogCommand implements Request<CreateBlogResponse> {

    private String content;

    private String description;

    private String title;

}
