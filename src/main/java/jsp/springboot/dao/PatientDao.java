package jsp.springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.MedicalRecord;
import jsp.springboot.entity.Patient;
import jsp.springboot.exception.EmailAlreadyExistsException;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.exception.PhoneNumberAlreadyExistsException;
import jsp.springboot.repository.AppointmentRepository;
import jsp.springboot.repository.MedicalRecordRepository;
import jsp.springboot.repository.PatientRepository;

@Repository
public class PatientDao {
	
	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private PatientRepository patientRepository;
	
	//1
	public Patient registerPatient(Patient patient)
	{


	    if (patientRepository.existsByEmail(patient.getEmail())) {
	        throw new EmailAlreadyExistsException("Email already exists");
	    }

	    if (patientRepository.existsByPhoneNumber(patient.getPhoneNumber())) {
	        throw new PhoneNumberAlreadyExistsException("Phone number already exists");
	    }
		return  patientRepository.save(patient);
		
	}
		
	//2
	public List<Patient> fetchAllPatient()
	
	{
		List<Patient> patients = patientRepository.findAll();
		if(patients.isEmpty())
		{
			throw new NoRecordAvailableException("No Patient Record present in Database");
		}
		
		return patients;
	}
	//3
	public Patient fetchPatientById(Integer id)
	{
		Optional<Patient> opt =patientRepository.findById(id);
		if(opt.isEmpty())
		{
			throw new IdNotFoundException("Id not present in the database");
			
		}
		else return opt.get();
		
	}
	
	//4 
	public Patient fetchPatientByPhoneNumber(String phoneNumber)
	{
		Optional<Patient> opt = patientRepository.findByPhoneNumber(phoneNumber);
		if(opt.isPresent())
		{
			return opt.get();
		}
		else 
			throw new NoRecordAvailableException("Patient with given phone number not found ");
	}
	public List<Patient> fetchPatientWhereAgeGreaterThan(int age) {
	    return patientRepository.findByAgeGreaterThan(age);
	}
	public Patient fetchPatientByAppointment(Integer id)
	{
		Optional<Appointment> opt = appointmentRepository.findById(id);
		if(opt.isPresent())
		{
			return opt.get().getPatient();
		}
		else 
			throw new IdNotFoundException("Appointment Id not found");
	
	}
	public Patient fetchPatientByMedicalRecord(Integer id)
	{
		Optional<MedicalRecord> opt = medicalRecordRepository.findById(id);
		if(opt.isPresent())
		{
			return opt.get().getPatient();
		}
		else 
			throw new IdNotFoundException("Medical record Id not found");
	
	}
	public Patient updatePatient(Patient patient) {

	    if (patient.getPatientId() == null) {
	        throw new NoRecordAvailableException("Id must be provided");
	    }

	    Optional<Patient> opt = 
	            patientRepository.findById(patient.getPatientId());

	    if (opt.isPresent()) {
	        return patientRepository.save(patient);
	    } 
	    else {
	        throw new IdNotFoundException("Id not present in the database");
	    }
	}
	public void deletePatient(Integer id)
	{
		Optional<Patient> opt =patientRepository.findById(id);
		if(opt.isPresent())
		
		 patientRepository.deleteById(id);
		
			else
			throw new IdNotFoundException("Id not present in the database");
			
		
		
	
	}


	
	
	

}
