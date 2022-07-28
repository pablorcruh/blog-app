package ec.com.shared.domain.specification;

import java.util.List;

public interface ISpecificationBaseRepository<T> {
    List<T> find(Criteria criteria);
    Long count(Criteria criteria);
}