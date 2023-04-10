package com.example.webshop.models.dto;

import com.example.webshop.models.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;

    private List<Role> roles = new ArrayList<>();

    public String getRolesString() {
        StringBuilder resultStr = new StringBuilder();
        for (Role role : this.roles) {
            resultStr.append(role.getName()).append(" ");
        }
        return resultStr.toString();
    }
}
