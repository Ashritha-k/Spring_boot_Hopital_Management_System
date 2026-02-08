package jsp.springboot.service;

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


@Service
public class DoctorService {
	
	@Autowired 
	private DoctorDao doctorDao;
	 @PersistenceContext
	    private EntityManager em;

	    public ResponseEntity<ResponseStructure<Doctor>> addDoctor(Doctor doctor) {
	        ResponseStructure<Doctor> response = new ResponseStructure<>();

	        // If department ID is provided
	        if (doctor.getDepartment() != null && doctor.getDepartment().getDepartmentId() != null) {
	            // Get a reference to the existing department
	            Department deptRef = em.getReference(Department.class, doctor.getDepartment().getDepartmentId());
	            doctor.setDepartment(deptRef);
	        }

	        Doctor savedDoctor = doctorDao.addDoctor(doctor);

	        response.setStatusCode(HttpStatus.CREATED.value());
	        response.setMessage("Doctor added successfully");
	        response.setData(savedDoctor);

	        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
	
	
	
	

}
