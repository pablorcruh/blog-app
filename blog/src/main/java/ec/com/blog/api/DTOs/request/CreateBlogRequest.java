package ec.com.blog.api.DTOs.request;

import ec.com.blog.application.commands.blog.CreateBlogCommand;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class
CreateBlogRequest {

    @NotNull
    @NotBlank
    private String content;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String title;

    public CreateBlogCommand toApplicationRequest(){
        return new CreateBlogCommand(content, description, title);
    }

}
