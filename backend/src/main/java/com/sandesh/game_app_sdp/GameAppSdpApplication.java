package com.sandesh.game_app_sdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.sandesh.game_app_sdp.repository")
@EntityScan(basePackages = "com.sandesh.game_app_sdp.modal")
public class GameAppSdpApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameAppSdpApplication.class, args);
    }
}