package jsp.springboot.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jsp.springboot.AppointmentStatus;
import jsp.springboot.entity.Appointment;

public interface AppointmentRepository  extends JpaRepository<Appointment, Integer>{


	boolean existsByDoctor_DoctorIdAndAppointmentDateTime(
	        Integer doctorId,
	        LocalDateTime dateTime
	);
	@Query("""
		       SELECT COUNT(a) > 0
		       FROM Appointment a
		       WHERE a.patient.patientId = :patientId
		       AND a.appointmentDateTime BETWEEN :start AND :end
		       """)
		boolean existsPatientAppointmentOnSameDay(
		        @Param("patientId") Integer patientId,
		        @Param("start") LocalDateTime start,
		        @Param("end") LocalDateTime end
		);
	

    @Query("SELECT a FROM Appointment a WHERE a.appointmentDateTime BETWEEN :start AND :end")
    List<Appointment> findAppointmentsByDateRange(@Param("start") LocalDateTime start,
                                                  @Param("end") LocalDateTime end);
    
    
    @Query("SELECT a FROM Appointment a WHERE a.patient.patientId = :patientId")
    List<Appointment> findAppointmentsByPatientId(@Param("patientId") Integer patientId);
    
    @Query("SELECT a FROM Appointment a WHERE a.status = :status")
    List<Appointment> findAppointmentsByStatus(@Param("status") AppointmentStatus status);
    
	List<Appointment> findByPatient_PatientIdAndStatus(Integer patientId, AppointmentStatus completed);


}
