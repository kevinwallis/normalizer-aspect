package com.example.normalizeraspect;

import com.example.normalizeraspect.model.Person;
import com.example.normalizeraspect.model.Vehicle;
import com.example.normalizeraspect.services.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.time.LocalDateTime;

@EnableAspectJAutoProxy(proxyTargetClass=true)
@SpringBootApplication(scanBasePackageClasses = {Application.class})
public class Application {

    public static void main(String[] args) {
    }

}
