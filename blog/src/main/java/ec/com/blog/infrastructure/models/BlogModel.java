package ec.com.blog.infrastructure.models;

import ch.qos.logback.classic.db.names.ColumnName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class BlogModel {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String content;
}
