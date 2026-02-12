package jsp.springboot.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.springboot.dao.MedicalRecordDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.MedicalRecord;

@Service
public class MedicalRecordService {
	
	@Autowired
	private MedicalRecordDao medicalRecordDao;
	
	public ResponseEntity<ResponseStructure<MedicalRecord>> createRecord(MedicalRecord medicalRecord)
	{
		ResponseStructure<MedicalRecord> response = new  ResponseStructure<MedicalRecord>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Department created");
        response.setData(medicalRecordDao.createRecord(medicalRecord));
        return new  ResponseEntity<ResponseStructure<MedicalRecord>>(response,HttpStatus.CREATED);
        
		
	}
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchAllRecord()
	{
		ResponseStructure<List<MedicalRecord> >response = new  ResponseStructure<List<MedicalRecord>>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Records Fetched");
        response.setData(medicalRecordDao.fetchAllRecords());
        return new  ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response,HttpStatus.OK);
        
		
	}
	public ResponseEntity<ResponseStructure<MedicalRecord>> fetchRecordById(Integer id)
	{
		
		ResponseStructure<MedicalRecord> response = new  ResponseStructure<MedicalRecord>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(" Record Fetched");
        response.setData(medicalRecordDao.fetchRecordById(id));
        return new  ResponseEntity<ResponseStructure<MedicalRecord>>(response,HttpStatus.CREATED);
        
		
	}
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByPatient( Integer id) 
	{
		ResponseStructure<List<MedicalRecord> >response = new  ResponseStructure<List<MedicalRecord>>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Records Fetched");
        response.setData(medicalRecordDao.fetchRecordByPatient(id));
        return new  ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response,HttpStatus.OK);
        
		
	}
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByAppointment( Integer id) 
	{
		ResponseStructure<List<MedicalRecord> >response = new  ResponseStructure<List<MedicalRecord>>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Records Fetched");
        response.setData(medicalRecordDao.fetchRecordByAppointment(id));
        return new  ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response,HttpStatus.OK);
        
		
	}
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByDoctor( Integer id) 
	{
		ResponseStructure<List<MedicalRecord> >response = new  ResponseStructure<List<MedicalRecord>>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Records Fetched");
        response.setData(medicalRecordDao.fetchRecordByDoctor(id));
        return new  ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response,HttpStatus.OK);
        
		
	}
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByVisitDate(
	       LocalDate visitDate) {

	  

	    List<MedicalRecord> records = medicalRecordDao.fetchRecordByVisitDate(visitDate);

	    ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<>();
	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Medical records fetched successfully for date: " + visitDate);
	    response.setData(records);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	

}
