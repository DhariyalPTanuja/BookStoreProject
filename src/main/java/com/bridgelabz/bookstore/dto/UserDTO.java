package com.bridgelabz.bookstore.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

public @Data class UserDTO {
    @NotBlank(message = "First Name is mandatory")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-z]{3,}$", message = "First name must be start with capital letter and minimum 3 char")
   public String firstName;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-z]{3,}$", message = "Last name must be start with capital letter and minimum 3 char")
public String lastName;
    @Column(unique = true)
    @NotBlank(message = "Email is mandatory")
 public String email;
    @NotBlank(message = "Address not empty")
    public String address;
    public LocalDate dob;
    @NotBlank(message = "new password is mandatory")
    public String password;
   //public char[] password;
    @NotBlank(message = "confirm password is mandatory")
    public String confirmPassword;
    //public char[] confirmPassword;
    
    public UserDTO(String firstName, String lastName, String email, String address, LocalDate dob, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

}
