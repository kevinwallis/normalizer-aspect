package com.example.normalizeraspect.services;

import com.example.normalizeraspect.aspect.NormalizeArgs;
import com.example.normalizeraspect.model.Person;
import com.example.normalizeraspect.model.Vehicle;
import org.springframework.stereotype.Controller;

@Controller
public class MyService {

    @NormalizeArgs
    public void testPersonNormalizer(Person person) {
        System.out.println(person);
    }

    @NormalizeArgs
    public void testVehicleNormalizer(Vehicle vehicle) {
        System.out.println(vehicle);
    }

    @NormalizeArgs
    public void testPersonAndVehicleNormalizer(Person person, Vehicle vehicle) {
        System.out.println(person + " | " + vehicle);
    }

    @NormalizeArgs
    public void testVehicleAndPersonNormalizer(Vehicle vehicle, Person person) {
        System.out.println(vehicle + " | " + person);
    }

    @NormalizeArgs
    public void testPersonNormalizer(Person person, String text) {
        System.out.println(person + " | " + text);
    }
}
