package jsp.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.springboot.entity.Department;

public interface DepartmentRepository  extends JpaRepository<Department, Integer>{
	
	Optional<Department> findByDepartmentName(String departmentName);

}
