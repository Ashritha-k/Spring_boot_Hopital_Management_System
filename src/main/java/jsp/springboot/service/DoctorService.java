package jsp.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jsp.springboot.dao.DoctorDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Department;
import jsp.springboot.entity.Doctor;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;


@Service
public class DoctorService {
	
	@Autowired 
	private DoctorDao doctorDao;
	

	    public ResponseEntity<ResponseStructure<Doctor>> addDoctor(Doctor doctor) {
	        ResponseStructure<Doctor> response = new ResponseStructure<>();
	        Doctor savedDoctor = doctorDao.addDoctor(doctor);

	       response.setStatusCode(HttpStatus.OK.value());
	       response.setMessage("doctor record added");
	       response.setData(savedDoctor);
	       return new ResponseEntity<ResponseStructure<Doctor>>(response,HttpStatus.OK);
	    }
	    
	    
	    public ResponseEntity<ResponseStructure<List<Doctor>>> fetchAllDoctor()
	    {
	    	ResponseStructure<List<Doctor>> response = new ResponseStructure<List<Doctor>>();
	    	 response.setStatusCode(HttpStatus.OK.value());
		       response.setMessage(" all doctor record fetched");
		       response.setData(doctorDao.fetchAllDoctor());
		      return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
	    }
	    
	    
	    public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorBySpecialization(String specialization)
	    {
	    	List<Doctor> doctors = doctorDao.fetchDoctorBySpecialization(specialization);
	    	if(doctors== null)
	    	{
	    		throw new NoRecordAvailableException("no record available");
	    	}
	    	ResponseStructure<List<Doctor>> response = new ResponseStructure<List<Doctor>>();
	    	 response.setStatusCode(HttpStatus.OK.value());
		       response.setMessage(" all doctor record fetched with specialization "+ specialization);
		       response.setData(doctors);
		      return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
	    
	    }
	
	public ResponseEntity<ResponseStructure<String>> deleteDoctor(Integer id)
	{
		ResponseStructure<String> response =  new ResponseStructure<String>();
		doctorDao.deleteDoctor(id);
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Succefull");
		response.setData("deleted");
		return new  ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Doctor>> fetchDoctorById(Integer id)
	{
		Doctor doctor= doctorDao.fetchDoctorById(id);
		if(doctor==null)
		{
			throw new NoRecordAvailableException("no record available");
		}
		
		ResponseStructure<Doctor> response = new ResponseStructure<Doctor>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctor with id"+id+ "fetched");
		response.setData(doctor);
		return new ResponseEntity<ResponseStructure<Doctor>>(response,HttpStatus.OK);
	}
	
	
	
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorInADepartment(Integer id)
    {
    	List<Doctor> doctors = doctorDao.fetchDoctorInADepartment(id);
    	if(doctors== null)
    	{
    		throw new NoRecordAvailableException("no record available");
    	}
    	ResponseStructure<List<Doctor>> response = new ResponseStructure<List<Doctor>>();
    	 response.setStatusCode(HttpStatus.OK.value());
	       response.setMessage(" all doctor record fetched in departmentid "+ id);
	       response.setData(doctorDao.fetchDoctorInADepartment(id));
	      return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
    }
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorByPatientId(Integer patientId)
    {
    	List<Doctor> doctors = doctorDao.fetchDoctorByPatient(patientId);
    	if(doctors== null)
    	{
    		throw new NoRecordAvailableException("no record available");
    	}
    	ResponseStructure<List<Doctor>> response = new ResponseStructure<List<Doctor>>();
    	 response.setStatusCode(HttpStatus.OK.value());
	       response.setMessage(" all doctor record fetched in patientId "+ patientId);
	       response.setData(doctorDao.fetchDoctorByPatient(patientId));
	      return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
    }
	public ResponseEntity<ResponseStructure<Doctor>> fetchDoctorByAppointmentId(Integer appId)
    {
    	Doctor doctors = doctorDao.fetchDoctorByAppointment(appId);
    	if(doctors== null)
    	{
    		throw new NoRecordAvailableException("no record available");
    	}
    	ResponseStructure<Doctor> response = new ResponseStructure<Doctor>();
    	 response.setStatusCode(HttpStatus.OK.value());
	       response.setMessage(" all doctor record fetched in AppointmentId "+ appId);
	       response.setData(doctorDao.fetchDoctorByAppointment(appId));
	      return new ResponseEntity<ResponseStructure<Doctor>>(response, HttpStatus.OK);
    }
	
	
	public ResponseEntity<ResponseStructure<Doctor>> updateDoctor(Doctor doctor)
	{
		Doctor updated = doctorDao.updateDoctor(doctor);
		if (updated == null) {
            throw new IdNotFoundException("Id is not present in the DB");
        }
        ResponseStructure<Doctor> response = new ResponseStructure<Doctor>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Doctor  updated");
        response.setData(updated);
        return new ResponseEntity<ResponseStructure<Doctor>>(response, HttpStatus.OK);
        
    
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorByAvailableDays(List<String> days)
    {
    	List<Doctor> doctors = doctorDao.fetchDoctorByAvailableDays(days);
    	if(doctors== null)
    	{
    		throw new NoRecordAvailableException("no record available");
    	}
    	ResponseStructure<List<Doctor>> response = new ResponseStructure<List<Doctor>>();
    	 response.setStatusCode(HttpStatus.OK.value());
	       response.setMessage(" all doctor record fetched with availableDays "+ days);
	       response.setData(doctorDao.fetchDoctorByAvailableDays(days));
	      return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
    
    }
		
	
	
		
	
	
	
	
	
	
	

}
