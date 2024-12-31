package noventagrados.modelo;

import noventagrados.util.Coordenada;
import noventagrados.util.Color;

/**
 * Clase que representa una celda en el tablero del juego Noventa
 * Grados.
 * Contiene una coordenada y, opcionalmente, una pieza.
 * 
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 * @since 1.0
 * @version 1.0
 */
public class Celda {
    private final Coordenada coordenada;
    private Pieza pieza;

    /**
     * Constructor de la clase Celda.
     *
     * @param coordenada Coordenada asociada a la celda (no nula).
     */
    public Celda(Coordenada coordenada) {
        this.coordenada = coordenada;
        this.pieza = null;
    }

    /**
     * Coloca una pieza en la celda.
     *
     * @param pieza Pieza a colocar en la celda.
     */
    public void colocar(Pieza pieza) {
        this.pieza = pieza;
    }

    /**
     * Elimina la pieza de la celda.
     */
    public void eliminarPieza() {
        this.pieza = null;
    }

    /**
     * Indica si la celda está vacía.
     *
     * @return true si la celda no contiene una pieza, false en caso contrario.
     */
    public boolean estaVacia() {
        return this.pieza == null;
    }

    /**
     * Devuelve la pieza colocada en la celda.
     *
     * @return Pieza en la celda o null si está vacía.
     */
    public Pieza consultarPieza() {
        Pieza pieza = null;
        if (this.pieza != null) {
            pieza = this.pieza.clonar();
        }
        return pieza;
    }

    /**
     * Devuelve el color de la pieza colocada en la celda.
     *
     * @return Color de la pieza o null si la celda está vacía.
     */
    public Color consultarColorDePieza() {
        return (pieza != null) ? pieza.consultarColor() : null;
    }

    /**
     * Devuelve la coordenada de la celda.
     *
     * @return Coordenada de la celda.
     */
    public Coordenada consultarCoordenada() {
        return this.coordenada;
    }

    /**
     * Retorna una copia profunda de la celda.
     *
     * @return Clon de la celda actual.
     */
    public Celda clonar() {
        Celda clon = new Celda(new Coordenada(coordenada.fila(), coordenada.columna()));
        if (this.pieza != null) {
            clon.colocar(this.pieza.clonar());
        }
        return clon;
    }

    /**
     * Genera un código hash para la celda.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        int resultado = 17;
        resultado = 31 * resultado + coordenada.hashCode();
        resultado = 31 * resultado + (pieza != null ? pieza.hashCode() : 0);
        return resultado;
    }

    /**
     * Compara si dos celdas son iguales.
     *
     * @param obj Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Celda))
            return false;
        Celda otra = (Celda) obj;
        boolean mismasCoordenadas = this.coordenada.equals(otra.coordenada);
        boolean mismasPiezas = (this.pieza == null && otra.pieza == null) ||
                (this.pieza != null && this.pieza.equals(otra.pieza));
        return mismasCoordenadas && mismasPiezas;
    }

    /**
     * Devuelve la representación en texto de la celda.
     *
     * @return Representación textual.
     */
    @Override
    public String toString() {
        return "Celda{" +
                "coordenada=" + coordenada +
                ", pieza=" + (pieza != null ? pieza.aTexto() : "null") +
                '}';
    }
}
