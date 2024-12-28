/**
 * Excepción lanzada cuando una operación relacionada con el tamaño del array encuentra un tamaño de array no válido.
 * 
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 * @version 1.0
 * @since 1.0
 */
package noventagrados.control.excepcion;

public class TamañoIncorrectoException extends Exception {
    private static final long serialVersionUID = 1L;

    public TamañoIncorrectoException() {
        super();
    }

    public TamañoIncorrectoException(String message) {
        super(message);
    }

    public TamañoIncorrectoException(Throwable cause) {
        super(cause);
    }

    public TamañoIncorrectoException(String message, Throwable cause) {
        super(message, cause);
    }

}
