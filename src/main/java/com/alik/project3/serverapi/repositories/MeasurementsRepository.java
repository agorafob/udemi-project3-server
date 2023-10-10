package com.alik.project3.serverapi.repositories;

import com.alik.project3.serverapi.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement,Integer> {

    @Query("select m.received_at from Measurement m where m.sensor.id=:id and m.raining=true ")
    List<LocalDateTime> findRainingReportsBySensorId(int id);

    @Query("select max (m.received_at) from Measurement m union select min(m.received_at) from Measurement m ")
    List<LocalDateTime> getPeriod();
}
