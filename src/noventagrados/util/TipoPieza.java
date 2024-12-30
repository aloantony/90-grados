package noventagrados.util;

/**
 * Enumeración que representa los diferentes tipos de piezas en el juego.
 * Cada tipo de pieza está asociado a un carácter.
 * 
 * Los tipos disponibles son:
 * - PEON: Representado por el carácter 'P'
 * - REINA: Representada por el carácter 'R'
 *
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 * @version 1.0
 */
public enum TipoPieza {
    /** Representa una pieza de tipo Peón, usando el carácter 'P'. */
    PEON('P'),

    /** Representa una pieza de tipo Reina, usando el carácter 'R'. */
    REINA('R');

    private final char caracter;

    /**
     * Constructor privado de la enumeración TipoPieza.
     *
     * @param caracter Carácter que representa el tipo de pieza.
     */
    private TipoPieza(char caracter) {
        this.caracter = caracter;
    }

    /**
     * Devuelve el carácter asociado al tipo de pieza.
     *
     * @return Carácter del tipo de pieza.
     */
    public char toChar() {
        return caracter;
    }
}
