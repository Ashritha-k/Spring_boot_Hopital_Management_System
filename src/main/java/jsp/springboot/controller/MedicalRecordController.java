package jsp.springboot.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.MedicalRecord;
import jsp.springboot.service.MedicalRecordService;

@RestController
@RequestMapping("/api/medicalRecord")
public class MedicalRecordController {
	
@Autowired
private MedicalRecordService medicalRecordService;

@PostMapping

public ResponseEntity<ResponseStructure<MedicalRecord>> createRecord( @RequestBody MedicalRecord medicalRecord)
{
	return medicalRecordService.createRecord(medicalRecord);
}
@GetMapping
public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchAllRecord()
{
	return medicalRecordService.fetchAllRecord();
}
@GetMapping("/{id}")
public ResponseEntity<ResponseStructure<MedicalRecord>> fetchRecordById( @PathVariable Integer id)
{
	return medicalRecordService.fetchRecordById(id);
}
@GetMapping("/patient/{id}")
public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByPatient(
        @PathVariable Integer id) {
    return medicalRecordService.fetchRecordByPatient(id);
}
@GetMapping("/appointment/{id}")
public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByAppointment(
        @PathVariable Integer id) {
    return medicalRecordService.fetchRecordByAppointment(id);
}
@GetMapping("/doctor/{id}")
public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByDoctor(
        @PathVariable Integer id) {
    return medicalRecordService.fetchRecordByDoctor(id);
}
@GetMapping("/date/{visitDate}")
public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByVisitDate(
        @PathVariable String visitDate) {

    
    LocalDate date = LocalDate.parse(visitDate);
return medicalRecordService.fetchRecordByVisitDate(date);

}
}
