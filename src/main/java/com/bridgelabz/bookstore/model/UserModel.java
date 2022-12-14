package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "User_Information")
public @Data class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_ID")
    private long userID;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private LocalDate dob;
    //private char[] password;
    private String password;
    //private char[] confirmPassword;
    private String confirmPassword;

    public UserModel() {
    }

    public void updateUserModel(UserDTO userDTO) {
        this.firstName = userDTO.firstName;
        this.lastName = userDTO.lastName;
        this.email = userDTO.email;
        this.address = userDTO.address;
        this.dob = userDTO.dob;
        this.password = userDTO.password;
        this.confirmPassword = userDTO.confirmPassword;
    }


    public UserModel(UserDTO userDTO) {
        this.updateUserModel(userDTO);

    }
}
