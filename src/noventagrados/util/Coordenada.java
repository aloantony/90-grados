package noventagrados.util;

/**
 * Tipo registro que representa una coordenada en el tablero.
 * Es inmutable y almacena la fila y columna de la posición.
 * 
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 * 
 * @param fila    La posición vertical de la coordenada
 * @param columna La posición horizontal de la coordenada
 */
public record Coordenada(int fila, int columna) {

    /**
     * Devuelve la representación en texto de la coordenada (ejemplo: "00").
     *
     * @return Representación textual de la coordenada.
     */
    public String aTexto() {
        return String.format("%d%d", fila, columna);
    }
}