package gasior.szymon.epam.Exceptions;

import java.io.IOException;

public class BadFormatException extends IOException {


    public BadFormatException() {
        super();
    }

    public BadFormatException(String message) {
        super(message);
    }

    public BadFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadFormatException(Throwable cause) {
        super(cause);
    }
}
