package jsp.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Department;
import jsp.springboot.service.DepartmentService;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Department>> createDepartment(@RequestBody Department department)
	{
		return departmentService.createDepartment(department);
	}
	@PostMapping("/All")
	public ResponseEntity<ResponseStructure<List<Department>>> createMultipleDepartment(@RequestBody List<Department>department)
	{
		return departmentService.createMultipleDepartment(department);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Department>>> fetchAllDepartment(){
		return departmentService.fetchAllDepartment();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Department>> fetchDepartmentById(@PathVariable Integer id)
	{
	return departmentService.fetchDepartmentById(id);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Department>> updateDepartment(@RequestBody Department department)
	{
	return departmentService.updateDepartment(department);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteDepartment(@PathVariable Integer id)
	{
	return departmentService.deleteDepartment(id);
	}
	@GetMapping("/departmentName")
	public ResponseEntity<ResponseStructure<Department>> fetchDepByDepartmentName(@RequestParam String departmentName)
	{
	return departmentService.fetchDepByDepartmentName(departmentName);
	}



}
