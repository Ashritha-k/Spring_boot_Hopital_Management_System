
package jsp.springboot.service;

import java.lang.classfile.instruction.NewMultiArrayInstruction;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.springboot.dao.PatientDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Patient;
import jsp.springboot.exception.NoRecordAvailableException;

@Service
public class PatientService {
	
	@Autowired
	private PatientDao patientDao;

	public ResponseEntity<ResponseStructure<Patient>> registerPatient (Patient patient)
	{
		Patient patients = patientDao.registerPatient(patient);
		ResponseStructure< Patient> response = new ResponseStructure<Patient>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Patient registerd");
		response.setData(patients);
		return new ResponseEntity<ResponseStructure<Patient>>(response,HttpStatus.CREATED);	
	}
	
	public ResponseEntity<ResponseStructure<List<Patient>>> fetchAllPatient()
	{
		ResponseStructure< List<Patient>> response = new ResponseStructure<List<Patient>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All patients fetched successfully");
		response.setData(patientDao.fetchAllPatient());
		return new ResponseEntity<ResponseStructure<List<Patient>>>(response,HttpStatus.OK);
		
		

	}
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientById (Integer id)
	{
		Patient patient = patientDao.fetchPatientById(id);
		if(patient==null)
		{
			throw new NoRecordAvailableException("No record Available");
		}
		ResponseStructure< Patient> response = new ResponseStructure<Patient>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Patient fetched successfully");
		response.setData(patient);
		return new ResponseEntity<ResponseStructure<Patient>>(response,HttpStatus.OK);	
	}
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientByPhoneNumber(String phoneNumber)
	{
		Patient patient = patientDao.fetchPatientByPhoneNumber(phoneNumber);
		if(patient==null)
		{
			throw new NoRecordAvailableException("No record Available");
		}
		ResponseStructure< Patient> response = new ResponseStructure<Patient>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Patient fetched successfully");
		response.setData(patient);
		return new ResponseEntity<ResponseStructure<Patient>>(response,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Patient>>> 
	fetchPatientWhereAgeGreaterThan(int age) {

	    List<Patient> patients = patientDao.fetchPatientWhereAgeGreaterThan(age);

	    if(patients.isEmpty()) {
	        throw new NoRecordAvailableException("No patients found with age greater than " + age);
	    }

	    ResponseStructure<List<Patient>> response = new ResponseStructure<>();
	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Patients fetched successfully");
	    response.setData(patients);

	    return new ResponseEntity<ResponseStructure<List<Patient>>>(response, HttpStatus.OK);
	}
	
//	public ResponseEntity<ResponseStructure<Patient>> fetchPatientByAppointment( int appointmentId) {
//
//	    Patient patient = patientDao.fetchPatientByAppointment(appointmentId);
//
//	    ResponseStructure<Patient> response = new ResponseStructure<>();
//	    response.setStatusCode(HttpStatus.OK.value());
//	    response.setMessage("Patient fetched successfully by Appointment Id");
//	    response.setData(patient);
//
//	    return new ResponseEntity<ResponseStructure<Patient>>(response, HttpStatus.OK);
//	}

	
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientByAppointment( int id) {

	    Patient patient = patientDao.fetchPatientByAppointment(id);

	    ResponseStructure<Patient> response = new ResponseStructure<>();
	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Patient fetched successfully");
	    response.setData(patient);

	    return new ResponseEntity<ResponseStructure<Patient>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientByMedicalRecord( int id) {

	    Patient patient = patientDao.fetchPatientByMedicalRecord(id);

	    ResponseStructure<Patient> response = new ResponseStructure<>();
	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Patient fetched successfully");
	    response.setData(patient);

	    return new ResponseEntity<ResponseStructure<Patient>>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Patient>> 
	updatePatient( Patient patient) {

	    Patient updated = 
	            patientDao.updatePatient(patient);

	    ResponseStructure<Patient> response = 
	            new ResponseStructure<>();

	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Patient updated");
	    response.setData(updated);

	    return new ResponseEntity<ResponseStructure<Patient>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<String>> deletePatient(Integer id)
	{
		ResponseStructure<String> response =  new ResponseStructure<String>();
		patientDao.deletePatient(id);
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Succefull");
		response.setData("deleted");
		return new  ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
	}

	

}
