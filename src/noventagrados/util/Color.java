package noventagrados.util;

/**
 * * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 * 
 * Enumeración que representa los colores de las piezas y turnos.
 * Asocia cada color con un carácter ('B' para blanco, 'N' para negro).
 * 
 * @since 1.0
 * @version 1.0
 */
public enum Color {
    BLANCO('B'),
    NEGRO('N');

    private final char letra;

    /**
     * Constructor privado de la enumeración Color.
     *
     * @param letra Carácter que representa el color.
     */
    private Color(char letra) {
        this.letra = letra;
    }

    /**
     * Devuelve el color contrario al actual.
     *
     * @return Color contrario.
     */
    public Color consultarContrario() {
        return this == BLANCO ? NEGRO : BLANCO;
    }

    /**
     * Devuelve el carácter asociado al color.
     *
     * @return Carácter del color.
     */
    public char toChar() {
        return letra;
    }
}
