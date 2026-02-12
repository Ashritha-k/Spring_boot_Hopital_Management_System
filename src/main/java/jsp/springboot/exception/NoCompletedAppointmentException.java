package jsp.springboot.exception;

public class NoCompletedAppointmentException extends RuntimeException {
	public NoCompletedAppointmentException(String message)
	{
		super(message);
	}

}
