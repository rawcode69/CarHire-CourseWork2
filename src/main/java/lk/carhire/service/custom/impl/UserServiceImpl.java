package lk.carhire.service.custom.impl;

import lk.carhire.dao.DaoFactory;
import lk.carhire.dao.custom.UserDao;
import lk.carhire.dto.UserDto;
import lk.carhire.entity.UserEntity;
import lk.carhire.entity.embedded.UserFullName;
import lk.carhire.service.custom.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao = (UserDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.USER);
    @Override
    public Integer saveUser(UserDto userDto) throws Exception {
        UserEntity userEntity = new UserEntity();

        UserFullName fullName = new UserFullName(userDto.getFirstName(),userDto.getLastName());

        userEntity.setUserName(userDto.getUserName());
        userEntity.setFullName(fullName);
        userEntity.setRole(userDto.getRole());
        userEntity.setPassword(userDto.getPassWord());

        return userDao.add(userEntity);
    }

    @Override
    public void updateUser(UserDto userDto) throws Exception {
        UserEntity userEntity = new UserEntity();

        UserFullName fullName = new UserFullName(userDto.getFirstName(),userDto.getLastName());

        userEntity.setId(userDto.getId());
        userEntity.setUserName(userDto.getUserName());
        userEntity.setFullName(fullName);
        userEntity.setRole(userDto.getRole());
        userEntity.setPassword(userDto.getPassWord());

        userDao.update(userEntity);
    }

    @Override
    public UserDto getUser(Integer id) throws Exception {
        UserEntity userEntity = userDao.get(id);
        UserDto userDto = new UserDto();

        userDto.setFirstName(userEntity.getFullName().getFirstName());
        userDto.setLastName(userEntity.getFullName().getLastName());
        userDto.setUserName(userEntity.getUserName());
        userDto.setRole(userEntity.getRole());
        userDto.setPassWord(userEntity.getPassword());

        return userDto;

    }

    @Override
    public void deleteUser(UserDto userDto) throws Exception {
        UserEntity userEntity = new UserEntity();

        UserFullName fullName = new UserFullName(userDto.getFirstName(),userDto.getLastName());

        userEntity.setId(userDto.getId());
        userEntity.setUserName(userDto.getUserName());
        userEntity.setFullName(fullName);
        userEntity.setRole(userDto.getRole());
        userEntity.setPassword(userDto.getPassWord());

        userDao.delete(userEntity);
    }

    @Override
    public List<UserDto> getAllUsers() throws Exception {

        List <UserDto> userDtos = new ArrayList<>();

        List <UserEntity> userEntities = userDao.getAll();

        for(UserEntity userEntity : userEntities){
            UserDto userDto = new UserDto();
            userDto.setId(userEntity.getId());
            userDto.setFirstName(userEntity.getFullName().getFirstName());
            userDto.setLastName(userEntity.getFullName().getLastName());
            userDto.setUserName(userEntity.getUserName());
            userDto.setRole(userEntity.getRole());
            userDto.setPassWord(userEntity.getPassword());

            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public UserDto getUserByUserName(String userName) throws Exception {

        UserEntity userEntity = userDao.getUserByUserName(userName);

        UserDto userDto = new UserDto();

        userDto.setUserName(userEntity.getUserName());
        userDto.setPassWord(userEntity.getPassword());
        userDto.setId(userEntity.getId());
        userDto.setFirstName(userEntity.getFullName().getFirstName());
        userDto.setLastName(userEntity.getFullName().getLastName());
        userDto.setRole(userEntity.getRole());

        return userDto;

    }


}
