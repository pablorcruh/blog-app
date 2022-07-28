package ec.com.shared.api.Controller;

import ec.com.shared.application.dto.response.GetEntitiesResponse;
import ec.com.shared.application.queries.ReadEntitiesBaseQuery;
import ec.com.shared.domain.models.FilterPaginationQueryModel;
import io.jkratz.mediator.core.Mediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadEntitiesBaseController{
    private static final Logger logger = LoggerFactory.getLogger(ReadEntitiesBaseController.class);
    protected final Mediator mediator;

    @Autowired
    public ReadEntitiesBaseController(Mediator mediator) {
        this.mediator = mediator;
    }

    protected ResponseEntity<GetEntitiesResponse> getEntities(FilterPaginationQueryModel filterPaginationQueryModel){
        ReadEntitiesBaseQuery query = new ReadEntitiesBaseQuery(
                filterPaginationQueryModel.getOrders(),
                filterPaginationQueryModel.getFilters(),
                filterPaginationQueryModel.getPage(),
                filterPaginationQueryModel.getPageSize()
        );
        logger.info("----- Sending query filter: {} {}", query.getClass().getName(), query);
        return new ResponseEntity<>( this.mediator.dispatch(query), HttpStatus.CREATED);
    }
}
