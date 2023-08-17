package ec.com.blog.domain.interfaces.repositories.blog;

import ec.com.blog.domain.entities.Blog;
import ec.com.shared.domain.specification.ISpecificationBaseRepository;

public interface IBlogRepository extends ISpecificationBaseRepository<Blog> {

    Blog save(Blog blog);

}
