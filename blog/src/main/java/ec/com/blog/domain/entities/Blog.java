package ec.com.blog.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Blog {

    private UUID id;

    private String title;

    private String description;

    private String content;
}
