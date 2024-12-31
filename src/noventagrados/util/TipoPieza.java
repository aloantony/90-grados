package noventagrados.util;

/**
 * Enumeración que representa los tipos de piezas en el juego.
 * Asocia cada tipo de pieza con un carácter ('P' para peón, 'R' para reina).
 * 
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
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
