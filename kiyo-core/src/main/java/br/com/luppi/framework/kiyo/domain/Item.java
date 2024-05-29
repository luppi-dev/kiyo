package br.com.luppi.framework.kiyo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String name;
    private String description;
    private String value;

    public static Item fromEnumValue(EnumValue value) {
        return new Item(
                value.getName(),
                value.getDescription(),
                value.getValue().toString()
        );
    }
}
