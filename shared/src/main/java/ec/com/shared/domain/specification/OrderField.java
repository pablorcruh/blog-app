package ec.com.shared.domain.specification;

import lombok.Getter;

@Getter
public class OrderField {
    private String fieldName;

    public OrderField(String fieldName) {
        this.fieldName = fieldName;
    }
}
