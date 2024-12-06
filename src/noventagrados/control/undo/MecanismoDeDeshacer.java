package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import java.util.Date;

/**
 * Interfaz que define el mecanismo de deshacer para el juego.
 * 
 * @since 1.0
 * @version 1.0.1
 * @autor <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 */
public interface MecanismoDeDeshacer {

    /**
     * Consulta el estado actual del árbitro.
     * 
     * @return El árbitro en su estado actual.
     */
    public Arbitro consultarArbitroActual();

    /**
     * Consulta el número de jugadas en el historial.
     * 
     * @return El número de jugadas en el historial.
     */
    public int consultarNumeroJugadasEnHistorico();

    /**
     * Deshace la última jugada realizada.
     */
    public void deshacerJugada();

    /**
     * Realiza una jugada, añadiéndola al historial.
     * 
     * @param jugada La jugada a realizar.
     */
    public void hacerJugada(Jugada jugada);

    /**
     * Obtiene la fecha de inicio del mecanismo de deshacer.
     * 
     * @return La fecha de inicio.
     */
    public Date obtenerFechaInicio();
}