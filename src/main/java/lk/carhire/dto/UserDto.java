package lk.carhire.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class UserDto {

    Integer id;
    String userName;
    String passWord;
    String role;

    String firstName;
    String lastName;

    public UserDto() {
    }

    public UserDto(Integer id, String userName, String passWord, String role, String firstName, String lastName) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
