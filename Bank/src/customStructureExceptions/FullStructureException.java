package customStructureExceptions;

@SuppressWarnings("serial")
public class FullStructureException extends Exception{
	
	public FullStructureException (String message) {
		super("FullStructureException: " + message);
	}
}
