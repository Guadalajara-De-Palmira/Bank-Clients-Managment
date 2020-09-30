package customStructureExceptions;

@SuppressWarnings("serial")
public class NotFoundException extends Exception {
	
	public NotFoundException(String message) {
		super ("NotFoundExcpetion: " + message);
	}
}
