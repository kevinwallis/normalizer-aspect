package com.example.normalizeraspect.aspect.argnormalizers;

import com.example.normalizeraspect.model.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonArgNormalizerTest {

    private final PersonArgNormalizer normalizer = new PersonArgNormalizer();

    private static final String firstName = "John";
    private static final String lastName = "Doe";
    private static final LocalDateTime birthday = LocalDateTime.now();

    @Test
    void normalize() {
        final Person person = Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .birthday(birthday)
                .build();

        final Person normalizedPerson = this.normalizer.normalize(person);

        assertAll(
                () -> assertThat(person).isNotSameAs(normalizedPerson),
                // Origin person has not changed
                () -> assertThat(person.getFirstName()).isEqualTo(firstName),
                () -> assertThat(person.getLastName()).isEqualTo(lastName),
                () -> assertThat(person.getBirthday()).isEqualTo(birthday),
                // Normalized person
                () -> assertThat(normalizedPerson.getFirstName()).isEqualTo(firstName.toUpperCase()),
                () -> assertThat(normalizedPerson.getLastName()).isEqualTo(lastName.toUpperCase()),
                () -> assertThat(normalizedPerson.getBirthday()).isEqualTo(birthday),
                // Properties are copied
                () -> assertThat(person.getFirstName()).isNotSameAs(normalizedPerson.getFirstName()),
                () -> assertThat(person.getLastName()).isNotSameAs(normalizedPerson.getLastName()),
                () -> assertThat(person.getBirthday()).isSameAs(normalizedPerson.getBirthday())
        );
    }
}