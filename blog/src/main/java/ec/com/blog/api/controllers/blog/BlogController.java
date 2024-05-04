package ec.com.blog.api.controllers.blog;

import ec.com.blog.api.DTOs.request.CreateBlogRequest;
import ec.com.blog.api.DTOs.request.UpdateBlogRequest;
import ec.com.blog.api.DTOs.responses.CreateBlogResponse;
import ec.com.blog.api.DTOs.responses.ReadBlogResponse;
import ec.com.blog.application.commands.blog.CreateBlogCommand;
import ec.com.blog.application.commands.blog.DeleteBlogCommand;
import ec.com.blog.application.commands.blog.UpdateBlogCommand;
import ec.com.blog.application.queries.blog.ReadBlogQuery;
import ec.com.blog.application.queries.blog.ReadBlogsQuery;
import ec.com.shared.application.dto.response.GetEntitiesResponse;
import io.jkratz.mediator.core.Mediator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<ReadBlogResponse> readBlog (@PathVariable("id") UUID id) {
        var query = new ReadBlogQuery(id);
        var blogResponse = this.mediator.dispatch(query);
        if(blogResponse != null){
            return new ResponseEntity<>(blogResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ReadBlogResponse(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<GetEntitiesResponse> getAll(@RequestParam MultiValueMap<String, String> requestParams) {
        var query = new ReadBlogsQuery(requestParams);
        return new ResponseEntity<>(this.mediator.dispatch(query), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void>updateBlog(@Valid @RequestBody UpdateBlogRequest request, @PathVariable UUID id){
        var command = new UpdateBlogCommand(id, request.getTitle(), request.getDescription(), request.getContent());
        this.mediator.dispatch(command);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable UUID id){
        this.mediator.dispatch(new DeleteBlogCommand(id));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
