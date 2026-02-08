package jsp.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.springboot.entity.MedicalRecord;

public interface MedicalRecordRepository  extends JpaRepository<MedicalRecord, Integer>{

}
