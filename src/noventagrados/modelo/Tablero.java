package noventagrados.modelo;

import java.util.ArrayList;
import java.util.List;

import noventagrados.util.Coordenada;

/**
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 *         Clase que representa el tablero del juego Noventa Grados.
 *         Contiene una matriz de celdas de 7x7.
 */
public class Tablero {
    private static final int TAMAÑO = 7;
    private final List<List<Celda>> celdas;

    /**
     * Constructor de la clase Tablero.
     * Inicializa el tablero con celdas vacías.
     */
    public Tablero() {
        celdas = new ArrayList<>(TAMAÑO);
        for (int fila = 0; fila < TAMAÑO; fila++) {
            List<Celda> filaCeldas = new ArrayList<>(TAMAÑO);
            for (int columna = 0; columna < TAMAÑO; columna++) {
                filaCeldas.add(new Celda(new Coordenada(fila, columna)));
            }
            celdas.add(filaCeldas);
        }
    }

    /**
     * Coloca una pieza en la celda indicada por la coordenada.
     *
     * @param pieza      Pieza a colocar.
     * @param coordenada Coordenada donde se colocará la pieza.
     */
    public void colocar(Pieza pieza, Coordenada coordenada) {
        if (pieza == null || coordenada == null) {
            // Ignorar si la pieza o coordenada es nula
            return;
        }
        Celda celda = obtenerCelda(coordenada);
        if (celda != null) {
            celda.colocar(pieza);
        }
    }

    /**
     * Elimina la pieza de la celda indicada por la coordenada.
     *
     * @param coordenada Coordenada de la celda.
     */
    public void eliminarPieza(Coordenada coordenada) {
        if (coordenada == null) {
            // Ignorar si la coordenada es nula
            return;
        }
        Celda celda = obtenerCelda(coordenada);
        if (celda != null) {
            celda.eliminarPieza();
        }
    }

    /**
     * Devuelve una copia de la celda en la coordenada dada.
     *
     * @param coordenada Coordenada de la celda.
     * @return Copia de la celda o null si la coordenada es inválida.
     */
    public Celda consultarCelda(Coordenada coordenada) {
        Celda celda = obtenerCelda(coordenada);
        return (celda != null) ? celda.clonar() : null;
    }

    /**
     * Devuelve la celda en la coordenada dada (sin clonar).
     *
     * @param coordenada Coordenada de la celda.
     * @return Celda o null si la coordenada es inválida.
     */
    Celda obtenerCelda(Coordenada coordenada) {
        if (coordenada != null) {
            int fila = coordenada.fila();
            int columna = coordenada.columna();
            if (fila >= 0 && fila < TAMAÑO && columna >= 0 && columna < TAMAÑO) {
                return celdas.get(fila).get(columna);
            }
        }
        return null;
    }

    /**
     * Devuelve un array con copias de todas las celdas del tablero.
     *
     * @return Array de celdas.
     */
    public List<Celda>/* Celda[] */ consultarCeldas() {
        // Celda[] todasLasCeldas = new Celda[TAMAÑO * TAMAÑO];
        // int indice = 0;
        List<Celda> todasLasCeldas = new ArrayList<>(TAMAÑO * TAMAÑO);
        for (int fila = 0; fila < TAMAÑO; fila++) {
            for (int columna = 0; columna < TAMAÑO; columna++) {
                todasLasCeldas.add(this.celdas.get(fila).get(columna).clonar());
            }
        }
        return todasLasCeldas;
    }

    /**
     * Devuelve el número de filas del tablero.
     *
     * @return Número de filas.
     */
    public int consultarNumeroFilas() {
        return TAMAÑO;
    }

    /**
     * Devuelve el número de columnas del tablero.
     *
     * @return Número de columnas.
     */
    public int consultarNumeroColumnas() {
        return TAMAÑO;
    }

    /**
     * Retorna una copia profunda del tablero.
     *
     * @return Clon del tablero actual.
     */
    public Tablero clonar() {
        Tablero clon = new Tablero();
        for (int fila = 0; fila < TAMAÑO; fila++) {
            for (int columna = 0; columna < TAMAÑO; columna++) {
                Celda celdaOriginal = this.celdas.get(fila).get(columna);
                Celda celdaClonada = celdaOriginal.clonar();
                clon.celdas.get(fila).set(columna, celdaClonada);
            }
        }
        return clon;
    }

    /**
     * Devuelve el estado del tablero en formato texto.
     *
     * @return Cadena que representa el tablero.
     */
    public String aTexto() {
        String resultado = "";
        for (int fila = 0; fila < TAMAÑO; fila++) {
            resultado = resultado + fila + " ";
            for (int columna = 0; columna < TAMAÑO; columna++) {
                Celda celda = celdas.get(fila).get(columna);
                if (celda.estaVacia()) {
                    resultado = resultado + "--";
                } else {
                    resultado = resultado + celda.consultarPieza().aTexto();
                }
                if (columna < TAMAÑO - 1) {
                    resultado = resultado + " ";
                }
            }
            if (fila < TAMAÑO - 1) {
                resultado = resultado + "\n";
            }
        }
        resultado = resultado + "\n  ";
        for (int columna = 0; columna < TAMAÑO; columna++) {
            resultado = resultado + columna + "  ";
        }
        return resultado;
    }

    /**
     * Genera un código hash para el tablero.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        int resultado = 17;
        for (int fila = 0; fila < TAMAÑO; fila++) {
            for (int columna = 0; columna < TAMAÑO; columna++) {
                resultado = 31 * resultado + celdas.get(fila).get(columna).hashCode();
            }
        }
        return resultado;
    }

    /**
     * Compara si dos tableros son iguales.
     *
     * @param obj Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Tablero))
            return false;
        Tablero otro = (Tablero) obj;
        for (int fila = 0; fila < TAMAÑO; fila++) {
            for (int columna = 0; columna < TAMAÑO; columna++) {
                Celda celdaThis = this.celdas.get(fila).get(columna);
                Celda celdaOtro = otro.celdas.get(fila).get(columna);
                if (!celdaThis.equals(celdaOtro)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Devuelve la representación en texto del tablero.
     *
     * @return Representación textual.
     */
    @Override
    public String toString() {
        return "Tablero{" +
                "celdas=" + celdas +
                '}';
    }
}
