package com.alik.project3.serverapi.util;

import com.alik.project3.serverapi.dto.SensorDTO;
import com.alik.project3.serverapi.services.SensorsService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class SensorValidator implements Validator {
    private final SensorsService sensorsService;

    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensor = (SensorDTO) target;
        if(Objects.nonNull(sensorsService.findByName(sensor.getName()))){
            errors.rejectValue("name","There is already sensor with such name");
        }

    }
}
