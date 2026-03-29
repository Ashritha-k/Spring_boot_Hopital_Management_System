package jsp.springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.springboot.entity.Department;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.repository.DepartmentRepository;

@Repository
public class DepartmentDao {
	@Autowired
	private DepartmentRepository departmentRepository;
	
	
	public Department createDepartment(Department department)
	{
		return departmentRepository.save(department);
	}
	public List<Department >createMultipleDepartment(List<Department> department)
	{
		return departmentRepository.saveAll(department);
	}
	public List<Department> fetchAllDepartment(){
	List<Department> departments = departmentRepository.findAll();
	if(departments.isEmpty())
	{
		throw new NoRecordAvailableException("Department Not Present in DataBase");
	}
	return departments;
		
		
	}
	public Department fetchDepartmentById(Integer id)
	{
		Optional<Department> opt = departmentRepository.findById(id);
		
		if(opt.isPresent())
		{
			return opt.get();
		}
		else throw new IdNotFoundException("Id not present  in the database");
	}
	public Department updateDepartment(Department department)
	{
		if(department.getDepartmentId()== null)
		{
			throw new NoRecordAvailableException("Id must be provided ");
		}
			Optional<Department> opt = departmentRepository.findById(department.getDepartmentId());
		
		if(opt.isPresent())
		{
			return departmentRepository.save(department);
		}
		else throw new IdNotFoundException("Id not present  in the database");
	}
	public void deletedDepartment(Integer id) {
		Optional<Department> opt = departmentRepository.findById(id);
		if(opt.isEmpty())
		{
			throw new IdNotFoundException("Id no present in database");
			
		}
		else
		 departmentRepository.deleteById(id);
		 
		
	}
	public Department fetchDepByDepartmentName(String departmentName)
	{
		Optional<Department> opt= departmentRepository.findByDepartmentName(departmentName);
		if(opt.isPresent())
		{
			return opt.get();
			
		}
		else
			throw new NoRecordAvailableException("No record available in Database");
	}

			
	
}


