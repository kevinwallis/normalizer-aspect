package com.example.normalizeraspect.aspect.argnormalizers;

import com.example.normalizeraspect.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;

@Component
class PersonArgNormalizer implements ArgNormalizer<Person> {

    @Override
    public Person normalize(Person obj) {
        Person person = new Person();

        final String firstName = obj.getFirstName();
        if (StringUtils.hasText(firstName)) {
            person.setFirstName(firstName.toUpperCase());
        }

        final String lastName = obj.getLastName();
        if (StringUtils.hasText(lastName)) {
            person.setLastName(lastName.toUpperCase());
        }

        person.setBirthday(obj.getBirthday());
        return person;
    }

    @Override
    public Type getNormalizedType() {
        return Person.class;
    }
}
