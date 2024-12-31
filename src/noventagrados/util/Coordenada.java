package noventagrados.util;

/**
 * Tipo registro que representa una coordenada en el tablero.
 * Es inmutable y almacena la fila y columna de la posición.
 * 
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 * @since 1.0
 * @version 1.0
 * @param fila    Fila de la coordenada.
 * @param columna Columna de la coordenada.
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