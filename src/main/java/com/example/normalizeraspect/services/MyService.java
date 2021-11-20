package com.example.normalizeraspect.services;

import com.example.normalizeraspect.aspect.NormalizeArgs;
import com.example.normalizeraspect.model.Person;
import com.example.normalizeraspect.model.Vehicle;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Controller;

@Controller
public class MyService {

    protected Properties properties;

    @NormalizeArgs
    public void testPersonNormalizer(Person person) {
        this.properties = Properties.builder().person(person).build();
        System.out.println(properties);
    }

    @NormalizeArgs
    public void testVehicleNormalizer(Vehicle vehicle) {
        this.properties = Properties.builder().vehicle(vehicle).build();
        System.out.println(properties);
    }

    @NormalizeArgs
    public void testPersonAndVehicleNormalizer(Person person, Vehicle vehicle) {
        this.properties = Properties.builder().person(person).vehicle(vehicle).build();
        System.out.println(properties);
    }

    @NormalizeArgs
    public void testVehicleAndPersonNormalizer(Vehicle vehicle, Person person) {
        this.properties = Properties.builder().vehicle(vehicle).person(person).build();
        System.out.println(properties);
    }

    @NormalizeArgs
    public void testPersonAndTextNormalizer(Person person, String text) {
        this.properties = Properties.builder().person(person).text(text).build();
        System.out.println(properties);
    }

    public Properties getProperties() {
        return this.properties;
    }

    @Getter
    @ToString
    @Builder
    static class Properties {
        private Person person;
        private Vehicle vehicle;
        private String text;
    }
}
