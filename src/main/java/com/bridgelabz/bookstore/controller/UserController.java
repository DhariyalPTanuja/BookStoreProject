package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.model.EmailModel;
import com.bridgelabz.bookstore.model.UserModel;
import com.bridgelabz.bookstore.service.EmailService;
import com.bridgelabz.bookstore.service.IUserService;
import com.bridgelabz.bookstore.util.TokenUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
@RequestMapping ("/bookStoreApp/user")
public class UserController {
    @Autowired
    IUserService serviceUser;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailService serviceEmail;
    List<UserModel> userModelList = new ArrayList<>();
//Create Api for welcome msg
    @GetMapping("/welcome")
   public String welcomeMsg(){
return serviceUser.welcomeMsg();
    }
    //Creating a post mapping with token that post the data in the database
//Create Api for registering the new user Record
    @PostMapping("/registerMail")
    public ResponseEntity<ResponseDTO> userRegistrationSend(@Valid @RequestBody UserDTO userDTO){
        UserModel userModel = serviceUser.userRegistration(userDTO);
        String token = tokenUtil.createToken(userModel.getUserID());
        EmailModel emailModel = new EmailModel(userModel.getEmail(),
                "Account sign-up successfully", "user " + userModel + " Token "+token);
        serviceEmail.sendEmail(emailModel);
        ResponseDTO responseDto = new ResponseDTO(" New user registered send in mail",userModel,token);
        ResponseEntity<ResponseDTO> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        log.info(token);
        return response;

    }
    //Create a get mapping Api that retrieves all the data from the database
    @GetMapping("/getAllRecord")
    public ResponseEntity<ResponseDTO> getAll(){
        userModelList = serviceUser.getAll();
        ResponseDTO responseDto = new ResponseDTO("fetch All user data successfully",userModelList,null);
        ResponseEntity<ResponseDTO> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
    //Creating Api for retrieve user Record by ID
    @GetMapping("/getRecordById/{id}")
    public ResponseEntity<ResponseDTO> getUserDatByID(@PathVariable long id){
        UserModel userModel = serviceUser.getUserDataByID(id);
        ResponseDTO responseDTO = new ResponseDTO("fetch user data successfully",userModel,null);
        ResponseEntity<ResponseDTO> response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
        return response;
    }
    //Creating Api for retrieve user Record by Token
    @GetMapping("/getRecordByToken")
    public ResponseEntity<ResponseDTO> getByToken(@RequestParam(value = "token" ,defaultValue = "") String token){
        UserModel userModel = serviceUser.getByToken(token);
        ResponseDTO responseDTO = new ResponseDTO("fetch user data for particular token",userModel,token);
        ResponseEntity<ResponseDTO> response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
        return response;
  }
  //Creating Api for retrieve user Record by Email
    @GetMapping("/getRecordByEmail")
    public ResponseEntity<ResponseDTO> getUserDatByEmail(@RequestParam String email){
        UserModel userModel = serviceUser.getUserDataByEmail(email);
        ResponseDTO responseDTO =  new ResponseDTO("fetch user data successfully by email",userModel,null);
        ResponseEntity<ResponseDTO> response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
        return response;
    }

    @PutMapping("/updateBYId/{id}")
    public ResponseEntity<ResponseDTO>updateUserByID(@Valid @RequestBody UserDTO userDTO, @PathVariable long id) throws UserException {
        UserModel userModel = serviceUser.updateUserByID(userDTO,id);
        ResponseDTO responseDto = new ResponseDTO("existing user data update successfully", userModel,null);
        ResponseEntity<ResponseDTO> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
//creating Api for Updating User details by email
    @PutMapping("/updateByEmail/{email}")
    public ResponseEntity<ResponseDTO> updateUserByEmail(@Valid @RequestBody UserDTO userDTO,@PathVariable String email){
        UserModel userModel = serviceUser.updateUserByEmail(userDTO,email);
        EmailModel emailModel = new EmailModel(email,"Your Account is Updated Successfully",userModel.getFirstName());
        serviceEmail.sendEmail(emailModel);
        ResponseDTO responseDto = new ResponseDTO("existing user data update successfully",userModel,null);
        ResponseEntity<ResponseDTO> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
//create Api for delete record
    @DeleteMapping("/deleteRecord/{id}")
    public ResponseEntity<String> deleteAddressData(@PathVariable long id){
        serviceUser.deleteUserData(id);
        return new ResponseEntity<>("delete the data successfully",HttpStatus.OK);

    }
    //Create Api for  user login
 @PostMapping("/loginUser")
    public ResponseEntity<ResponseDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO){
        String userModel = serviceUser.loginUser(loginDTO);
        ResponseDTO responseDTO = new ResponseDTO("Logged in  Successfully",userModel,null);
        ResponseEntity<ResponseDTO> response = new ResponseEntity<>(responseDTO,HttpStatus.OK);
        return response;
 }
 //Create Api for Changing or updating password using email

    @PutMapping("/forgetPassword/{email}")
    public  ResponseEntity<ResponseDTO> forgetPassword(@Valid @RequestBody UserDTO userDTO, @PathVariable String email){
        UserModel userModel=serviceUser.forgetPassword(userDTO,email);
        ResponseDTO responseDto = new ResponseDTO("Password successfully changed",userModel,null);
        ResponseEntity<ResponseDTO> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;

    }



}
