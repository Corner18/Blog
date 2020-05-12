package ru.itis.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegistrationDto {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String phoneNumber;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;

}

