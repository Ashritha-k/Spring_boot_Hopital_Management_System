package jsp.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Prescription;
import jsp.springboot.service.PrescriptionService;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {
	
	@Autowired
	private PrescriptionService prescriptionService;
	
	
@PostMapping
public ResponseEntity<ResponseStructure<Prescription>> createPrescription( @RequestBody Prescription prescription)
{
	return prescriptionService.createPrescription(prescription);
}
@GetMapping
public ResponseEntity<ResponseStructure<List<Prescription>> >fetchAllPrescription()
{
	return prescriptionService.fetchAllPrescription();
}
@GetMapping("/{id}")
public ResponseEntity<ResponseStructure<Prescription>> fetchPrescriptionById(@PathVariable Integer id )
{
return prescriptionService.fetchPrescriptionById(id);
}
@GetMapping("/medicalRecord/{id}")
public ResponseEntity<ResponseStructure<List<Prescription>> >fetchPrescriptionByMedicalRecord(@PathVariable Integer id)
{
	return prescriptionService.fetchPrescriptionByMedicalRecord(id);
}
@GetMapping("/patient/{id}")
public ResponseEntity<ResponseStructure<List<Prescription>> >fetchPrescriptionByPatient(@PathVariable Integer id)
{
	return prescriptionService.fetchPrescriptionByPatient(id);
}
@DeleteMapping("/{id}")
public ResponseEntity<ResponseStructure<String>> deletePrescription(@PathVariable Integer id )
{
return prescriptionService.deletePrescription(id);
}
}