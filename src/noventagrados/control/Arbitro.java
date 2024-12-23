package noventagrados.control;

import noventagrados.modelo.Tablero;

import java.util.Objects;

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
     * Cambia el turno al otro contrincante si la partida no está finalizada
     * y hay un turno actual asignado.
     */
    public void cambiarTurno() {
        turnoActual = (turnoActual == Color.BLANCO) ? Color.NEGRO : Color.BLANCO;
    }

    /**
     * Coloca piezas en el tablero según los arrays proporcionados e inicializa el
     * turno.
     * 
     * @param piezas      Array de piezas a colocar (no nulo)
     * @param coordenadas Array de coordenadas donde colocar las piezas (no nulo)
     * @param turnoActual Color del turno inicial (no nulo)
     *                    tienen diferente longitud
     */
    public void colocarPiezas(Pieza[] piezas, Coordenada[] coordenadas, Color turnoActual) {

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
     */
    public Caja consultarCaja(Color color) {

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
    public void empujar(Jugada jugada) {
        TableroConsultor consultor = new TableroConsultor(tablero);

        // Obtener sentido del movimiento
        Coordenada origen = jugada.origen().consultarCoordenada();
        Coordenada destino = jugada.destino().consultarCoordenada();
        Sentido sentido = consultor.calcularSentido(origen, destino);

        // Reubicar las piezas para similar el empuje
        Reubicador(origen, sentido, destino);

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
    private void Reubicador(Coordenada origen, Sentido sentido, Coordenada destino) {
        Coordenada actual = origen;
        // Recolectar las piezas que hay que empujar y eliminarlas del tablero
        Pieza[] listaPiezasAReubicar = Recolector(actual, destino, sentido);

        // Reubicar piezas las piezas recolectadas
        Coordenada posicion = destino;
        for (int i = 0; i < listaPiezasAReubicar.length; i++) {
            Pieza pieza = listaPiezasAReubicar[i];
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

    private Pieza[] Recolector(Coordenada actual, Coordenada destino, Sentido sentido) {
        Pieza[] listaPiezasAReubicar = new Pieza[tablero.consultarNumeroFilas() * tablero.consultarNumeroColumnas()];
        int numPiezas = 0;

        do {
            Celda celda = tablero.consultarCelda(actual);
            if (!celda.estaVacia()) {
                listaPiezasAReubicar[numPiezas++] = celda.consultarPieza();
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
                    listaPiezasAReubicar[numPiezas++] = celda.consultarPieza();
                    tablero.eliminarPieza(consultarCoordenadaSiguienteAActualEnSentidoDeterminado(actual, sentido));
                    celda = tablero
                            .consultarCelda(consultarCoordenadaSiguienteAActualEnSentidoDeterminado(actual, sentido));
                    actual = consultarCoordenadaSiguienteAActualEnSentidoDeterminado(actual, sentido);
                }
            } while (actual != null && !celda.estaVacia());
        }

        Pieza[] resultado = new Pieza[numPiezas];
        System.arraycopy(listaPiezasAReubicar, 0, resultado, 0, numPiezas);
        return resultado;
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
    public boolean esMovimientoLegal(Jugada jugada) {
        if (jugada == null) {
            return false;
        }

        // Verificar que la partida no ha terminado
        boolean cumpleRegla = !estaFinalizadaPartida();

        if (cumpleRegla) {
            TableroConsultor consultor = new TableroConsultor(tablero);
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
    public boolean estaFinalizadaPartida() {
        TableroConsultor consultor = new TableroConsultor(tablero);
        boolean finalizada = false;

        // Verificar si la reina blanca ha alcanzado el centro
        if (consultor.estaReinaEnElCentro(Color.BLANCO)) {
            turnoGanador = Color.BLANCO;
            finalizada = true;
        }

        // Verificar si la reina negra ha alcanzado el centro
        if (consultor.estaReinaEnElCentro(Color.NEGRO)) {
            turnoGanador = Color.NEGRO;
            finalizada = true;
        }

        // Verificar si ambas reinas han sido expulsadas del tablero

        if (!consultor.hayReina(Color.BLANCO) && !consultor.hayReina(Color.NEGRO)) {
            turnoGanador = null;
            finalizada = true; // Empate
        } else {
            // Verificar si la reina blanca ha sido expulsada del tablero
            if (!consultor.hayReina(Color.BLANCO)) {
                turnoGanador = Color.NEGRO;
                finalizada = true;
            }

            // Verificar si la reina negra ha sido expulsada del tablero
            if (!consultor.hayReina(Color.NEGRO)) {
                turnoGanador = Color.BLANCO;
                finalizada = true;
            }
            ;
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
