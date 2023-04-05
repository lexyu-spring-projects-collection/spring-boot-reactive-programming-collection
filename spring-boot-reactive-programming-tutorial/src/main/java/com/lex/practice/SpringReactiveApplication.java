package com.lex.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
public class SpringReactiveApplication {

    public static void main(String[] args) {
        ReactorDebugAgent.init();

        SpringApplication.run(SpringReactiveApplication.class, args);
    }

}
