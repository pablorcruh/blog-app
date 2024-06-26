package ec.com.blog.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Blog {

    private UUID id;

    private String title;

    private String description;

    private String content;

    private Set<Comment> comments;
}
