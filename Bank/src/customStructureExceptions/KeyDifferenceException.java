package customStructureExceptions;

@SuppressWarnings("serial")
public class KeyDifferenceException extends Exception{
	
	public KeyDifferenceException (String message) {
		super("KeyDifferenceException: " + message);
	}
}
