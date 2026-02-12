package jsp.springboot.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jsp.springboot.entity.MedicalRecord;

public interface MedicalRecordRepository  extends JpaRepository<MedicalRecord, Integer>{

	  @Query("SELECT m FROM MedicalRecord m WHERE m.patient.patientId = :patientId")
	    List<MedicalRecord> findByPatientId(@Param("patientId") Integer patientId);
	
	  @Query("SELECT m FROM MedicalRecord m WHERE m.doctor.doctorId = :doctorId")
	  List<MedicalRecord> findByDoctorId(@Param("doctorId") Integer doctorId);
	  @Query("SELECT m FROM MedicalRecord m WHERE m.visitDate = :visitDate")
	  List<MedicalRecord> findByVisitDate(@Param("visitDate") LocalDate date);
	  
	  
	
}
