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
import jsp.springboot.entity.Patient;
import jsp.springboot.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	//1
	@PostMapping
	public ResponseEntity<ResponseStructure<Patient>> registerPatient(@RequestBody Patient patient)
	
	{
		return patientService.registerPatient(patient);
		
	}
	//2
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Patient>>> fetchAllPatient()
	{
		return patientService.fetchAllPatient();
	}
	//3
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientById( @PathVariable Integer id)
	{
		return patientService.fetchPatientById(id);
	}
	//4
	@GetMapping("/phone")
	public ResponseEntity<ResponseStructure<Patient>> 
	fetchPatientByPhoneNumber(@RequestParam String phoneNumber)
	{
	    return patientService.fetchPatientByPhoneNumber(phoneNumber);
	}
	@GetMapping("/age")
	public ResponseEntity<ResponseStructure<List<Patient>>> 
	fetchPatientWhereAgeGreaterThan(@RequestParam int value) {

	    return patientService.fetchPatientWhereAgeGreaterThan(value);
	}
	
	@GetMapping("/appointment/{id}")
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientByAppointment(
	        @PathVariable int id) {

	    return patientService.fetchPatientByAppointment(id);
	}
	@GetMapping("/medicalRecord/{id}")
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientByMedicalRecord(
	        @PathVariable int id) {

	    return patientService.fetchPatientByMedicalRecord(id);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Patient>> 
	updatePatient(@RequestBody Patient patient) {
return patientService.updatePatient(patient);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>>
	deletePatient(@PathVariable Integer id)
	{
		return patientService.deletePatient(id);
	}





}
