package noventagrados.textui.excepcion;

/**
 * Excepción que se lanza cuando se intenta acceder a una opción no disponible.
 * 
 * @since 1.0
 * @version 1.0
 * @autor <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 */
public class OpcionNoDisponibleException extends Exception {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto de la excepción.
     */
    public OpcionNoDisponibleException() {
        super();
    }

    /**
     * Constructor de la excepción con un mensaje.
     * 
     * @param mensaje El mensaje de la excepción.
     */
    public OpcionNoDisponibleException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor de la excepción con una causa.
     * 
     * @param causa La causa de la excepción.
     */
    public OpcionNoDisponibleException(Throwable causa) {
        super(causa);
    }

    /**
     * Constructor de la excepción con un mensaje y una causa.
     * 
     * @param mensaje El mensaje de la excepción.
     * @param causa   La causa de la excepción.
     */
    public OpcionNoDisponibleException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
