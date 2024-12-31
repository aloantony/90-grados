/**
 * Excepción lanzada cuando una operación relacionada con el tamaño del array encuentra un tamaño de array no válido.
 * 
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 * @version 1.0
 * @since 1.0
 */
package noventagrados.control.excepcion;

/**
 * Excepción lanzada cuando una operación relacionada con el tamaño del array
 * encuentra un tamaño de array no válido.
 * 
 * @since 1.0
 * @version 1.0
 * @see Exception
 */
public class TamañoIncorrectoException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la excepción.
     */
    public TamañoIncorrectoException() {
        super();
    }

    /**
     * Constructor de la excepción con un mensaje.
     * 
     * @param message El mensaje de la excepción.
     */
    public TamañoIncorrectoException(String message) {
        super(message);
    }

    /**
     * Constructor de la excepción con una causa.
     * 
     * @param cause La causa de la excepción.
     */
    public TamañoIncorrectoException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor de la excepción con un mensaje y una causa.
     * 
     * @param message El mensaje de la excepción.
     * @param cause   La causa de la excepción.
     */
    public TamañoIncorrectoException(String message, Throwable cause) {
        super(message, cause);
    }
}
