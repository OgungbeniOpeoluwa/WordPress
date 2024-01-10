package org.example.services;

import org.example.dto.WriteRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;

public interface UserService {
    void register(RegisterRequest register);

    void login(LoginRequest loginRequest);

    void write(WriteRequest request);
}
