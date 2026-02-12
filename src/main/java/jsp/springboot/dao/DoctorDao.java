package jsp.springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.springboot.entity.Department;
import jsp.springboot.entity.Doctor;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.repository.DoctorRepository;
import jsp.springboot.service.DepartmentService;

@Repository
public class DoctorDao {
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	//1
	public Doctor addDoctor(Doctor doctor)
	{
	    if (doctor.getDepartment() == null || doctor.getDepartment().getDepartmentId() == null) {
	        throw new RuntimeException("DepartmentId must be provided inside department object");
	    }

	    Department dep = departmentDao.fetchDepartmentById(
	            doctor.getDepartment().getDepartmentId()
	    );

	    doctor.setDepartment(dep); 

	    return doctorRepository.save(doctor);
	}
	//2
	public List<Doctor> fetchAllDoctor()
	{
		List<Doctor> doctors = doctorRepository.findAll();
		if(doctors.isEmpty())
		{
			throw new NoRecordAvailableException("No Doctors are available in the database");
			
		}
		else 
			return doctors;
		
	}
//3
	public Doctor fetchDoctorById(Integer id)
	{
	Optional<Doctor> opt= doctorRepository.findById(id);
	
		if(opt.isPresent())
		{
			return opt.get();
		}
		else 	throw new IdNotFoundException("Id Not Present in Db ");
		
		
	}
	//4
	public List<Doctor> fetchDoctorBySpecialization(String specialization)
	{
	List<Doctor> doctors = doctorRepository.findBySpecialization(specialization);
	
		if(doctors.isEmpty())
		{
			throw new NoRecordAvailableException("No record available ");
		}
		else return  doctors;
		
	}
	
	
	//5
	public List<Doctor> fetchDoctorInADepartment(Integer id)
	{
		List<Doctor> doctors = doctorRepository.findDoctorsByDepartmentId(id);
		if(doctors.isEmpty())
		{
			throw new NoRecordAvailableException(  "No doctors found for department id " + id
				       );
		}
			else 
				return doctors;
		
	}
	//6
		public List<Doctor> fetchDoctorByPatient(Integer patientId)
		{
			List<Doctor> doctors = doctorRepository.findDoctorsByPatientId(patientId);
			if(doctors.isEmpty())
			{
				throw new NoRecordAvailableException(  "No doctors found for Patient id " + patientId
					       );
			}
				else 
					return doctors;
			
		}
		//7
		public Doctor fetchDoctorByAppointment(Integer appId){
	Optional<Doctor> doctor = doctorRepository.findDoctorByAppointmentId(appId);
			if(doctor.isPresent())
			{
				return doctor.get();
			}
			else  throw new NoRecordAvailableException(  "No doctors found for Appointment id " + appId
				       );
		
		}
		//8
		public Doctor updateDoctor(Doctor doctor)
		{
		    if (doctor.getDoctorId() == null) {
		        throw new NoRecordAvailableException("Doctor Id must be provided");
		    }

		    Optional<Doctor> opt = doctorRepository.findById(doctor.getDoctorId());

		    if (opt.isPresent()) {

		       
		        if (doctor.getDepartment() != null &&
		            doctor.getDepartment().getDepartmentId() != null) {

		            Department dep = departmentDao
		                    .fetchDepartmentById(
		                        doctor.getDepartment().getDepartmentId()
		                    );

		            doctor.setDepartment(dep);
		        }

		        return doctorRepository.save(doctor);  
		    } 
		    else {
		        throw new IdNotFoundException("Doctor Id not present in DB");
		    }
		}
	//9
	public List<Doctor> fetchDoctorByAvailableDays(List<String> days)
	{
		List<Doctor> doctors = doctorRepository.findByAvailableDays(days);
		if(doctors.isEmpty())
		{
			throw new NoRecordAvailableException("No record Available");
			
		}
		else
			{
			
			return doctors;
			}
	}
	//10
	
	public void deleteDoctor(Integer id)
	{
		Optional<Doctor> opt = doctorRepository.findById(id);
		if(opt.isPresent())
			doctorRepository.deleteById(id);
		else throw new IdNotFoundException("no id present");
	}

}
