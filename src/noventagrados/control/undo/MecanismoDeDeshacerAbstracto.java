package noventagrados.control.undo;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase abstracta que implementa el mecanismo de deshacer para el juego.
 * 
 * @since 1.0
 * @version 1.0.1
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 * @param <Tipo> El tipo de objeto que se va a deshacer.
 */

public abstract class MecanismoDeDeshacerAbstracto<Tipo> implements MecanismoDeDeshacer {
    /**
     * La fecha en que se inició el mecanismo de deshacer.
     */
    public Date fechaInicio;

    /**
     * Lista que almacena el historial de acciones que pueden ser deshechas.
     */
    public List<Tipo> historico;

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
     * Consulta el número de jugadas en el historial.
     * 
     * @return El número de jugadas en el historial.
     */
    public int consultarNumeroJugadasEnHistorico() {
        return historico.size();
    }

    /**
     * Obtiene la fecha de inicio del mecanismo de deshacer.
     * 
     * @return La fecha de inicio.
     */
    public Date obtenerFechaInicio() {
        return fechaInicio;
    }

    public void reiniciar() {
        historico.clear();
    }
}
