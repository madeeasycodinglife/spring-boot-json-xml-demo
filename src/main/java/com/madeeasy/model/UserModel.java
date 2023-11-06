package com.madeeasy.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {
    private String username;
    private String password;
}
