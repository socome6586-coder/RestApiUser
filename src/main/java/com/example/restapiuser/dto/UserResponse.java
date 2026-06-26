package com.example.restapiuser.dto;

import com.example.restapiuser.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record UserResponse(
        String  userid,
        String  username,
        String  email,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime indate
) {
    public static UserResponse from(UserEntity userEntity ) {
        return new UserResponse(
                userEntity.getUserid(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getIndate()
        );
    }
}








