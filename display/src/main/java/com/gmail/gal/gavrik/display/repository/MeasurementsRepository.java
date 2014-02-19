package com.gmail.gal.gavrik.display.repository;

//import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

import com.gmail.gal.gavrik.display.domain.Measurements;

public interface MeasurementsRepository extends CrudRepository<Measurements, Long> {

//	@Query("select distinct m from Measurements m left join fetch m.users u join fetch m.equipment e join fetch m.spectrums s join fetch e.model mo join fetch s.harmonics h join fetch s.date d")
//	@Query("select distinct m from Measurements m")
//	public List<Measurements> findAllWithDetail();

}
