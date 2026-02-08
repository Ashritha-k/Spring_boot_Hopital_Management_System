package jsp.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Doctor;
import jsp.springboot.service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Doctor>> addDoctor(@RequestBody Doctor doctor)
	{
		return doctorService.addDoctor(doctor);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>>
 deleteDoctor( @PathVariable Integer  id){
		return doctorService.deleteDoctor(id);
	}
	

}
