package com.alik.project3.serverapi.controlers;

import com.alik.project3.serverapi.dto.MeasurementDTO;
import com.alik.project3.serverapi.exceptions.measurements.MeasurementErrorResponse;
import com.alik.project3.serverapi.exceptions.measurements.MeasurementNotCreatedException;
import com.alik.project3.serverapi.exceptions.sensors.NoSuchSensorException;
import com.alik.project3.serverapi.exceptions.sensors.SensorErrorResponse;
import com.alik.project3.serverapi.models.Measurement;
import com.alik.project3.serverapi.services.MeasurementsService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final ModelMapper mapper;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, ModelMapper mapper) {
        this.measurementsService = measurementsService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<MeasurementDTO> printMeasurements() {
        return measurementsService.findAll().stream().map(m -> mapper.map(m, MeasurementDTO.class)).collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError f : fieldErrors) {
                errorMsg.append(f.getField()).append("-").
                        append(f.getDefaultMessage()).append(";");
            }

            throw new MeasurementNotCreatedException(errorMsg.toString());
        }
        measurementsService.save(convertor(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public String rainyDaysCount(@RequestParam("name") String name) {
        Integer rainyDaysCount = measurementsService.getRainyDays(name);
        return "Sensor name: " + name + "\n" +
                "Period: " + measurementsService.getPeriod() + "\n" +
                "Number of rainy days: " + rainyDaysCount;
    }


    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleMeasurementException(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleSensorException(NoSuchSensorException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                "There is no such sensor", LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    private Measurement convertor(MeasurementDTO measurementDTO) {
        Measurement measurement = mapper.map(measurementDTO, Measurement.class);
        measurement.setRaining(measurementDTO.transferRaining());
        return measurement;
    }


}
