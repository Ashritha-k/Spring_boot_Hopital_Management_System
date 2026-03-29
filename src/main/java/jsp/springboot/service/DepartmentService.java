package jsp.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.springboot.dao.DepartmentDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Department;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDao;
	
	public ResponseEntity<ResponseStructure<Department>> createDepartment(Department department)
	{
		ResponseStructure<Department> response = new  ResponseStructure<Department>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Department created");
        response.setData(departmentDao.createDepartment(department));
        return new  ResponseEntity<ResponseStructure<Department>>(response,HttpStatus.CREATED);
        
		
	}
	public ResponseEntity<ResponseStructure<List<Department>>> createMultipleDepartment(List<Department >department)
	{
		ResponseStructure<List<Department>> response = new  ResponseStructure<List<Department>>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Department created");
        response.setData(departmentDao.createMultipleDepartment(department));
        return new  ResponseEntity<ResponseStructure<List<Department>>>(response,HttpStatus.CREATED);
        
		
	}
	public ResponseEntity<ResponseStructure<List<Department>>> fetchAllDepartment()
	
	{
		ResponseStructure<List<Department> >response = new  ResponseStructure<List<Department>>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Departments fetched successfully");
        response.setData(departmentDao.fetchAllDepartment());
        return new  ResponseEntity<ResponseStructure<List<Department>>>(response,HttpStatus.OK);
        
		
	}
	public ResponseEntity<ResponseStructure<Department>> fetchDepartmentById(Integer id)
	{
		Department department = departmentDao.fetchDepartmentById(id);
		if(department== null)
		{
			throw new IdNotFoundException("Id not present in database");
		}
		ResponseStructure<Department> response = new  ResponseStructure<Department>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Department found");
        response.setData(department);
        return new  ResponseEntity<ResponseStructure<Department>>(response,HttpStatus.OK);
        
		
	}
	public ResponseEntity<ResponseStructure<Department>> updateDepartment(Department department)
	{
		Department updated = departmentDao.updateDepartment(department);
		if (updated == null) {
            throw new IdNotFoundException("Id is not present in the DB");
        }
        ResponseStructure<Department> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Department  updated");
        response.setData(updated);
        return new ResponseEntity<>(response, HttpStatus.OK);
        
    
	}
	public ResponseEntity<ResponseStructure<String>> deleteDepartment(Integer id){
		ResponseStructure<String> response =  new ResponseStructure<String>();
		departmentDao.deletedDepartment(id);
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Succefull");
		response.setData("deleted");
		return new  ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
	
	}
	public ResponseEntity<ResponseStructure<Department>> fetchDepByDepartmentName(String departmentName)
	{
		Department department = departmentDao.fetchDepByDepartmentName(departmentName);
		if(department== null)
		{
			throw new NoRecordAvailableException(" record not present in database");
		}
		ResponseStructure<Department> response = new  ResponseStructure<Department>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Department found");
        response.setData(department);
        return new  ResponseEntity<ResponseStructure<Department>>(response,HttpStatus.OK);
        
		
	}
	
	
	

}
