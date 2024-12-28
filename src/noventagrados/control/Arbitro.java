package noventagrados.control;

import noventagrados.modelo.Tablero;

import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import noventagrados.control.excepcion.TableroIncorrectoException;
import noventagrados.control.excepcion.TamañoIncorrectoException;
import noventagrados.modelo.Celda;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Pieza;
import noventagrados.util.Color;
import noventagrados.util.Coordenada;
import noventagrados.util.Sentido;
import noventagrados.util.TipoPieza;

/**
 * Clase que gestiona el estado del juego, validación de movimientos y
 * condiciones de victoria.
 *
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 * @since 1.0
 * @version 1.0
 * 
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
     * Constructor.
     * Inicializa la partida con el tablero y contador de jugadas a cero.
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
     * Crea un clon profundo del árbitro actual.
     *
     * @return Un nuevo objeto Arbitro que es una copia del actual.
     */
    public Arbitro clonar() {
        Tablero tableroClonado = this.tablero.clonar();
        Arbitro arbitroClonado = new Arbitro(tableroClonado);
        arbitroClonado.turnoActual = this.turnoActual;
        arbitroClonado.turnoGanador = this.turnoGanador;
        arbitroClonado.numeroJugada = this.numeroJugada;

        // Copiar piezas de las cajas originales a las nuevas
        for (Pieza pieza : this.cajaPiezasBlancas.consultarPiezas()) {
            arbitroClonado.cajaPiezasBlancas.añadir(pieza);
        }
        for (Pieza pieza : this.cajaPiezasNegras.consultarPiezas()) {
            arbitroClonado.cajaPiezasNegras.añadir(pieza);
        }
        return arbitroClonado;
    }

    /**
     * Cambia el turno al otro contrincante si la partida no está finalizada y hay
     * un turno actual asignado.
     */
    public void cambiarTurno() {
        turnoActual = (turnoActual == Color.BLANCO) ? Color.NEGRO : Color.BLANCO;
    }

    /**
     * Coloca piezas en el tablero según las listas proporcionadas e inicializa el
     * turno.
     *
     * @param piezas      Lista de piezas a colocar (no nula)
     * @param coordenadas Lista de coordenadas donde colocar las piezas (no nula)
     * @param turnoActual Color del turno inicial (no nulo)
     */
    public void colocarPiezas(List<Pieza> piezas, List<Coordenada> coordenadas, Color turnoActual)
            throws TamañoIncorrectoException {
        if (piezas.size() != coordenadas.size()) {
            throw new TamañoIncorrectoException("Las listas, piezas y coordenadas, no tienen el mismo tamñaño");
        }
        for (int i = 0; i < piezas.size(); i++) {
            Pieza pieza = piezas.get(i);
            Coordenada coordenada = coordenadas.get(i);
            tablero.colocar(pieza, coordenada);
        }

        this.turnoActual = turnoActual;
    }

    /**
     * Coloca las piezas de manera que se pueda empezar una partida según las
     * reglas.
     * para el atacante (blancas).
     */
    public void colocarPiezasConfiguracionInicial() {

        // Colocar las piezas blancas
        tablero.colocar(new Pieza(TipoPieza.REINA, Color.BLANCO), new Coordenada(0, 0));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.BLANCO), new Coordenada(0, 1));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.BLANCO), new Coordenada(0, 2));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.BLANCO), new Coordenada(0, 3));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.BLANCO), new Coordenada(1, 0));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.BLANCO), new Coordenada(2, 0));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.BLANCO), new Coordenada(3, 0));

        // Colocar las piezas negras
        tablero.colocar(new Pieza(TipoPieza.REINA, Color.NEGRO), new Coordenada(6, 6));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.NEGRO), new Coordenada(6, 3));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.NEGRO), new Coordenada(6, 4));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.NEGRO), new Coordenada(6, 5));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.NEGRO), new Coordenada(5, 6));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.NEGRO), new Coordenada(4, 6));
        tablero.colocar(new Pieza(TipoPieza.PEON, Color.NEGRO), new Coordenada(3, 6));

        // Inicializar el turno para el atacante (blancas)
        this.turnoActual = Color.BLANCO;

        // Iniciar el contador de jugadas y el ganador
        this.numeroJugada = 0;
        this.turnoGanador = null;
    }

    /**
     * Devuelve la caja que contiene las piezas expulsadas del color indicado.
     *
     * @param color Color de la caja a consultar (no nulo).
     * @return La caja correspondiente.
     */
    public Caja consultarCaja(Color color) throws IllegalArgumentException {
        if (color == null) {
            throw new IllegalArgumentException();
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
     */
    public void empujar(Jugada jugada) throws TableroIncorrectoException {
        TableroConsultor<Tablero> consultor = new TableroConsultor<>(tablero);

        // Obtener sentido del movimiento
        Coordenada origen = jugada.origen().consultarCoordenada();
        Coordenada destino = jugada.destino().consultarCoordenada();
        Sentido sentido = consultor.calcularSentido(origen, destino);

        // Reubicar las piezas para similar el empuje
        reubicador(origen, sentido, destino);

        // Incrementar el número de jugadas
        numeroJugada++;
    }

    /**
     * Método auxiliar para empujar las piezas en una dirección.
     *
     * @param origen  Coordenada de origen.
     * @param sentido Sentido del movimiento.
     * @param destino Coordenada de destino.
     */
    private void reubicador(Coordenada origen, Sentido sentido, Coordenada destino) throws TableroIncorrectoException {
        Coordenada actual = origen;
        // Recolectar las piezas que hay que empujar y eliminarlas del tablero
        List<Pieza> listaPiezasAReubicar = Recolector(actual, destino, sentido);

        // Reubicar piezas las piezas recolectadas
        Coordenada posicion = destino;
        for (Pieza pieza : listaPiezasAReubicar) {
            if (posicion != null) {
                Celda celda = tablero.consultarCelda(posicion);
                if (!celda.estaVacia()) {
                    meterPiezaEnSuCaja(celda.consultarPieza());
                }
                tablero.colocar(pieza, posicion);
                posicion = consultarCoordenadaSiguienteAActualEnSentidoDeterminado(posicion, sentido);
            } else {
                meterPiezaEnSuCaja(pieza);
            }
        }
        estaFinalizadaPartida();

    }

    private List<Pieza> Recolector(Coordenada actual, Coordenada destino, Sentido sentido) {
        List<Pieza> listaPiezasAReubicar = new ArrayList<>();
        do {
            Celda celda = tablero.consultarCelda(actual);
            if (!celda.estaVacia()) {
                listaPiezasAReubicar.add(celda.consultarPieza());
                tablero.eliminarPieza(actual);
            }
            actual = actual.equals(destino) ? null
                    : consultarCoordenadaSiguienteAActualEnSentidoDeterminado(actual, sentido);

        } while (actual != null);

        // Recolectar piezas oportunas más allá del destino.
        Celda celdaTrasDestino = tablero
                .consultarCelda(consultarCoordenadaSiguienteAActualEnSentidoDeterminado(destino, sentido));
        if (destino != null && !tablero.consultarCelda(destino).estaVacia() && celdaTrasDestino != null
                && !celdaTrasDestino.estaVacia()) {
            // asignamos a celda local la copia de la celda siguiente al destino
            Celda celda = tablero
                    .consultarCelda(consultarCoordenadaSiguienteAActualEnSentidoDeterminado(destino, sentido));
            // asignamos a actual la coordenada siguiente al destino
            actual = consultarCoordenadaSiguienteAActualEnSentidoDeterminado(destino, sentido);
            // mientras la celda no esté vacía o la coordenada siguiente no sea nula
            // seguimos metiendo piezas al array
            do {
                if (!celda.estaVacia()) {
                    listaPiezasAReubicar.add(celda.consultarPieza());
                    tablero.eliminarPieza(consultarCoordenadaSiguienteAActualEnSentidoDeterminado(actual, sentido));
                    celda = tablero
                            .consultarCelda(consultarCoordenadaSiguienteAActualEnSentidoDeterminado(actual, sentido));
                    actual = consultarCoordenadaSiguienteAActualEnSentidoDeterminado(actual, sentido);
                }
            } while (actual != null && !celda.estaVacia());
        }
        return listaPiezasAReubicar;
    }

    /**
     * Expulsa la pieza en la coordenada indicada y la añade a la caja
     * correspondiente con el método añadir de la clase caja.
     *
     * @param pieza Pieza a expulsar.
     */
    private void meterPiezaEnSuCaja(Pieza pieza) {
        Caja caja = pieza.consultarColor().equals(Color.BLANCO) ? cajaPiezasBlancas : cajaPiezasNegras;
        caja.añadir(pieza);
    }

    /**
     * Método auxiliar privado para obtener la coordenada siguiente a la
     * proporcionada en una dirección y sentido determinados.
     *
     * @param coordenada Coordenada actual.
     * @param sentido    Sentido del movimiento.
     * @return La siguiente coordenada en la dirección dada, o null si está fuera
     *         del tablero.
     */
    private Coordenada consultarCoordenadaSiguienteAActualEnSentidoDeterminado(Coordenada coordenada, Sentido sentido) {
        int fila = coordenada.fila() + sentido.consultarDesplazamientoEnFilas();
        int columna = coordenada.columna() + sentido.consultarDesplazamientoEnColumnas();

        if (fila >= 0 && fila < tablero.consultarNumeroFilas() && columna >= 0
                && columna < tablero.consultarNumeroColumnas()) {
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
     */
    public boolean esMovimientoLegal(Jugada jugada) throws TableroIncorrectoException {
        if (jugada == null) {
            return false;
        }

        // Verificar que la partida no ha terminado
        boolean cumpleRegla = !estaFinalizadaPartida();

        if (cumpleRegla) {
            TableroConsultor<Tablero> consultor = new TableroConsultor<>(tablero);
            Coordenada origen = jugada.origen().consultarCoordenada();
            Coordenada destino = jugada.destino().consultarCoordenada();
            // Las coordenadas, origen y destino, están en el tablero y son distintas
            cumpleRegla = estaCoordenadaEnTablero(origen) && estaCoordenadaEnTablero(destino)
                    && !origen.equals(destino);

            if (cumpleRegla) {
                Pieza piezaOrigen = tablero.consultarCelda(origen).consultarPieza();
                // Hay pieza en el origen y es del color del turno actual
                cumpleRegla = piezaOrigen != null && piezaOrigen.consultarColor() == turnoActual;

                if (cumpleRegla) {
                    int piezasHorizontal = consultor.consultarNumeroPiezasEnHorizontal(origen);
                    int piezasVertical = consultor.consultarNumeroPiezasEnVertical(origen);
                    int distanciaHorizontal = Math.abs(destino.columna() - origen.columna());
                    int distanciaVertical = Math.abs(destino.fila() - origen.fila());
                    // La distancia horizontal o vertical es igual al número de piezas en la
                    // perpendicular
                    cumpleRegla = ((distanciaVertical == piezasHorizontal) ||
                            (distanciaHorizontal == piezasVertical)) &&
                            consultor.calcularSentido(origen, destino) != null;
                }
            }
        }

        return cumpleRegla;
    }

    /**
     * Comprueba si una coordenada está dentro del tablero.
     *
     * @param coordenada Coordenada a verificar.
     * @return true si es válida, false en caso contrario.
     */
    private boolean estaCoordenadaEnTablero(Coordenada coordenada) {
        return coordenada.fila() >= 0 && coordenada.fila() < tablero.consultarNumeroFilas() &&
                coordenada.columna() >= 0 && coordenada.columna() < tablero.consultarNumeroColumnas();
    }

    /**
     * Comprueba si la partida ha finalizado.
     *
     * @return true si la partida ha finalizado, false en caso contrario.
     */
    public boolean estaFinalizadaPartida() throws TableroIncorrectoException {
        TableroConsultor<Tablero> consultor = new TableroConsultor<>(tablero);
        boolean finalizada = false;

        if (consultor.estaReinaEnElCentro(Color.BLANCO)) {
            turnoGanador = Color.BLANCO;
            finalizada = true;
        }

        if (consultor.estaReinaEnElCentro(Color.NEGRO)) {
            turnoGanador = Color.NEGRO;
            finalizada = true;
        }

        if (!consultor.hayReina(Color.BLANCO) && !consultor.hayReina(Color.NEGRO)) {
            turnoGanador = null;
            finalizada = true;
        } else {
            if (!consultor.hayReina(Color.BLANCO)) {
                turnoGanador = Color.NEGRO;
                finalizada = true;
            }
            if (!consultor.hayReina(Color.NEGRO)) {
                turnoGanador = Color.BLANCO;
                finalizada = true;
            }
        }
        return finalizada;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cajaPiezasBlancas, cajaPiezasNegras, numeroJugada, tablero, turnoActual, turnoGanador);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Arbitro other = (Arbitro) obj;
        return Objects.equals(cajaPiezasBlancas, other.cajaPiezasBlancas)
                && Objects.equals(cajaPiezasNegras, other.cajaPiezasNegras) && numeroJugada == other.numeroJugada
                && Objects.equals(tablero, other.tablero) && turnoActual == other.turnoActual
                && turnoGanador == other.turnoGanador;
    }

    @Override
    public String toString() {
        return "Arbitro [tablero=" + tablero + ", cajaPiezasBlancas=" + cajaPiezasBlancas + ", cajaPiezasNegras="
                + cajaPiezasNegras + ", turnoActual=" + turnoActual + ", turnoGanador=" + turnoGanador
                + ", numeroJugada=" + numeroJugada + "]";
    }
}
