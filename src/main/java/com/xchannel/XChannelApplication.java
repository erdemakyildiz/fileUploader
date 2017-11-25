package com.xchannel;

import com.xchannel.repository.UserRepository;
import com.xchannel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableMongoRepositories
@SpringBootApplication
public class XChannelApplication {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(XChannelApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                userService.findAll().forEach(user -> {
                    if (user.getUsername().equals("erdem")){
                        user.setRole("ADMIN");
                        userRepository.save(user);
                    }
                });
            }
        };
    }
}
