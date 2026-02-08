package jsp.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.springboot.entity.Appointment;

public interface AppointmentRepository  extends JpaRepository<Appointment, Integer>{

}
