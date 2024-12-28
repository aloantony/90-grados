package noventagrados.control.excepcion;

public class TamañoIncorrectoException extends Exception {
    private static final long serialVersionUID = 1L;

    public TamañoIncorrectoException() {
        super();
    }

    public TamañoIncorrectoException(String message) {
        super("El tablero es incorrecto.");
    }

    public TamañoIncorrectoException(Throwable cause) {
        super(cause);
    }

    public TamañoIncorrectoException(String message, Throwable cause) {
        super("El tablero es incorrecto.", cause);
    }

}
