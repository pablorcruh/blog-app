package ec.com.blog.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private UUID id;
    private String name;
    private String email;
    private String body;
    private Blog blog;
}
