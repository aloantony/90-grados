package noventagrados.textui.excepcion;

/**
 * Excepción que se lanza cuando se intenta acceder a una opción no disponible.
 * 
 * @since 1.0
 * @version 1.0
 */
public class OpcionNoDisponibleException extends Exception {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    public OpcionNoDisponibleException() {
        super();
    }

    public OpcionNoDisponibleException(String mensaje) {
        super(mensaje);
    }

    public OpcionNoDisponibleException(Throwable causa) {
        super(causa);
    }

    public OpcionNoDisponibleException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
