package com.example.normalizeraspect.aspect.argnormalizers;

import com.example.normalizeraspect.model.Vehicle;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
class VehicleArgNormalizer implements ArgNormalizer<Vehicle> {

    @Override
    public Vehicle normalize(Vehicle vehicle) {
        Vehicle normalizedVehicle = new Vehicle();

        final String plateCountry = vehicle.getPlateCountry();
        if (StringUtils.hasText(plateCountry)) {
            normalizedVehicle.setPlateCountry(plateCountry.toUpperCase());
        }

        normalizedVehicle.setPlateValue(vehicle.getPlateValue());
        return normalizedVehicle;
    }

    @Override
    public boolean canNormalize(Vehicle vehicle) {
        return true;
    }
}
