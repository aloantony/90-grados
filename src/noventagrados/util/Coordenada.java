package noventagrados.util;

/**
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 * Tipo registro que representa una coordenada en el tablero.
 * Es inmutable y almacena la fila y columna de la posición.
 * @since 1.0
 * @version 1.0
 */
/**
 * Tipo registro que representa una coordenada en el tablero.
 * Es inmutable y almacena la fila y columna de la posición.
 */
public record Coordenada(int fila, int columna) {

    /**
     * Devuelve la representación en texto de la coordenada (ej. "00").
     *
     * @return Representación textual de la coordenada.
     */
    public String aTexto() {
        return String.format("%d%d", fila, columna);
    }
}