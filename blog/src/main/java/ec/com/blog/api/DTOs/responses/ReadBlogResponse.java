package ec.com.blog.api.DTOs.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadBlogResponse {

    private UUID id;

    private String content;

    private String description;

    private String title;
}
