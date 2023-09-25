package lk.carhire.controller;


import lk.carhire.dto.UserDto;
import lk.carhire.service.ServiceFactory;
import lk.carhire.service.custom.UserService;

import java.util.List;

public class UserController {

UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceFactory.serviceType.USER);
    public Integer saveUser(UserDto userDto) throws Exception {

        return userService.saveUser(userDto);
    }



    public void updateUser(UserDto userDto) throws Exception {
        userService.updateUser(userDto);
    }


    public UserDto getUser(Integer id) throws Exception {
        return userService.getUser(id);
    }

    public void deleteUser(UserDto userDto) throws Exception {
        userService.deleteUser(userDto);
    }

    public List<UserDto> getAllUsers() throws Exception {
        return userService.getAllUsers();
    }


    public UserDto getUserByUserName(String userName) throws Exception {
        return userService.getUserByUserName(userName);
    }
}
