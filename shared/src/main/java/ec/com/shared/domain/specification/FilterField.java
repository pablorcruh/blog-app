package ec.com.shared.domain.specification;

import lombok.Getter;

@Getter
public class FilterField {
    private String fieldName;

    public FilterField(String fieldName) {
        this.fieldName = fieldName;
    }
}
