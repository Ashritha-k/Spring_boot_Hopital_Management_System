package jsp.springboot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.springboot.entity.Doctor;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.repository.DoctorRepository;

@Repository
public class DoctorDao {
	@Autowired
	private DoctorRepository doctorRepository;
	
	
	public Doctor  addDoctor(Doctor doctor)
	{
		return doctorRepository.save(doctor);
	}
	
	
	public void deleteDoctor(Integer id)
	{
		Optional<Doctor> opt = doctorRepository.findById(id);
		if(opt.isPresent())
			doctorRepository.deleteById(id);
		else throw new IdNotFoundException("no id present");
	}

}
