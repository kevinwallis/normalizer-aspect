package com.example.normalizeraspect.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Person {

    private String firstName;
    private String lastName;
    private LocalDateTime birthday;

}
