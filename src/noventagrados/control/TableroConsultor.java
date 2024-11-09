package noventagrados.control;

import java.util.Objects;

import noventagrados.modelo.Celda;
import noventagrados.modelo.Pieza;
import noventagrados.modelo.Tablero;
import noventagrados.util.Color;
import noventagrados.util.Coordenada;
import noventagrados.util.Sentido;
import noventagrados.util.TipoPieza;

/**
 * Clase que permite realizar consultas avanzadas sobre el tablero.
 * 
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 */
public class TableroConsultor {
    private final Tablero tablero;

    /**
     * Constructor de la clase TableroConsultor.
     *
     * @param tablero Tablero sobre el que se realizarán las consultas (no nulo).
     * @throws IllegalArgumentException si el tablero es nulo.
     */
    public TableroConsultor(Tablero tablero) {
        if (tablero == null) {
            throw new IllegalArgumentException("El tablero no puede ser nulo.");
        }
        this.tablero = tablero;
    }

    /**
     * Calcula el sentido entre dos coordenadas.
     *
     * @param origen  Coordenada de origen (no nula).
     * @param destino Coordenada de destino (no nula).
     * @return Sentido entre las coordenadas o null si no es válido.
     * @throws IllegalArgumentException si alguna coordenada es nula.
     */
    public Sentido calcularSentido(Coordenada origen, Coordenada destino) {
        if (origen == null || destino == null) {
            throw new IllegalArgumentException("Las coordenadas no pueden ser nulas.");
        }
        int diferenciaFila = destino.fila() - origen.fila();
        int diferenciaColumna = destino.columna() - origen.columna();
        Sentido resultado = null;

        if (diferenciaFila == 0) {
            resultado = diferenciaColumna > 0 ? Sentido.HORIZONTAL_E : Sentido.HORIZONTAL_O;
        } else if (diferenciaColumna == 0) {
            resultado = diferenciaFila > 0 ? Sentido.VERTICAL_S : Sentido.VERTICAL_N;
        }
        return resultado;
    }

    /**
     * Retorna la distancia en horizontal entre dos coordenadas en la misma fila.
     *
     * @param origen  Coordenada de origen (no nula).
     * @param destino Coordenada de destino (no nula).
     * @return Distancia o -1 si no están en la misma horizontal.
     * @throws IllegalArgumentException si alguna coordenada es nula.
     */
    public int consultarDistanciaEnHorizontal(Coordenada origen, Coordenada destino) {
        if (origen == null || destino == null) {
            throw new IllegalArgumentException("Las coordenadas no pueden ser nulas.");
        }
        if (origen.fila() == destino.fila()) {
            int diferencia = destino.columna() - origen.columna();
            return diferencia < 0 ? -diferencia : diferencia;
        }
        return -1;
    }

    /**
     * Retorna la distancia en vertical entre dos coordenadas en la misma columna.
     *
     * @param origen  Coordenada de origen (no nula).
     * @param destino Coordenada de destino (no nula).
     * @return Distancia o -1 si no están en la misma vertical.
     * @throws IllegalArgumentException si alguna coordenada es nula.
     */
    public int consultarDistanciaEnVertical(Coordenada origen, Coordenada destino) {
        if (origen == null || destino == null) {
            throw new IllegalArgumentException("Las coordenadas no pueden ser nulas.");
        }
        if (origen.columna() == destino.columna()) {
            int diferencia = destino.fila() - origen.fila();
            return diferencia < 0 ? -diferencia : diferencia;
        }
        return -1;
    }

    /**
     * Retorna el número de piezas del tipo y color indicados en el tablero.
     *
     * @param tipoPieza Tipo de pieza a contar (no nulo).
     * @param color     Color de las piezas a contar (no nulo).
     * @return Número de piezas encontradas.
     * @throws IllegalArgumentException si el tipo de pieza o el color son nulos.
     */
    public int consultarNumeroPiezas(TipoPieza tipoPieza, Color color) {
        if (tipoPieza == null || color == null) {
            throw new IllegalArgumentException("El tipo de pieza y el color no pueden ser nulos.");
        }
        int contador = 0;
        Celda[] celdas = tablero.consultarCeldas();
        for (int i = 0; i < celdas.length; i++) {
            Pieza pieza = celdas[i].consultarPieza();
            if (pieza != null && pieza.consultarTipoPieza() == tipoPieza && pieza.consultarColor() == color) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Retorna el número de piezas en la misma horizontal de la coordenada dada.
     *
     * @param coordenada Coordenada de referencia (no nula).
     * @return Número de piezas en la misma fila.
     * @throws IllegalArgumentException si la coordenada es nula.
     */
    public int consultarNumeroPiezasEnHorizontal(Coordenada coordenada) {
        if (coordenada == null) {
            throw new IllegalArgumentException("La coordenada no puede ser nula.");
        }
        int fila = coordenada.fila();
        int contador = 0;
        for (int columna = 0; columna < tablero.consultarNumeroColumnas(); columna++) {
            Celda celda = tablero.obtenerCelda(new Coordenada(fila, columna));
            if (!celda.estaVacia()) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Retorna el número de piezas en la misma vertical de la coordenada dada.
     *
     * @param coordenada Coordenada de referencia (no nula).
     * @return Número de piezas en la misma columna.
     * @throws IllegalArgumentException si la coordenada es nula.
     */
    public int consultarNumeroPiezasEnVertical(Coordenada coordenada) {
        if (coordenada == null) {
            throw new IllegalArgumentException("La coordenada no puede ser nula.");
        }
        int columna = coordenada.columna();
        int contador = 0;
        for (int fila = 0; fila < tablero.consultarNumeroFilas(); fila++) {
            Celda celda = tablero.obtenerCelda(new Coordenada(fila, columna));
            if (!celda.estaVacia()) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Verifica si la reina del color indicado está en la celda central.
     *
     * @param color Color de la reina (no nulo).
     * @return true si la reina está en el centro, false en caso contrario.
     * @throws IllegalArgumentException si el color es nulo.
     */
    public boolean estaReinaEnElCentro(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("El color no puede ser nulo.");
        }
        Coordenada centro = new Coordenada(3, 3);
        Celda celdaCentral = tablero.obtenerCelda(centro);
        Pieza pieza = celdaCentral.consultarPieza();
        return pieza != null && pieza.consultarTipoPieza() == TipoPieza.REINA && pieza.consultarColor() == color;
    }

    /**
     * Verifica si la reina del color indicado está en el tablero.
     *
     * @param color Color de la reina (no nulo).
     * @return true si la reina está en el tablero, false en caso contrario.
     * @throws IllegalArgumentException si el color es nulo.
     */
    public boolean hayReina(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("El color no puede ser nulo.");
        }
        Celda[] celdas = tablero.consultarCeldas();
        for (int i = 0; i < celdas.length; i++) {
            Pieza pieza = celdas[i].consultarPieza();
            if (pieza != null && pieza.consultarTipoPieza() == TipoPieza.REINA && pieza.consultarColor() == color) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tablero);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TableroConsultor other = (TableroConsultor) obj;
        return Objects.equals(tablero, other.tablero);
    }

    @Override
    public String toString() {
        return "TableroConsultor [tablero=" + tablero + "]";
    }

}
