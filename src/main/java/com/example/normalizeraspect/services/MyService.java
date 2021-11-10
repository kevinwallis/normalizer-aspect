package com.example.normalizeraspect.services;

import com.example.normalizeraspect.aspect.Normalize;
import com.example.normalizeraspect.model.Person;
import com.example.normalizeraspect.model.Vehicle;
import org.springframework.stereotype.Controller;

@Controller
public class MyService {

    @Normalize
    public void testPersonNormalizer(Person person) {
        System.out.println(person);
    }

    @Normalize
    public void testVehicleNormalizer(Vehicle vehicle) {
        System.out.println(vehicle);
    }

    @Normalize
    public void testPersonAndVehicleNormalizer(Person person, Vehicle vehicle) {
        System.out.println(person + " | " + vehicle);
    }

    @Normalize
    public void testVehicleAndPersonNormalizer(Vehicle vehicle, Person person) {
        System.out.println(vehicle + " | " + person);
    }

    @Normalize
    public void testPersonNormalizer(Person person, String text) {
        System.out.println(person + " | " + text);
    }
}
