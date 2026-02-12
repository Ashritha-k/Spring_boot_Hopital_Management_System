package jsp.springboot.exception;

public class PatientAlreadyBookedException extends RuntimeException {
	 public PatientAlreadyBookedException(String message)
	 {
		 super(message);
	 }

}
