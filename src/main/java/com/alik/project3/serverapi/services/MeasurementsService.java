package com.alik.project3.serverapi.services;

import com.alik.project3.serverapi.models.Measurement;
import com.alik.project3.serverapi.models.Sensor;
import com.alik.project3.serverapi.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
   private final MeasurementsRepository measurementsRepository;
   private final SensorsService sensorsService;

   @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
       this.sensorsService = sensorsService;
   }


    @Transactional
    public void save(Measurement measurement){
        measurement.setReceived_at(LocalDateTime.now());
        measurement.setSensor(sensorsService.findByName(measurement.getSensor().getName()));
        measurementsRepository.save(measurement);
    }

    public Integer getRainyDays(String name){
        Sensor sensor=sensorsService.findByName(name);
        List<LocalDateTime> mes =  measurementsRepository.findRainingReportsBySensorId(sensor.getId());
        Set<LocalDate> distinctDates = mes.stream().map(LocalDateTime::toLocalDate).collect(Collectors.toSet());
        return distinctDates.size();
    }

    public List<Measurement> findAll(){
       return measurementsRepository.findAll();
    }

    public String getPeriod(){
       List<LocalDateTime> period = measurementsRepository.getPeriod();
       return period.get(0).toString().replace("T"," ")+" - " + period.get(1).toString().replace("T"," ");
    }


}
