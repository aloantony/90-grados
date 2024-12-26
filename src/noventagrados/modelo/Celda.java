package noventagrados.modelo;

import noventagrados.util.Coordenada;

import java.util.Objects;

import noventagrados.util.Color;

/**
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 *         Clase que representa una celda en el tablero del juego Noventa
 *         Grados.
 *         Contiene una coordenada y, opcionalmente, una pieza.
 */
public class Celda {
    private final Coordenada coordenada;
    private Pieza pieza;

    /**
     * Constructor de la clase Celda.
     *
     * @param coordenada Coordenada asociada a la celda (no nula).
     * @throws IllegalArgumentException si la coordenada es nula.
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
    public Pieza consultarPieza()  {
    	Pieza pieza = null;
    	if(this.pieza != null) {
    		pieza = this.pieza.clonar();}
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

    @Override
	public int hashCode() {
		return Objects.hash(coordenada, pieza);
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celda other = (Celda) obj;
		return Objects.equals(coordenada, other.coordenada) && Objects.equals(pieza, other.pieza);
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
