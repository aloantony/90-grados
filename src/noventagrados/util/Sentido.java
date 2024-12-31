package noventagrados.util;

/**
 * Enumeración que representa los sentidos posibles de movimiento en el
 * juego.
 * Cada sentido tiene asociado un desplazamiento en filas y columnas.
 * 
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 * @since 1.0
 * @version 1.0
 */
public enum Sentido {
    /**
     * Representa un movimiento vertical hacia el arriba.
     * Desplazamiento: -1 en filas, 0 en columnas.
     */
    VERTICAL_N(-1, 0),

    /**
     * Representa un movimiento vertical hacia el abajo.
     * Desplazamiento: 1 en filas, 0 en columnas.
     */
    VERTICAL_S(1, 0),

    /**
     * Representa un movimiento horizontal hacia la derecha.
     * Desplazamiento: 0 en filas, 1 en columnas.
     */
    HORIZONTAL_E(0, 1),

    /**
     * Representa un movimiento horizontal hacia la izquierda.
     * Desplazamiento: 0 en filas, -1 en columnas.
     */
    HORIZONTAL_O(0, -1);

    private final int desplazamientoEnFilas;
    private final int desplazamientoEnColumnas;

    /**
     * Constructor privado de la enumeración Sentido.
     *
     * @param desplazamientoEnFilas    Desplazamiento en filas.
     * @param desplazamientoEnColumnas Desplazamiento en columnas.
     */
    private Sentido(int desplazamientoEnFilas, int desplazamientoEnColumnas) {
        this.desplazamientoEnFilas = desplazamientoEnFilas;
        this.desplazamientoEnColumnas = desplazamientoEnColumnas;
    }

    /**
     * Devuelve el desplazamiento en filas asociado al sentido.
     *
     * @return Desplazamiento en filas.
     */
    public int consultarDesplazamientoEnFilas() {
        return desplazamientoEnFilas;
    }

    /**
     * Devuelve el desplazamiento en columnas asociado al sentido.
     *
     * @return Desplazamiento en columnas.
     */
    public int consultarDesplazamientoEnColumnas() {
        return desplazamientoEnColumnas;
    }
}
