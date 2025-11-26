package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTRequest {

    private String userName;
    private String password;
}
