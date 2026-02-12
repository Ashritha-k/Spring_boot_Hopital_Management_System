package jsp.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.springboot.dao.PrescriptionDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Prescription;

@Service
public class PrescriptionService {
	@Autowired
	private PrescriptionDao prescriptionDao;
	
	
	public ResponseEntity<ResponseStructure<Prescription>> createPrescription(Prescription prescription)
	{
		Prescription saved = prescriptionDao.createPrescription(prescription);

		ResponseStructure<Prescription> response = new ResponseStructure<Prescription>();
		 response.setStatusCode(HttpStatus.CREATED.value());
	        response.setMessage("Prescription created successfully");
	        response.setData(saved);

	        return  new ResponseEntity<ResponseStructure<Prescription>>(response,HttpStatus.CREATED);
	    		
	}

	public ResponseEntity<ResponseStructure<List<Prescription>> >fetchAllPrescription()
	{
		List<Prescription>  prescriptions= prescriptionDao.fetchAllPrescription();

		ResponseStructure<List<Prescription>> response = new ResponseStructure<List<Prescription>>();
		 response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Prescription created successfully");
	        response.setData(prescriptions);

	        return  new ResponseEntity<ResponseStructure<List<Prescription>>>(response,HttpStatus.OK);
	    		
	}
	public ResponseEntity<ResponseStructure<Prescription>> fetchPrescriptionById(Integer id )
	{
		Prescription saved = prescriptionDao.fetchPrescriptionById(id);
		

		ResponseStructure<Prescription> response = new ResponseStructure<Prescription>();
		 response.setStatusCode(HttpStatus.CREATED.value());
	        response.setMessage("Prescription fetched successfully");
	        response.setData(saved);

	        return  new ResponseEntity<ResponseStructure<Prescription>>(response,HttpStatus.CREATED);
	    		
	}
	public ResponseEntity<ResponseStructure<List<Prescription>> >fetchPrescriptionByMedicalRecord( Integer id)
	{
		List<Prescription>  prescriptions= prescriptionDao.fetchPrescriptionByMedicalRecord(id);

		ResponseStructure<List<Prescription>> response = new ResponseStructure<List<Prescription>>();
		 response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Prescription fetched successfully");
	        response.setData(prescriptions);

	        return  new ResponseEntity<ResponseStructure<List<Prescription>>>(response,HttpStatus.OK);
	    		
	}
	public ResponseEntity<ResponseStructure<List<Prescription>> >fetchPrescriptionByPatient( Integer id)
	{
		List<Prescription>  prescriptions= prescriptionDao.fetchPrescriptionByPatient(id);

		ResponseStructure<List<Prescription>> response = new ResponseStructure<List<Prescription>>();
		 response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Prescription fetched successfully");
	        response.setData(prescriptions);

	        return  new ResponseEntity<ResponseStructure<List<Prescription>>>(response,HttpStatus.OK);
	    		
	}
	public ResponseEntity<ResponseStructure<String>> deletePrescription( Integer id)
	{
		prescriptionDao.deletePrescription(id);

		ResponseStructure<String> response = new ResponseStructure<String>();
		 response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage(" successfully");
	        response.setData("deleted");

	        return  new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
	    		
	}
	
	
	
}
