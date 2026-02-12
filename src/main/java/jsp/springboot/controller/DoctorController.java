package jsp.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Department;
import jsp.springboot.entity.Doctor;
import jsp.springboot.service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Doctor>> addDoctor(@RequestBody Doctor doctor)
	{
		return doctorService.addDoctor(doctor);
		
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchAllDoctor()
	{
		return doctorService.fetchAllDoctor();
	}
	
	@GetMapping("/specialization")
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorBySpeciailization(@RequestParam String specialization)
	{
		return doctorService.fetchDoctorBySpecialization(specialization);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Doctor>> fetchDoctorById(@PathVariable Integer id)
	{
		return doctorService.fetchDoctorById(id);
	}
	
	@GetMapping("/availableDays")
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorAvailableDays(@RequestParam List<String> days)
	{
		return doctorService.fetchDoctorByAvailableDays(days);
	}
	@GetMapping("/department/{deptId}")
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorInADepartment(@PathVariable  Integer deptId )
	{
		return doctorService.fetchDoctorInADepartment(deptId);
	}
	
	@GetMapping("/patient/{patientId}")
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorByPatientId(@PathVariable  Integer patientId )
	{
		return doctorService.fetchDoctorByPatientId(patientId);
	}
	@GetMapping("/appointment/{appId}")
	public ResponseEntity<ResponseStructure<Doctor>> fetchDoctorByAppointmentId(@PathVariable  Integer appId )
	{
		return doctorService.fetchDoctorByAppointmentId(appId);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Doctor>> updateDoctor(@RequestBody Doctor doctor)
	{
	return doctorService.updateDoctor(doctor);
	}
	
	
	
	
	
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>>
 deleteDoctor( @PathVariable Integer  id){
		return doctorService.deleteDoctor(id);
	}
	

}
