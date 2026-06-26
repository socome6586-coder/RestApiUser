package com.example.restapiuser.init;

import com.example.restapiuser.entity.UserEntity;
import com.example.restapiuser.repository.UserRepository;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements ApplicationRunner {
    // ApplicationRunner : 애플리케이션이 시작된 후 자동으로 실행되는
    //  run() 함수 가지있는 클래스
    private final UserRepository userRepository;
    // 생성자 주입
    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        createIfNotExists("admin", "admin1234", "관리자", "admin@example.com");
        createIfNotExists("user1", "user1234", "홍길동", "user1@example.com");
        createIfNotExists("user2", "user1234", "김자바", "user2@example.com");
        createIfNotExists("oracle", "oracle1234", "오라클", "oracle@example.com");
        createIfNotExists("restapi", "rest1234", "REST API", "restapi@example.com");
    }

    private void createIfNotExists(String userid, String passwd, String username, String email) {
        if (userRepository.existsById(userid)) {
            return;
        }
        userRepository.save(new UserEntity(userid, passwd, username, email));
    }
}
