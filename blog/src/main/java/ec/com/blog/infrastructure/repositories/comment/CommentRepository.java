package ec.com.blog.infrastructure.repositories.comment;

import com.querydsl.jpa.impl.JPAQuery;
import ec.com.blog.domain.entities.Comment;
import ec.com.blog.domain.interfaces.repositories.comments.ICommentRepository;
import ec.com.blog.infrastructure.models.CommentModel;
import ec.com.blog.infrastructure.models.QCommentModel;
import ec.com.blog.infrastructure.persistence.mappers.CommentMapper;
import ec.com.shared.domain.specification.Criteria;
import ec.com.shared.infrastructure.models.DslBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class CommentRepository implements ICommentRepository {

    @Autowired
    private JpaCommentRepository commentRepository;

    @Autowired
    private CommentMapper mapper;

    @PersistenceContext
    EntityManager entityManager;

    private QCommentModel entityClass = QCommentModel.commentModel;

    private String entityName = "CommentModel";

    private JPAQuery<CommentModel> getQuery(Criteria criteria){
        final var dslBuilder = new DslBuilder(CommentModel.class, entityName);
        return (JPAQuery<CommentModel>) dslBuilder.getQuery(criteria, entityManager, entityClass);
    }

    @Override
    public List<Comment> find(Criteria criteria) {
        return mapper.toCommentList(getQuery(criteria).fetch());
    }

    @Override
    public Long count(Criteria criteria) {

        return 0L;
    }
}
