package ec.com.blog.infrastructure.repositories.blog;

import com.querydsl.jpa.impl.JPAQuery;
import ec.com.blog.domain.entities.Blog;

import ec.com.blog.domain.interfaces.repositories.blog.IBlogRepository;
import ec.com.blog.infrastructure.models.BlogModel;
import ec.com.blog.infrastructure.models.QBlogModel;
import ec.com.blog.infrastructure.persistence.mappers.BlogMapper;
import ec.com.shared.domain.specification.Criteria;
import ec.com.shared.infrastructure.models.DslBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BlogRepository implements IBlogRepository {

    @Autowired
    private JpaBlogRepository blogRepository;

    @Autowired
    private BlogMapper mapper;

    @PersistenceContext
    EntityManager entityManager;

    private QBlogModel entityClass = QBlogModel.blogModel;

    private String entityName = "blogModel";

    private JPAQuery<BlogModel> getQuery(Criteria criteria){
        final var dslBuilder = new DslBuilder(BlogModel.class, entityName);
        return (JPAQuery<BlogModel>) dslBuilder.getQuery(criteria, entityManager, entityClass);
    }

    @Override
    public List<Blog> find(Criteria criteria) {
        return mapper.toBlogList(getQuery(criteria).fetch());
    }

    @Override
    public Long count(Criteria criteria) {
        return getQuery(criteria).fetchCount();
    }
}
