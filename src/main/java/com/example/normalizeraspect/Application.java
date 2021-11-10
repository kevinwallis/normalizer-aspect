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
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        MyService myService = context.getBean(MyService.class);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setBirthday(LocalDateTime.now());

        Vehicle vehicle = new Vehicle();
        vehicle.setPlateValue("be1234");
        vehicle.setPlateCountry("che");

        myService.testPersonNormalizer(person);
        myService.testVehicleNormalizer(vehicle);
        myService.testPersonNormalizer(person, "my text");
        myService.testPersonAndVehicleNormalizer(person, vehicle);
        myService.testVehicleAndPersonNormalizer(vehicle, person);
    }

}
