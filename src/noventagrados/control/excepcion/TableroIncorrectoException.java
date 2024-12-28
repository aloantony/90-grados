package noventagrados.control.excepcion;

public class TableroIncorrectoException extends Exception {
    private static final long serialVersionUID = 1L;

    public TableroIncorrectoException() {
        super();
    }

    public TableroIncorrectoException(String message) {
        super("El tablero es incorrecto.");
    }

    public TableroIncorrectoException(Throwable cause) {
        super(cause);
    }

    public TableroIncorrectoException(String message, Throwable cause) {
        super("El tablero es incorrecto.", cause);
    }

}