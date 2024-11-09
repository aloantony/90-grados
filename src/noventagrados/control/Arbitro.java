package noventagrados.control;

import noventagrados.modelo.Tablero;
import noventagrados.modelo.Celda;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Pieza;
import noventagrados.util.Color;
import noventagrados.util.Coordenada;
import noventagrados.util.Sentido;
import noventagrados.util.TipoPieza;

/**
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 *         Clase que representa el árbitro del juego Noventa Grados.
 *         Gestiona el estado del juego, validación de movimientos y condiciones
 *         de victoria.
 */
public class Arbitro {

    // Atributos privados
    private final Tablero tablero;
    private final Caja cajaPiezasBlancas;
    private final Caja cajaPiezasNegras;
    private Color turnoActual;
    private Color turnoGanador;
    private int numeroJugada;

    /**
     * Constructor de la clase Árbitro.
     * Inicializa la partida con el tablero proporcionado y contador de jugadas a
     * cero.
     * Inicialmente no hay turno asignado.
     *
     * @param tablero Tablero inicial del juego (no nulo).
     */

    public Arbitro(Tablero tablero) {
        this.tablero = tablero;
        this.cajaPiezasBlancas = new Caja(Color.BLANCO);
        this.cajaPiezasNegras = new Caja(Color.NEGRO);
        this.turnoActual = null;
        this.turnoGanador = null;
        this.numeroJugada = 0;
    }

    /**
     * Cambia el turno al otro contrincante.
     */
    public void cambiarTurno() {
        if (turnoActual == Color.BLANCO) {
            turnoActual = Color.NEGRO;
        } else if (turnoActual == Color.NEGRO) {
            turnoActual = Color.BLANCO;
        }
    }

    /**
     * Coloca piezas en el tablero según los arrays proporcionados e inicializa el
     * turno.
     * 
     * @param piezas      Array de piezas a colocar (no nulo)
     * @param coordenadas Array de coordenadas donde colocar las piezas (no nulo)
     * @param turnoActual Color del turno inicial (no nulo)
     * @throws IllegalArgumentException Si algún parámetro es nulo o los arrays
     *                                  tienen diferente longitud
     */
    public void colocarPiezas(Pieza[] piezas, Coordenada[] coordenadas, Color turnoActual) {
        if (piezas == null || coordenadas == null || turnoActual == null) {
            throw new IllegalArgumentException("Los parámetros no pueden ser nulos.");
        }
        if (piezas.length != coordenadas.length) {
            throw new IllegalArgumentException("Los arrays de piezas y coordenadas deben tener la misma longitud.");
        }

        // Colocar las piezas en las coordenadas especificadas
        for (int i = 0; i < piezas.length; i++) {
            Pieza pieza = piezas[i];
            Coordenada coordenada = coordenadas[i];
            tablero.colocar(pieza, coordenada);
        }

        // Establecer el turno inicial
        this.turnoActual = turnoActual;
    }

