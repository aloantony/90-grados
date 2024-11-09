package noventagrados.control;

import noventagrados.modelo.Pieza;
import noventagrados.util.Color;
import noventagrados.util.TipoPieza;

/**
 * Clase que representa una caja donde se almacenan las piezas capturadas en el
 * juego Noventa Grados.
 * 
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 */
public class Caja {
    private final Color color;
    private Pieza[] piezas;
    private int numPiezas;

    private static final int CAPACIDAD_MAXIMA = 7;

    /**
     * Constructor de la clase Caja.
     *
     * @param color Color asignado a la caja (no nulo).
     */
    public Caja(Color color) {
        this.color = color;
        this.piezas = new Pieza[CAPACIDAD_MAXIMA];
        this.numPiezas = 0;
    }

    /**
     * Añade una pieza a la caja.
     *
     * @param pieza Pieza a añadir (no nula).
     */
    public void añadir(Pieza pieza) {
        if (numPiezas != CAPACIDAD_MAXIMA) {
            if (this.color == pieza.consultarColor()) {
                piezas[numPiezas++] = pieza;
            }
        }
    }

    /**
     * Devuelve un arreglo con clones de todas las piezas en la caja.
     *
     * @return Arreglo de piezas clonadas.
     */
    public Pieza[] consultarPiezas() {
        Pieza[] piezasClonadas = new Pieza[numPiezas];
        for (int i = 0; i < numPiezas; i++) {
            piezasClonadas[i] = piezas[i].clonar();
        }
        return piezasClonadas;
    }

    /**
     * Devuelve el número total de piezas contenidas en la caja.
     *
     * @return Número de piezas.
     */
    public int contarPiezas() {
        return numPiezas;
    }

    /**
     * Devuelve el número de piezas de un determinado tipo contenidas en la caja.
     *
     * @param tipoPieza Tipo de pieza a contar (no nulo).
     * @return Número de piezas del tipo especificado.
     * @throws IllegalArgumentException si el tipo de pieza es nulo.
     */
    public int contarPiezas(TipoPieza tipoPieza) {
        int contador = 0;
        for (int i = 0; i < numPiezas; i++) {
            if (piezas[i].consultarTipoPieza() == tipoPieza) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el color asignado a la caja.
     *
     * @return Color de la caja.
     */
    public Color consultarColor() {
        return color;
    }

    /**
     * Retorna una copia profunda de la caja.
     *
     * @return Clon de la caja actual.
     */
    public Caja clonar() {
        Caja clon = new Caja(this.color);
        for (int i = 0; i < this.numPiezas; i++) {
            clon.añadir(this.piezas[i].clonar());
        }
        return clon;
    }

    /**
     * Genera un código hash para la caja.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        int resultado = 17;
        resultado = 31 * resultado + color.hashCode();
        for (int i = 0; i < numPiezas; i++) {
            resultado = 31 * resultado + piezas[i].hashCode();
        }
        return resultado;
    }

    /**
     * Compara si dos cajas son iguales.
     *
     * @param obj Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Caja))
            return false;
        Caja otra = (Caja) obj;
        if (this.color != otra.color || this.numPiezas != otra.numPiezas) {
            return false;
        }
        for (int i = 0; i < numPiezas; i++) {
            if (!this.piezas[i].equals(otra.piezas[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Devuelve la representación en texto de la caja.
     *
     * @return Representación textual.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Caja{color=").append(color).append(", piezas=[");
        for (int i = 0; i < numPiezas; i++) {
            sb.append(piezas[i].aTexto());
            if (i < numPiezas - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}
