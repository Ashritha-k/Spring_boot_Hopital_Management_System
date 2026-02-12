package jsp.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jsp.springboot.dto.ResponseStructure;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleINFE(IdNotFoundException exception){
		ResponseStructure<String> response =new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(exception.getMessage());
		response.setData("Failure");
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(NoRecordAvailableException.class)
	public ResponseEntity<ResponseStructure<String>> handleNRAE(NoRecordAvailableException exception)
	{
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(exception.getMessage());
		response.setData("Failure");
		return  new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ResponseStructure<String>> handleEAEE(EmailAlreadyExistsException exception)
	{
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.CONFLICT.value());
		response.setMessage(exception.getMessage());
		response.setData("Failure");
		return  new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(PhoneNumberAlreadyExistsException.class)
	public ResponseEntity<ResponseStructure<String>> handlePnAEE(PhoneNumberAlreadyExistsException exception)
	{
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.CONFLICT.value());
		response.setMessage(exception.getMessage());
		response.setData("Failure");
		return  new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(DoctorAlreadyBookedException.class)
	public ResponseEntity<ResponseStructure<String>> handleDABE(DoctorAlreadyBookedException exception)
	{
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.CONFLICT.value());
		response.setMessage(exception.getMessage());
		response.setData("Failure");
		return  new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(PatientAlreadyBookedException.class)
	public ResponseEntity<ResponseStructure<String>> handlePABE(PatientAlreadyBookedException exception)
	{
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.CONFLICT.value());
		response.setMessage(exception.getMessage());
		response.setData("Failure");
		return  new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(NoCompletedAppointmentException.class)
	public ResponseEntity<ResponseStructure<String>> handleNCAE(NoCompletedAppointmentException exception)
	{
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.CONFLICT.value());
		response.setMessage(exception.getMessage());
		response.setData("Failure");
		return  new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.CONFLICT);
	}
	
	
	

	
}
