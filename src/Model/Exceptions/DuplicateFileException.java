package Model.Exceptions;

public class DuplicateFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5516220286507543629L;
	
	private final int code;

	public DuplicateFileException(int code) {
		super();
		this.code = code;
	}

	public DuplicateFileException(String message, Throwable cause, int code) {
		super(message, cause);
		this.code = code;
	}

	public DuplicateFileException(String message, int code) {
		super(message);
		this.code = code;
	}

	public DuplicateFileException(Throwable cause, int code) {
		super(cause);
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	@Override
	public String getMessage() {
		String msg = super.getMessage();
		return msg.substring(msg.indexOf(':'), msg.length() - 1);
	}
}
