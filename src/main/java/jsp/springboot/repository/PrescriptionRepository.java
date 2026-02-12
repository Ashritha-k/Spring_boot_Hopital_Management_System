package jsp.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jsp.springboot.entity.MedicalRecord;
import jsp.springboot.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer>{

	
	
	@Query("SELECT p From Prescription p WHERE p.medicalRecord = :medicalRecord")
	List<Prescription> findByMedicalRecord(@Param("id") Integer id);
	@Query("SELECT p From Prescription p WHERE p.medicalRecord.patient.patientId= :patientId")
	List<Prescription> findByPatientId(@Param("id") Integer id);
			
}
