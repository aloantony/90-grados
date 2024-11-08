package noventagrados.modelo;

import noventagrados.util.Color;
import noventagrados.util.TipoPieza;

/**
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 *         Clase que representa una pieza en el juego Noventa Grados.
 *         Cada pieza tiene un tipo y un color.
 */
public class Pieza {
    private final TipoPieza tipoPieza;
    private final Color color;

    /**
     * Constructor de la clase Pieza.
     *
     * @param tipoPieza Tipo de la pieza (no nulo).
     * @param color     Color de la pieza (no nulo).
     * @throws IllegalArgumentException si el tipo de pieza o el color son nulos.
     */
    public Pieza(TipoPieza tipoPieza, Color color) {
        if (tipoPieza == null || color == null) {
            throw new IllegalArgumentException("El tipo de pieza y el color no pueden ser nulos.");
        }
        this.tipoPieza = tipoPieza;
        this.color = color;
    }

    /**
     * Retorna una copia profunda de la pieza.
     *
     * @return Clon de la pieza actual.
     */
    public Pieza clonar() {
        return new Pieza(this.tipoPieza, this.color);
    }

    /**
     * Devuelve el texto correspondiente al tipo de pieza y color (e.g., "PB").
     *
     * @return Representación textual de la pieza.
     */
    public String aTexto() {
        return "" + tipoPieza.toChar() + color.toChar();
    }

    /**
     * Devuelve el tipo de pieza.
     *
     * @return Tipo de la pieza.
     */
    public TipoPieza consultarTipoPieza() {
        return this.tipoPieza;
    }

    /**
     * Devuelve el color de la pieza.
     *
     * @return Color de la pieza.
     */
    public Color consultarColor() {
        return this.color;
    }

    /**
     * Genera un código hash para la pieza.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        int resultado = 17;
        resultado = 31 * resultado + tipoPieza.hashCode();
        resultado = 31 * resultado + color.hashCode();
        return resultado;
    }

    /**
     * Compara si dos piezas son iguales.
     *
     * @param obj Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Pieza))
            return false;
        Pieza otra = (Pieza) obj;
        return this.tipoPieza == otra.tipoPieza && this.color == otra.color;
    }

    /**
     * Devuelve la representación en texto de la pieza.
     *
     * @return Representación textual.
     */
    @Override
    public String toString() {
        return "Pieza{" +
                "tipoPieza=" + tipoPieza +
                ", color=" + color +
                '}';
    }
}
