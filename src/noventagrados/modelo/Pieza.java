package noventagrados.modelo;

import java.util.Objects;

import noventagrados.util.Color;
import noventagrados.util.TipoPieza;

/**
 * Clase que representa una pieza en el juego Noventa Grados.
 * Cada pieza tiene un tipo y un color.
 * 
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 */
public class Pieza {
    private int id;
    private final TipoPieza tipoPieza;
    private final Color color;
    private static int identificadorDeNuevasPiezas = 0;

    /**
     * Constructor de la clase Pieza.
     *
     * @param tipoPieza Tipo de la pieza (no nulo).
     * @param color     Color de la pieza (no nulo).
     */
    public Pieza(TipoPieza tipoPieza, Color color) {
        this.id = ++identificadorDeNuevasPiezas;
        this.tipoPieza = tipoPieza;
        this.color = color;
    }

    public Pieza(int id, TipoPieza tipoPieza, Color color) {
        this.id = ++identificadorDeNuevasPiezas;
        this.tipoPieza = tipoPieza;
        this.color = color;
    }

    /**
     * Retorna una copia profunda de la pieza.
     *
     * @return Clon de la pieza actual.
     */
    public Pieza clonar() {
        return new Pieza(this.id, this.tipoPieza, this.color);
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
     * Devuelve el identificador de la pieza.
     *
     * @return Identificador de la pieza.
     */
    public int consultarIdentificacion() {
        return this.id;
    }

    /**
     * Devuelve el color de la pieza.
     *
     * @return Color de la pieza.
     */
    public Color consultarColor() {
        return this.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, tipoPieza);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pieza other = (Pieza) obj;
        return color == other.color && tipoPieza == other.tipoPieza;
    }

    @Override
    public String toString() {
        return "Pieza [tipoPieza=" + tipoPieza + ", color=" + color + "]";
    }

}
