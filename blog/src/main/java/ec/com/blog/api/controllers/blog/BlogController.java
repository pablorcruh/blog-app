package ec.com.blog.api.controllers.blog;

import ec.com.blog.api.DTOs.request.CreateBlogRequest;
import ec.com.blog.api.DTOs.responses.CreateBlogResponse;
import ec.com.blog.application.commands.blog.CreateBlogCommand;
import io.jkratz.mediator.core.Mediator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class BlogController {

    private final Mediator mediator;

    public BlogController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<CreateBlogResponse> createBlog(@Valid @RequestBody CreateBlogRequest blogRequest){
        CreateBlogCommand command = blogRequest.toApplicationRequest();
        this.mediator.dispatch(command);
        CreateBlogResponse blogResponse = new CreateBlogResponse(blogRequest.getContent(), blogRequest.getDescription(),blogRequest.getTitle());
        return new ResponseEntity<>(blogResponse, HttpStatus.CREATED);
    }
}
