package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import java.util.Date;

/**
 * Interfaz para definir el mecanismo de deshacer para el juego.
 * 
 * @since 1.0
 * @version 1.0.1
 * @autor <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 */
public interface MecanismoDeDeshacer {

    /**
     * Recupera una copia profunda del árbitro actual.
     * 
     * @return El árbitro en su estado actual.
     */
    public Arbitro consultarArbitroActual();

    /**
     * Consulta cuantas jugadas se han realizado cuando se llama.
     * 
     * @return El número de jugadas en el histórico.
     */
    public int consultarNumeroJugadasEnHistorico();

    /**
     * Deshace la última jugada grabada en el histórico.
     * 
     */
    public void deshacerJugada();

    /**
     * Realiza una jugada y la añade al histórico.
     * 
     * @param jugada La jugada a realizar.
     */
    public void hacerJugada(Jugada jugada);

    /**
     * Obtiene la fecha, destinada a registrar cuando se deshace una jugada.
     * 
     * @return La fecha de inicio.
     */
    public Date obtenerFechaInicio();

    public void reiniciar();
}