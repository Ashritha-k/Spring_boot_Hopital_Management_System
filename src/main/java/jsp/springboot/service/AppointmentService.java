	package jsp.springboot.service;
	
	import java.time.LocalDate;
	import java.util.List;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.stereotype.Service;

import jsp.springboot.AppointmentStatus;
import jsp.springboot.dao.AppointmentDao;
	import jsp.springboot.dto.ResponseStructure;
	import jsp.springboot.entity.Appointment;
	import jsp.springboot.exception.NoRecordAvailableException;
	
	@Service
	public class AppointmentService {
		
		@Autowired
		private AppointmentDao appointmentDao;
		
		
		
		public ResponseEntity<ResponseStructure<Appointment>> BookAppointment(Appointment appointment)
		{
			ResponseStructure<Appointment> response = new  ResponseStructure<Appointment>();
	        response.setStatusCode(HttpStatus.CREATED.value());
	        response.setMessage("Department created");
	        response.setData(appointmentDao.BookAppointment(appointment));
	        return new  ResponseEntity<ResponseStructure<Appointment>>(response,HttpStatus.CREATED);
	        
			
		}
		public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAllAppointment()
		{
			ResponseStructure<List<Appointment>> response = new  ResponseStructure<List<Appointment>>();
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Department created");
	        response.setData(appointmentDao.fetchAllAppointment());
	        return new  ResponseEntity<ResponseStructure<List<Appointment>>>(response,HttpStatus.OK);
	        
	
		}
		public ResponseEntity<ResponseStructure<Appointment>> fetchAppointmentById(Integer id)
		{
			ResponseStructure<Appointment> response = new  ResponseStructure<Appointment>();
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Department created");
	        response.setData(appointmentDao.fetchAppointmentById(id));
	        return new  ResponseEntity<ResponseStructure<Appointment>>(response,HttpStatus.OK);
	        
			
		}
		  public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByDate(LocalDate date) {
		        List<Appointment> appointments = appointmentDao.fetchAppointmentByDate(date);

		        ResponseStructure<List<Appointment>> response = new ResponseStructure<>();
		        response.setStatusCode(HttpStatus.OK.value());
		        response.setMessage("Appointments fetched successfully for the given date");
		        response.setData(appointments);

		        return new ResponseEntity<>(response, HttpStatus.OK);
		    }
		  public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByPatient(Integer id) {
		        List<Appointment> appointments = appointmentDao.fetchAppointmentByPatient(id);

		        ResponseStructure<List<Appointment>> response = new ResponseStructure<>();
		        response.setStatusCode(HttpStatus.OK.value());
		        response.setMessage("Appointments fetched successfully ");
		        response.setData(appointments);

		        return new ResponseEntity<>(response, HttpStatus.OK);
		    }
		  public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByStatus(AppointmentStatus status) {
		        List<Appointment> appointments = appointmentDao.fetchAppointmentByStatus(status);

		        ResponseStructure<List<Appointment>> response = new ResponseStructure<>();
		        response.setStatusCode(HttpStatus.OK.value());
		        response.setMessage("Appointments fetched successfully ");
		        response.setData(appointments);

		        return new ResponseEntity<>(response, HttpStatus.OK);
		    }
		  public ResponseEntity<ResponseStructure<Appointment>> cancleAppointment(Integer id)
			{
				ResponseStructure<Appointment> response = new  ResponseStructure<Appointment>();
		        response.setStatusCode(HttpStatus.CREATED.value());
		        response.setMessage("Department created");
		        response.setData(appointmentDao.cancleAppointment(id));
		        return new  ResponseEntity<ResponseStructure<Appointment>>(response,HttpStatus.CREATED);
		        
				
			}
		  public ResponseEntity<ResponseStructure<Appointment>> updateAppointmentStatus(Integer id, AppointmentStatus status) {
			    Appointment updatedAppointment = appointmentDao.updateAppointmentStatus(id, status);

			    ResponseStructure<Appointment> response = new ResponseStructure<>();
			    response.setStatusCode(HttpStatus.OK.value());
			    response.setMessage("Appointment status updated successfully");
			    response.setData(updatedAppointment);

			    return new ResponseEntity<ResponseStructure<Appointment>>(response, HttpStatus.OK);
		  }
		  
		  
		
	
	}
