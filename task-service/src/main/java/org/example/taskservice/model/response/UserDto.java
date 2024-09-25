package org.example.taskservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
