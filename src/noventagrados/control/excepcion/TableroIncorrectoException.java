/**
 * Excepción que se lanza cuando el tablero de juego no es válido.
 * 
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 * @version 1.0
 * @since 1.0
 */
package noventagrados.control.excepcion;

/**
 * Excepción que se lanza cuando el tablero de juego no es válido.
 * 
 * @since 1.0
 * @version 1.0
 * @see Exception
 */
public class TableroIncorrectoException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la excepción.
     */
    public TableroIncorrectoException() {
        super();
    }

    /**
     * Constructor de la excepción con un mensaje.
     * 
     * @param message El mensaje de la excepción.
     */
    public TableroIncorrectoException(String message) {
        super(message);
    }

    /**
     * Constructor de la excepción con una causa.
     * 
     * @param cause La causa de la excepción.
     */
    public TableroIncorrectoException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor de la excepción con un mensaje y una causa.
     * 
     * @param message El mensaje de la excepción.
     * @param cause   La causa de la excepción.
     */
    public TableroIncorrectoException(String message, Throwable cause) {
        super(message, cause);
    }

}