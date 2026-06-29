package com.example.restapiuser.controller;

import com.example.restapiuser.dto.DeleteResponse;
import com.example.restapiuser.dto.UserCreateRequest;
import com.example.restapiuser.dto.UserResponse;
import com.example.restapiuser.entity.UserEntity;
import com.example.restapiuser.repository.UserRepository;
import com.example.restapiuser.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController      // @Controler + @ResonseBody
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    public  UserRestController(UserService userService) {
        this.userService = userService;
    }

    // GET http://localhost:8080/api/users
    // GET http://localhost:8080/api/users?keyword=user
    @GetMapping
    public List<UserResponse> list(
            @RequestParam(required = false) String keyword) {
        return  userService.findUsers(keyword);
    }

    // 회원가입 : Create : Insert
    // POST http://localhost:8080/api/users
    // @RequestBody : 넘어오는 파라미터는 json 이다
    @PostMapping
    public ResponseEntity<UserResponse> create(
            @Valid @RequestBody UserCreateRequest request) {
        UserResponse response = userService.createUser(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(response.userid()) // record 는 getUserid() -> response.userid()
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    // 회원삭제 DELETE DELETE(SQL)
    // DELETE   http://localhost:8080/api/users/test1
    @DeleteMapping("/{userid}")
    public DeleteResponse delete(@PathVariable String userid) {
        userService.deleteUser(userid);
        return new DeleteResponse(userid, true);
    }
}






