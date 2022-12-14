package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.model.UserModel;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserService {

    List<UserModel> userList = new ArrayList<>();
    @Autowired
    UserRepository repoUser;
    @Autowired
    TokenUtil tokenUtil;

    @Override
    public String welcomeMsg() {
        return "Welcome to the Book Store Project ";
    }

    //saving a specific record by using the method save() of CrudRepository
    @Override
    public UserModel userRegistration(UserDTO userDTO) {
        UserModel userModelObj = new UserModel(userDTO);
        return repoUser.save(userModelObj);
    }

    //getting all the record by using the method findAll() of CrudRepository
    @Override
    public List<UserModel> getAll() {
        userList = repoUser.findAll();
        return userList;
    }

    //getting a specific record by using the method findById() of CrudRepository
    @Override
    public UserModel getUserDataByID(long id) {
        Optional<UserModel> userModelObj = repoUser.findById(id);
        if (userModelObj.isPresent())
            return userModelObj.get();
        else
            throw new UserException("Id is not present in database");
    }

    @Override
    public UserModel getByToken(String token) {
        long userToken = tokenUtil.decodeToken(token);
        Optional<UserModel> userModelObj = repoUser.findById(userToken);
        if (userModelObj.isPresent())
            return repoUser.findById(userToken).get();
        else
            throw new UserException("Token is not match");

    }

    @Override
    public UserModel getUserDataByEmail(String email) {
        UserModel userDataList = repoUser.findDataByMail(email);
        if (userDataList != null)
            throw new UserException("Sorry!!! we can not find the user email:" + email);
        else
            return userDataList;

    }

    //updating an existing record by using the method save() of CrudRepository
    @Override
    public UserModel updateUserByID(UserDTO userDTO, long id) {
        Optional<UserModel> checkID = repoUser.findById(id);
        if (checkID.isPresent()) {
            UserModel userModel = new UserModel(userDTO);
            userModel.setUserID(id);
            UserModel userModelUpdate = repoUser.save(userModel);
            return userModelUpdate;
        } else
            throw new UserException("Id is not found ,updation fail");

    }

    //updating an existing record by using the method save() of CrudRepository
    @Override
    public UserModel updateUserByEmail(UserDTO userDTO, String email) {
        UserModel checkMail = repoUser.findDataByMail(email);
        Long id = checkMail.getUserID();
        if (repoUser.findDataByMail(email) != null) {
            UserModel userModel = new UserModel(userDTO);
            userModel.setEmail(email);
            userModel.setUserID(id);
            UserModel userModelUpdate = repoUser.save(userModel);
            return userModelUpdate;
        } else
            throw new UserException("email is not found ,updation fail");
    }

    //login user
    @Override
    public String loginUser(LoginDTO loginDTO) {
        UserModel checkLoginData = repoUser.checkLoginData(loginDTO.email, loginDTO.password);
        if (checkLoginData != null)
            return "Congratulation !! You have logged in successfully..";
        else
            throw new UserException("Email or password is incorrect,login fail");
    }

    @Override
    public UserModel changePassword(String password, String confirmPassword, String email) {
        UserModel fetchUserData = repoUser.changeUserPassword(password, confirmPassword, email);
        return repoUser.save(fetchUserData);
    }

    // create a forget or change password method
    @Override
    public UserModel forgetPassword(UserDTO userDTO, String email) {
        UserModel user = repoUser.findDataByMail(email);
        if (user != null) {
            user.setPassword(userDTO.getPassword());
            user.setConfirmPassword(userDTO.getConfirmPassword());
            repoUser.save(user);
            return user;
        } else
            throw new UserException("Sorry !! we can not find the user email:");
    }

    @Override
    public String deleteUserData(long id) throws UserException {
        Optional<UserModel> checkID = repoUser.findById(id);
        if (checkID.isPresent())
            repoUser.deleteById(id);
        else
            return "id is not found";
        return "data delete";
    }


}

