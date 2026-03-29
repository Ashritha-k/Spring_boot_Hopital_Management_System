package jsp.springboot.controller;

import java.time.LocalDate;
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

import jsp.springboot.AppointmentStatus;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Appointment;
import jsp.springboot.service.AppointmentService;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	@PostMapping
	public ResponseEntity<ResponseStructure<Appointment>> BookAppointment(@RequestBody Appointment appointmet)
	{
	return appointmentService.BookAppointment(appointmet);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAllAppointment()
	{
		return appointmentService.fetchAllAppointment();
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Appointment>> fetchAppointmentById( @PathVariable Integer id)
	{
		return appointmentService.fetchAppointmentById(id);
	}
	@GetMapping("/date")
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByDate(
	        @RequestParam("date") String dateString) {
	    LocalDate date = LocalDate.parse(dateString);
	    return appointmentService.fetchAppointmentByDate(date);
	}
	@GetMapping("/patientId/{id}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByPatient(@PathVariable Integer id)
	{
		return appointmentService.fetchAppointmentByPatient(id);
	}
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByStatus(@PathVariable AppointmentStatus status)
	{
		return appointmentService.fetchAppointmentByStatus(status);
	}
	@PutMapping("/cancle/{id}")
	public  ResponseEntity<ResponseStructure<Appointment>> cancleAppointment(@PathVariable Integer id)
	{
		return appointmentService.cancleAppointment(id);
	}
	@PutMapping("/updateStatus/{id}")
	public ResponseEntity<ResponseStructure<Appointment>> updateAppointmentStatus(
	        @PathVariable Integer id,
	        @RequestParam AppointmentStatus status) {
	    return appointmentService.updateAppointmentStatus(id, status);
	}
	

}
