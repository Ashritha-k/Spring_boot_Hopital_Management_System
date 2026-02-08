package jsp.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.springboot.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
