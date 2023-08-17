package ec.com.blog.infrastructure.persistence.mappers;

import ec.com.blog.domain.entities.Blog;
import ec.com.blog.infrastructure.models.BlogModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BlogMapper.class})
public interface BlogMapper {

    BlogModel toBlogModel(Blog blog);

    @InheritInverseConfiguration
    Blog toBlog(BlogModel blogModel);

    List<BlogModel> toBlogModelList(List<Blog> blogList);

    @InheritInverseConfiguration
    List<Blog> toBlogList(List<BlogModel> blogModelList);


}
