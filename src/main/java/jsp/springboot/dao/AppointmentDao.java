package jsp.springboot.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.springboot.AppointmentStatus;
import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.Doctor;
import jsp.springboot.entity.Patient;
import jsp.springboot.exception.DoctorAlreadyBookedException;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.exception.PatientAlreadyBookedException;
import jsp.springboot.repository.AppointmentRepository;
import jsp.springboot.repository.DoctorRepository;
import jsp.springboot.repository.PatientRepository;

@Repository
public class AppointmentDao {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private PatientRepository patientRepository;
	
	
	public Appointment BookAppointment(Appointment appointment)
	{
	  Integer doctorId = appointment.getDoctor().getDoctorId();
	  Integer patientId = appointment.getPatient().getPatientId();
	  LocalDateTime dateTime = appointment.getAppointmentDateTime();
	 

	    
	    Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
	    if (!doctorOpt.isPresent())
	    {
	        throw new IdNotFoundException("Doctor not found in database");
	    }

	    
	    Optional<Patient> patientOpt = patientRepository.findById(patientId);
	    if (!patientOpt.isPresent())
	    {
	        throw new IdNotFoundException("Patient not found in database");
	    }

	  boolean busy = appointmentRepository.existsByDoctor_DoctorIdAndAppointmentDateTime(doctorId, dateTime);
	  if(busy)
	  {
		  throw new DoctorAlreadyBookedException("Doctor already has appointment at this time");
	  }
	
	    LocalDateTime startOfDay = dateTime.toLocalDate().atStartOfDay();
	    LocalDateTime endOfDay = dateTime.toLocalDate().atTime(23,59,59);

	    boolean patientBusy = appointmentRepository
	            .existsPatientAppointmentOnSameDay(
	                    patientId, startOfDay, endOfDay);

	    if (patientBusy) {
	        throw new PatientAlreadyBookedException("Patient already has appointment on this day");
	    }
	    appointment.setStatus(AppointmentStatus.BOOKED);
	    appointment.setDoctor(doctorOpt.get());
	    appointment.setPatient(patientOpt.get());
	    return appointmentRepository.save(appointment);
	}
	
	public List<Appointment> fetchAllAppointment()
	{
		List<Appointment> appointments =appointmentRepository.findAll();
		if(appointments.isEmpty())
		{
			throw new NoRecordAvailableException("No Reord Available");
			
		}
		
		return appointments;
			
	}
	public Appointment fetchAppointmentById(Integer id)
	{
		Optional<Appointment> opt = appointmentRepository.findById(id);
		if(opt.isPresent())
		{
			return opt.get();
		}
		else  throw  new IdNotFoundException("Id not found in database");
	}
	public List<Appointment> fetchAppointmentByDate(LocalDate date)
	{
		 if (date == null) {
	            throw new IllegalArgumentException("Date must be provided");
	        }

	        LocalDateTime startOfDay = date.atStartOfDay();
	        LocalDateTime endOfDay = date.atTime(23, 59, 59);

	        List<Appointment> appointments = appointmentRepository.findAppointmentsByDateRange(startOfDay, endOfDay);

	        if (appointments.isEmpty()) {
	            throw new NoRecordAvailableException("No appointments available on the given date");
	        }

	        return appointments;
	    }
	public List<Appointment> fetchAppointmentByPatient(Integer id)
	{
		Optional<Patient> opt = patientRepository.findById(id);
		if(opt.isEmpty())
		{
			throw new 	IdNotFoundException("Patient not found in database");
		}
        List<Appointment> appointments = appointmentRepository.findAppointmentsByPatientId(id);
        if (appointments.isEmpty()) {
            throw new NoRecordAvailableException("No appointments found for this patient");
        }

        return appointments;
	}
	public List<Appointment> fetchAppointmentByStatus(AppointmentStatus status)
	{
		List<Appointment> appointments = appointmentRepository.findAppointmentsByStatus(status);
		if(appointments.isEmpty())
		{
			throw new NoRecordAvailableException("No record available");
			
		}
		else return appointments;
	}
		public Appointment cancleAppointment(Integer id)
		{
		
		Optional<Appointment> opt = appointmentRepository.findById(id);
		if(opt.isEmpty())
		{
			throw new IdNotFoundException("Appointment not found");
		}
		 Appointment appointment = opt.get();
		 if (appointment.getStatus() != AppointmentStatus.BOOKED) {
		        throw new IllegalStateException("Only booked appointments can be canceled");
		    }
		 appointment.setStatus(AppointmentStatus.CANCELED);
		    return appointmentRepository.save(appointment);
	}

		public Appointment updateAppointmentStatus(Integer id, AppointmentStatus status) {
		    Optional<Appointment> opt = appointmentRepository.findById(id);
		    if (!opt.isPresent()) {
		        throw new IdNotFoundException("Appointment not found with id: " + id);
		    }

		    Appointment appointment = opt.get();
		    appointment.setStatus(status);
		    return appointmentRepository.save(appointment);

		}

}
