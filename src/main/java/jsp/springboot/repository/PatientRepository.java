package jsp.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.springboot.entity.Patient;

public interface PatientRepository  extends JpaRepository<Patient, Integer>{

	
	 boolean existsByEmail(String email);

	    boolean existsByPhoneNumber(String phoneNumber);
	    
	    Optional<Patient> findByPhoneNumber(String PhoneNumber);
	    List<Patient> findByAgeGreaterThan(int age);
	   
 
}
