/**
 * Excepción que se lanza cuando el tablero de juego no es válido.
 * 
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 * @version 1.0
 * @since 1.0
 */
package noventagrados.control.excepcion;

public class TableroIncorrectoException extends Exception {
    private static final long serialVersionUID = 1L;

    public TableroIncorrectoException() {
        super();
    }

    public TableroIncorrectoException(String message) {
        super(message);
    }

    public TableroIncorrectoException(Throwable cause) {
        super(cause);
    }

    public TableroIncorrectoException(String message, Throwable cause) {
        super(message, cause);
    }

}