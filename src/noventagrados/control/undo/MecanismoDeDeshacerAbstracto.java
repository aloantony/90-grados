package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Tablero;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public abstract class MecanismoDeDeshacerAbstracto<Tipo> implements MecanismoDeDeshacer {

    Date fechaInicio;
    List<Tipo> historico;

    /*
     * Constructor de la Máquina del tiempo
     */
    public MecanismoDeDeshacerAbstracto(Date fechaInicio) {
        this.historico = new ArrayList<>();
        this.fechaInicio = fechaInicio;

    }

    public abstract Arbitro consultarArbitroActual();

    public int consultarNumeroJugadasEnHistorico() {
        return historico.size();
    }

    public abstract void hacerJugada(Jugada jugada);

    public abstract void deshacerJugada();

    public Date obtenerFechaInicio() {
        return fechaInicio;
    }
}