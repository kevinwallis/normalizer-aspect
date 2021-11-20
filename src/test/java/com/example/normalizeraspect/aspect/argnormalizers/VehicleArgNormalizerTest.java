package com.example.normalizeraspect.aspect.argnormalizers;

import com.example.normalizeraspect.model.Vehicle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class VehicleArgNormalizerTest {

    private final VehicleArgNormalizer normalizer = new VehicleArgNormalizer();

    private static final String plateValue = "be1234";
    private static final String plateCountry = "che";

    @Test
    void normalize() {
        final Vehicle vehicle = Vehicle.builder()
                .plateValue(plateValue)
                .plateCountry(plateCountry)
                .build();

        final Vehicle normalizedVehicle = this.normalizer.normalize(vehicle);

        assertAll(
                () -> assertThat(vehicle).isNotSameAs(normalizedVehicle),
                // Origin person has not changed
                () -> assertThat(vehicle.getPlateValue()).isEqualTo(plateValue),
                () -> assertThat(vehicle.getPlateCountry()).isEqualTo(plateCountry),
                // Normalized person
                () -> assertThat(normalizedVehicle.getPlateValue()).isEqualTo(plateValue),
                () -> assertThat(normalizedVehicle.getPlateCountry()).isEqualTo(plateCountry.toUpperCase()),
                // Properties are copied
                () -> assertThat(vehicle.getPlateValue()).isSameAs(normalizedVehicle.getPlateValue()),
                () -> assertThat(vehicle.getPlateCountry()).isNotSameAs(normalizedVehicle.getPlateCountry())
        );
    }
}