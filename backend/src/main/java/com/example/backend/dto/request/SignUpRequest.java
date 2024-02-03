package com.example.backend.dto.request;

<<<<<<< HEAD
import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.UniqueConstraint;
=======
>>>>>>> 54a6bb6c909c10a24cc66aa4cc064847a90b8442
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @NotNull(message = "First Name is required")
    private String firstName;
    @NotNull(message = "Last Name is required")
    private String lastName;
    @NotNull(message = "Password is required")
    private String password;
    @NotNull(message = "Email is required")
<<<<<<< HEAD
    // @UniqueElements
    @Email(message = "Email should be valid")
    private String email;
    private com.example.backend.entities.Role role;
}
=======
    @Email(message = "Email should be valid")
    private String email;
    private com.example.backend.entities.Role role;
}
>>>>>>> 54a6bb6c909c10a24cc66aa4cc064847a90b8442
