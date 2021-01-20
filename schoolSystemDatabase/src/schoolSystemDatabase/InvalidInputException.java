package schoolSystemDatabase;

/**
 * InvalidInputException.java <br>
 * This Class creates a subclass of RunTimeException to use to throw errors in school system and 
 * the related classes. <br>
 * @author Michael Wieszczek <br>
 * January 20th, 2020
 */
public class InvalidInputException extends RuntimeException {

	public InvalidInputException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidInputException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InvalidInputException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InvalidInputException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public InvalidInputException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}