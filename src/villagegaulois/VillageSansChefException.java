package villagegaulois;

public class VillageSansChefException extends Exception {
	private static final long serialVersionUID = 1L;

	public VillageSansChefException() {
		super();
	}

	public VillageSansChefException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public VillageSansChefException(String message, Throwable cause) {
		super(message, cause);
	}

	public VillageSansChefException(String message) {
		super(message);
	}

	public VillageSansChefException(Throwable cause) {
		super(cause);
	}
}
