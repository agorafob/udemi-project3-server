package com.alik.project3.serverapi.services;

import com.alik.project3.serverapi.exceptions.sensors.NoSuchSensorException;
import com.alik.project3.serverapi.models.Sensor;
import com.alik.project3.serverapi.repositories.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public Sensor findByName(String name){
        return sensorsRepository.findByName(name).orElseThrow(NoSuchSensorException::new);
    }

    @Transactional
    public void save(Sensor sensor){
        sensorsRepository.save(sensor);
    }
}
