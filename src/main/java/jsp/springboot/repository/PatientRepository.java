package jsp.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.springboot.entity.Patient;

public interface PatientRepository  extends JpaRepository<Patient, Integer>{

}
