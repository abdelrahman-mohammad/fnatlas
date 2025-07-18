package com.fnatlas.api.dtos.auth;

import com.fnatlas.api.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    @NotBlank(message = "Token cannot be blank")
    private String token;

    @NotNull(message = "User cannot be null")
    @Valid
    private User user;
}
