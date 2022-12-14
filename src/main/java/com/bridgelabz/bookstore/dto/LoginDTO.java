package com.bridgelabz.bookstore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {
    @NotBlank(message = "Email is mandatory")
    public String email;
    @NotBlank(message = "password is mandatory")
    public String password;
}
