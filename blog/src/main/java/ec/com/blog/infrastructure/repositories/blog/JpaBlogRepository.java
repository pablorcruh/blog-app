package ec.com.blog.infrastructure.repositories.blog;

import ec.com.blog.infrastructure.models.BlogModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaBlogRepository extends JpaRepository<BlogModel, UUID> {
}
