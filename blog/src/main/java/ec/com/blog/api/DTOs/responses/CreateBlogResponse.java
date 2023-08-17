package ec.com.blog.api.DTOs.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CreateBlogResponse {

    private String content;

    private String description;

    private String title;
}
