package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WriteRequest {
    private String email;
    private String domainName;
    private String tile;
    private String body;
}
