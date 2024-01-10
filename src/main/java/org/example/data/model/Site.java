package org.example.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String domainName;
    private  boolean isLocked = true;
    @OneToMany
    private List<Post> post;
    private Long userId;
}
