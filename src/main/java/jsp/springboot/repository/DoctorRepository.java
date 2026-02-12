package jsp.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jsp.springboot.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	
	List<Doctor> findBySpecialization(String specialization);
	@Query("""
	        SELECT DISTINCT d 
	        FROM Doctor d 
	        JOIN d.availableDays ad 
	        WHERE ad IN :days
	    """)
	    List<Doctor> findByAvailableDays(@Param("days") List<String> days);
	
	   @Query("""
		        SELECT d
		        FROM Doctor d
		        WHERE d.department.departmentId = :deptId
		    """)
		    List<Doctor> findDoctorsByDepartmentId(@Param("deptId") Integer deptId);
	

	   @Query("""
			    SELECT DISTINCT a.doctor
			    FROM Appointment a
			    WHERE a.patient.patientId = :patientId
			""")
			List<Doctor> findDoctorsByPatientId(@Param("patientId") Integer patientId);

	   
	   @Query("""
			    SELECT a.doctor
			    FROM Appointment a
			    WHERE a.appointmentId = :appointmentId
			""")
		Optional<Doctor> findDoctorByAppointmentId(@Param("appointmentId") Integer appointmentId);

}
