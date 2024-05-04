package ec.com.blog.application.queries.blog;

import ec.com.blog.api.DTOs.responses.ReadBlogResponse;
import io.jkratz.mediator.core.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor
public class ReadBlogQuery implements Request<ReadBlogResponse> {

    @NotBlank
    private UUID id;

}
