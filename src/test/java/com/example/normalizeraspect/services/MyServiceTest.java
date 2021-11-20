package com.example.normalizeraspect.services;

import com.example.normalizeraspect.model.Person;
import com.example.normalizeraspect.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyServiceTest {

    @Autowired
    private MyService myService;

    private Person person;
    private Person normalizedPerson;

    private Vehicle vehicle;
    private Vehicle normalizedVehicle;

    private String text;

    @BeforeEach
    void setup() {
        person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setBirthday(LocalDateTime.now());

        normalizedPerson = new Person();
        normalizedPerson.setFirstName(person.getFirstName().toUpperCase());
        normalizedPerson.setLastName(person.getLastName().toUpperCase());
        normalizedPerson.setBirthday(person.getBirthday());

        vehicle = new Vehicle();
        vehicle.setPlateValue("be1234");
        vehicle.setPlateCountry("che");

        normalizedVehicle = new Vehicle();
        normalizedVehicle.setPlateValue(vehicle.getPlateValue());
        normalizedVehicle.setPlateCountry(vehicle.getPlateCountry().toUpperCase());

        text = "text";
    }

    @Test
    void testPersonNormalizer() {
        this.myService.testPersonNormalizer(person);

        final MyService.Properties properties = this.myService.getProperties();
        assertAll(
                () -> assertThat(properties.getPerson()).isNotNull(),
                () -> assertThat(properties.getVehicle()).isNull(),
                () -> assertThat(properties.getText()).isNull(),
                // Check person
                () -> assertThat(properties.getPerson()).usingRecursiveComparison().isEqualTo(normalizedPerson)
        );
    }

    @Test
    void testVehicleNormalizer() {
        this.myService.testVehicleNormalizer(vehicle);

        final MyService.Properties properties = this.myService.getProperties();
        assertAll(
                () -> assertThat(properties.getPerson()).isNull(),
                () -> assertThat(properties.getVehicle()).isNotNull(),
                () -> assertThat(properties.getText()).isNull(),
                // Check vehicle
                () -> assertThat(properties.getVehicle()).usingRecursiveComparison().isEqualTo(normalizedVehicle)
        );
    }

    @Test
    void testPersonAndVehicleNormalizer() {
        this.myService.testPersonAndVehicleNormalizer(person, vehicle);

        final MyService.Properties properties = this.myService.getProperties();
        assertAll(
                () -> assertThat(properties.getPerson()).isNotNull(),
                () -> assertThat(properties.getVehicle()).isNotNull(),
                () -> assertThat(properties.getText()).isNull(),
                // Check person and vehicle
                () -> assertThat(properties.getPerson()).usingRecursiveComparison().isEqualTo(normalizedPerson),
                () -> assertThat(properties.getVehicle()).usingRecursiveComparison().isEqualTo(normalizedVehicle)
        );
    }

    @Test
    void testVehicleAndPersonNormalizer() {
        this.myService.testVehicleAndPersonNormalizer(vehicle, person);

        final MyService.Properties properties = this.myService.getProperties();
        assertAll(
                () -> assertThat(properties.getPerson()).isNotNull(),
                () -> assertThat(properties.getVehicle()).isNotNull(),
                () -> assertThat(properties.getText()).isNull(),
                // Check person and vehicle
                () -> assertThat(properties.getPerson()).usingRecursiveComparison().isEqualTo(normalizedPerson),
                () -> assertThat(properties.getVehicle()).usingRecursiveComparison().isEqualTo(normalizedVehicle)
        );
    }

    @Test
    void testPersonAndTextNormalizer() {
        this.myService.testPersonAndTextNormalizer(person, text);

        final MyService.Properties properties = this.myService.getProperties();
        assertAll(
                () -> assertThat(properties.getPerson()).isNotNull(),
                () -> assertThat(properties.getVehicle()).isNull(),
                () -> assertThat(properties.getText()).isNotNull(),
                // Check person and text
                () -> assertThat(properties.getPerson()).usingRecursiveComparison().isEqualTo(normalizedPerson),
                () -> assertThat(properties.getText()).isEqualTo(text)
        );
    }
}