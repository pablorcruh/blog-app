package ec.com.blog.infrastructure.repositories.comment;

import ec.com.blog.infrastructure.models.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCommentRepository extends JpaRepository<CommentModel, UUID> {
}
