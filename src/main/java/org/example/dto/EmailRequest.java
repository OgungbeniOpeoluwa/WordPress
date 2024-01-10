package org.example.dto;

import lombok.Data;

@Data

public class EmailRequest {
    private String reciepent;
    private String Title;
    private String message;
}
