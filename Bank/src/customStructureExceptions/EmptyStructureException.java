package customStructureExceptions;

@SuppressWarnings("serial")
public class EmptyStructureException extends Exception{
	
	public EmptyStructureException(String message) {
		super("EmptyStructureException: " + message);
	}
}
