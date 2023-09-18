package lk.carhire.service.custom;

import lk.carhire.dto.UserDto;
import lk.carhire.service.SuperService;

import java.util.List;

public interface UserService extends SuperService {
    Integer saveUser(UserDto userDto) throws Exception;

    void updateUser(UserDto userDto) throws Exception;

    UserDto getUser(Integer id) throws Exception;

    void deleteUser(UserDto userDto) throws Exception;

    List<UserDto> getAllUsers() throws Exception;
}