    /**
     * Coloca las piezas en la configuración inicial del juego e inicializa el turno
     * para el atacante (blancas).
     */
    public void colocarPiezasConfiguracionInicial() {
        // Limpiar el tablero
        for (int fila = 0; fila < tablero.consultarNumeroFilas(); fila++) {
            for (int columna = 0; columna < tablero.consultarNumeroColumnas(); columna++) {
                tablero.eliminarPieza(new Coordenada(fila, columna));
            }
        }

        // Colocar las piezas blancas
        // Reina blanca en (0,0)
        tablero.colocar(new Pieza(TipoPieza.REINA, Color.BLANCO), new Coordenada(0, 0));
        // Peones blancos
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.BLANCO), new Coordenada(0, 1));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.BLANCO), new Coordenada(0, 2));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.BLANCO), new Coordenada(0, 3));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.BLANCO), new Coordenada(1, 0));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.BLANCO), new Coordenada(2, 0));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.BLANCO), new Coordenada(3, 0));

        // Colocar las piezas negras
        // Reina negra en (6,6)
        tablero.colocar(new Pieza(TipoPieza.REINA, Color.NEGRO), new Coordenada(6, 6));
        // Peones negros
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.NEGRO), new Coordenada(6, 3));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.NEGRO), new Coordenada(6, 4));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.NEGRO), new Coordenada(6, 5));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.NEGRO), new Coordenada(5, 6));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.NEGRO), new Coordenada(4, 6));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.NEGRO), new Coordenada(3, 6));

        // Inicializar el turno para el atacante (blancas)
        this.turnoActual = Color.BLANCO;

        // Reiniciar el contador de jugadas y el ganador
        this.numeroJugada = 0;
        this.turnoGanador = null;
    }

    /**
     * Devuelve la caja que contiene las piezas expulsadas del color indicado.
     *
     * @param color Color de la caja a consultar (no nulo).
     * @return La caja correspondiente.
     * @throws IllegalArgumentException si el color es nulo.
     */
    public Caja consultarCaja(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("El color no puede ser nulo.");
        }
        if (color == Color.BLANCO) {
            return cajaPiezasBlancas.clonar();
        } else {
            return cajaPiezasNegras.clonar();
        }
    }

    /**
     * Devuelve el número de jugadas realizadas hasta el momento.
     *
     * @return Número de jugadas.
     */
    public int consultarNumeroJugada() {
        return numeroJugada;
    }

    /**
     * Devuelve un clon profundo del tablero actual.
     *
     * @return Clon del tablero.
     */
    public Tablero consultarTablero() {
        return tablero.clonar();
    }

    /**
     * Devuelve el turno actual.
     *
     * @return Color del turno actual.
     */
    public Color consultarTurno() {
        return turnoActual;
    }

    /**
     * Devuelve el turno del ganador actual o null si no hay ganador.
     *
     * @return Color del ganador o null.
     */
    public Color consultarTurnoGanador() {
        return turnoGanador;
    }

    /**
     * Desplaza la pieza en origen hasta destino, empujando las piezas en su camino.
     * Incrementa el número de jugadas.
     * Si se expulsan piezas, se añaden a la caja correspondiente.
     * Se asume que la jugada es legal.
     *
     * @param jugada Jugada a realizar (no nula).
     * @throws IllegalArgumentException si la jugada es nula.
     */
    public void empujar(Jugada jugada) {
        if (jugada == null) {
            throw new IllegalArgumentException("La jugada no puede ser nula.");
        }

        // Instanciar un TableroConsultor para esta operación
        TableroConsultor consultor = new TableroConsultor(tablero);

        // Obtener sentido del movimiento
        Coordenada origen = jugada.origen().consultarCoordenada();
        Coordenada destino = jugada.destino().consultarCoordenada();
        Sentido sentido = consultor.calcularSentido(origen, destino);

        // Realizar el empuje de las piezas
        empujarPiezas(origen, sentido);

        // Incrementar el número de jugadas
        numeroJugada++;

        // Comprobar si la partida ha finalizado
        if (!estaFinalizadaPartida()) {
            cambiarTurno();
        }
    }

    /**
     * Método auxiliar para empujar las piezas en una dirección.
     *
     * @param origen  Coordenada de origen.
     * @param sentido Sentido del movimiento.
     */
    private void empujarPiezas(Coordenada origen, Sentido sentido) {
        Coordenada actual = origen;
        Pieza piezaMovida = tablero.obtenerCelda(actual).consultarPieza();
        tablero.obtenerCelda(actual).eliminarPieza();

        while (true) {
            Coordenada siguiente = obtenerCoordenadaEnDireccion(actual, sentido);

            // Si llegamos al borde del tablero
            if (siguiente == null) {
                // La pieza actual se expulsa
                if (piezaMovida != null) {
                    añadirPiezaACaja(piezaMovida);
                }
                break;
            }

            Celda celdaSiguiente = tablero.obtenerCelda(siguiente);
            Pieza piezaEnSiguiente = celdaSiguiente.consultarPieza();

            if (piezaEnSiguiente == null) {
                // Si la siguiente celda está vacía, colocamos la pieza y terminamos
                celdaSiguiente.colocar(piezaMovida);
                break;
            } else {
                // Si la siguiente celda está ocupada, empujamos la pieza
                celdaSiguiente.eliminarPieza();
                celdaSiguiente.colocar(piezaMovida);
                piezaMovida = piezaEnSiguiente;
                actual = siguiente;
            }
        }
    }

    /**
     * Añade una pieza a la caja correspondiente según su color.
     *
     * @param pieza Pieza a añadir.
     */
    private void añadirPiezaACaja(Pieza pieza) {
        if (pieza.consultarColor() == Color.BLANCO) {
            cajaPiezasBlancas.añadir(pieza);
        } else {
            cajaPiezasNegras.añadir(pieza);
        }
    }

    /**
     * Método auxiliar para obtener la coordenada siguiente en una dirección dada.
     *
     * @param coordenada Coordenada actual.
     * @param sentido    Sentido del movimiento.
     * @return La siguiente coordenada en la dirección dada, o null si está fuera
     *         del tablero.
     */
    private Coordenada obtenerCoordenadaEnDireccion(Coordenada coordenada, Sentido sentido) {
        int fila = coordenada.fila() + sentido.consultarDesplazamientoEnFilas();
        int columna = coordenada.columna() + sentido.consultarDesplazamientoEnColumnas();

        if (fila >= 0 && fila < tablero.consultarNumeroFilas()
                && columna >= 0 && columna < tablero.consultarNumeroColumnas()) {
            return new Coordenada(fila, columna);
        } else {
            return null;
        }

    }

    /**
     * Comprueba la legalidad de la jugada según las reglas del juego.
     *
     * @param jugada Jugada a validar (no nula).
     * @return true si la jugada es legal, false en caso contrario.
     * @throws IllegalArgumentException si la jugada es nula.
     */
    public boolean esMovimientoLegal(Jugada jugada) {
        if (jugada == null) {
            return false;
        }
        boolean esLegal = !estaFinalizadaPartida();
        if (esLegal) {
            TableroConsultor consultor = new TableroConsultor(tablero);
            Coordenada origen = jugada.origen().consultarCoordenada();
            Coordenada destino = jugada.destino().consultarCoordenada();
            esLegal = esCoordenadaValida(origen) && esCoordenadaValida(destino) && !origen.equals(destino);
            if (esLegal) {
                Pieza piezaOrigen = tablero.obtenerCelda(origen).consultarPieza();
                esLegal = piezaOrigen != null && piezaOrigen.consultarColor() == turnoActual;
                if (esLegal) {
                    int piezasHorizontal = consultor.consultarNumeroPiezasEnHorizontal(origen);
                    int piezasVertical = consultor.consultarNumeroPiezasEnVertical(origen);
                    boolean esBlanca = piezaOrigen.consultarColor() == Color.BLANCO;
                    esLegal = !(esBlanca && (destino.fila() < origen.fila() || destino.columna() < origen.columna())) &&
                            !(!esBlanca && (destino.fila() > origen.fila() || destino.columna() > origen.columna()));
                    if (esLegal) {
                        int distanciaHorizontal = Math.abs(destino.columna() - origen.columna());
                        int distanciaVertical = Math.abs(destino.fila() - origen.fila());
                        esLegal = ((distanciaVertical == piezasHorizontal) ||
                                (distanciaHorizontal == piezasVertical)) &&
                                consultor.calcularSentido(origen, destino) != null;
                    }
                }
            }
        }
        return esLegal;
    }

    private boolean esCoordenadaValida(Coordenada coord) {
        return coord.fila() >= 0 && coord.fila() < tablero.consultarNumeroFilas() &&
                coord.columna() >= 0 && coord.columna() < tablero.consultarNumeroColumnas();
    }

    /**
     * Comprueba si se cumple alguna de las condiciones de finalización del juego.
     *
     * @return true si la partida ha finalizado, false en caso contrario.
     */
    public boolean estaFinalizadaPartida() {
        // Instanciar un TableroConsultor para esta operación
        TableroConsultor consultor = new TableroConsultor(tablero);

        // Condición 1: La reina es expulsada del tablero
        boolean reinaExpulsada = !consultor.hayReina(Color.BLANCO);
        if (reinaExpulsada) {
            turnoGanador = Color.NEGRO;
            return true;
        }
        // Condición 2: La reina ocupa la celda central
        boolean reinaEnCentro = consultor.estaReinaEnElCentro(Color.BLANCO);
        if (reinaEnCentro) {
            turnoGanador = Color.BLANCO;
            return true;
        }
        // Condición 3: Un bando no tiene más piezas
        boolean sinPiezasBlancas = consultor.consultarNumeroPiezas(TipoPieza.PEON, Color.BLANCO) == 0
                && !consultor.hayReina(Color.BLANCO);
        boolean sinPiezasNegras = consultor.consultarNumeroPiezas(TipoPieza.PEON, Color.NEGRO) == 0;
        if (sinPiezasBlancas) {
            turnoGanador = Color.NEGRO;
            return true;
        }
        if (sinPiezasNegras) {
            turnoGanador = Color.BLANCO;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Arbitro [tablero=" + tablero + ", cajaPiezasBlancas=" + cajaPiezasBlancas + ", cajaPiezasNegras="
                + cajaPiezasNegras + ", turnoActual=" + turnoActual + ", turnoGanador=" + turnoGanador
                + ", numeroJugada=" + numeroJugada + "]";
    }
}
