package jsp.springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.springboot.entity.MedicalRecord;
import jsp.springboot.entity.Patient;
import jsp.springboot.entity.Prescription;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.repository.MedicalRecordRepository;
import jsp.springboot.repository.PatientRepository;
import jsp.springboot.repository.PrescriptionRepository;

@Repository
public class PrescriptionDao {
	
	@Autowired
	private PrescriptionRepository prescriptionRepository;
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	@Autowired
	private PatientRepository patientRepository;
	
	public Prescription createPrescription(Prescription prescription) {
		Optional<MedicalRecord >recordOpt= medicalRecordRepository.findById(prescription.getMedicalRecord().getRecordId());
	    if (recordOpt.isEmpty()) {
	        throw new NoRecordAvailableException(" Medical record does not exist");
	    }
	    
	    MedicalRecord record = recordOpt.get();
	    prescription.setMedicalRecord(record);
	    record.setPrescription(prescription); 
	    
	    return prescriptionRepository.save(prescription);
	}
	
	 public List<Prescription> fetchAllPrescription()
	{
	
		 List<Prescription> prescriptions = prescriptionRepository.findAll();
		 if(prescriptions.isEmpty())
		 {
			 throw new NoRecordAvailableException("No record available");
			 
		 }
		 else return prescriptions;
	} public Prescription fetchPrescriptionById(Integer id )
	{
		Optional<Prescription> opt = prescriptionRepository.findById(id);
		if(opt.isPresent())
			return opt.get();
		else throw new IdNotFoundException("id not found ");
		
	}
	 public List<Prescription> fetchPrescriptionByMedicalRecord(Integer id)
		{
		  Optional<MedicalRecord> opt = medicalRecordRepository.findById(id);
		  if(opt.isEmpty())
		   throw new NoRecordAvailableException(" Medical record does not exist");
		
		
		  return  prescriptionRepository.findByMedicalRecord(id);
		}
	 public List<Prescription> fetchPrescriptionByPatient(Integer id)
		{
		 Optional<Patient> opt = patientRepository.findById(id);
		  if(opt.isEmpty())
		   throw new NoRecordAvailableException(" Patient does not exist");
			  
		
		  return  prescriptionRepository.findByPatientId(id);
		}
		 
		public void deletePrescription (Integer id)
		{
			Optional<Prescription> opt = prescriptionRepository.findById(id);
			if(opt.isPresent())
			prescriptionRepository.deleteById(id);
			
			
			
		}
	 

}
