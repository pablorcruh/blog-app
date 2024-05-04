package ec.com.blog.infrastructure.persistence.mappers;

import ec.com.blog.domain.entities.Comment;
import ec.com.blog.infrastructure.models.CommentModel;
import org.mapstruct.InheritInverseConfiguration;

import java.util.List;

public interface CommentMapper {

    CommentModel toCommentModel(Comment comment);

    @InheritInverseConfiguration
    Comment toComment(CommentModel commentModel);

    List<CommentModel> toCommentModelList(List<Comment> comments);

    @InheritInverseConfiguration
    List<Comment> toCommentList(List<CommentModel> comments);
}
