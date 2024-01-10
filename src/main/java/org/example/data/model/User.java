package org.example.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Enumerated
    private UserRole role = UserRole.ADMINISTRATION;
    private String password;
    private String email;
}
