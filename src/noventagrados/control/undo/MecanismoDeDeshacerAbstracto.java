package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase abstracta que implementa el mecanismo de deshacer para el juego.
 * 
 * @since 1.0
 * @version 1.0.1
 * @autor <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 */
public abstract class MecanismoDeDeshacerAbstracto<Tipo> implements MecanismoDeDeshacer {

    protected Date fechaInicio;
    protected List<Tipo> historico;

    /**
     * Constructor de la Máquina del tiempo.
     * 
     * @param fechaInicio La fecha de inicio del mecanismo de deshacer.
     */
    public MecanismoDeDeshacerAbstracto(Date fechaInicio) {
        this.historico = new ArrayList<>();
        this.fechaInicio = fechaInicio;
    }

    /**
     * Consulta el estado actual del árbitro.
     * 
     * @return El árbitro en su estado actual.
     */
    public abstract Arbitro consultarArbitroActual();

    /**
     * Consulta el número de jugadas en el historial.
     * 
     * @return El número de jugadas en el historial.
     */
    public int consultarNumeroJugadasEnHistorico() {
        return historico.size();
    }

    /**
     * Realiza una jugada, añadiéndola al historial.
     * 
     * @param jugada La jugada a realizar.
     */
    public abstract void hacerJugada(Jugada jugada);

    /**
     * Deshace la última jugada realizada.
     */
    public abstract void deshacerJugada();

    /**
     * Obtiene la fecha de inicio del mecanismo de deshacer.
     * 
     * @return La fecha de inicio.
     */
    public Date obtenerFechaInicio() {
        return fechaInicio;
    }
}