package lk.carhire.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class CategoryDto {

Integer id;
String name;

    public CategoryDto() {
    }

    public CategoryDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}

