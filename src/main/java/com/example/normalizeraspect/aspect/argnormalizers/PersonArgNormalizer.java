package com.example.normalizeraspect.aspect.argnormalizers;

import com.example.normalizeraspect.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Component
class PersonArgNormalizer implements ArgNormalizer<Person> {

    @Override
    public Person normalize(Person person) {
        Person normalizedPerson = new Person();

        final String firstName = person.getFirstName();
        if (StringUtils.hasText(firstName)) {
            normalizedPerson.setFirstName(firstName.toUpperCase());
        }

        final String lastName = person.getLastName();
        if (StringUtils.hasText(lastName)) {
            normalizedPerson.setLastName(lastName.toUpperCase());
        }

        normalizedPerson.setBirthday(person.getBirthday());
        return normalizedPerson;
    }

    @Override
    public boolean canNormalize(Person person) {
        return true;
    }
}
