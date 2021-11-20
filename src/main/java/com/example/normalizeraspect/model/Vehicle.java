package com.example.normalizeraspect.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private String plateValue;
    private String plateCountry;

}
