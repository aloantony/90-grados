package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public abstract class MecanismoDeDeshacerAbstracto<Tipo> implements MecanismoDeDeshacer {

    Arbitro arbitro;
    int numeroJugadasEnHistorico;
    Date fechaInicio;
    List<Tipo> historico;
    Jugada jugada;

    /*
     * Constructor de la Máquina del tiempo
     */
    public MecanismoDeDeshacerAbstracto(Date fechaInicio) {
        this.historico = new ArrayList<>();
        this.numeroJugadasEnHistorico = 0; // A 0 al crear la instancia
        this.fechaInicio = fechaInicio;
        this.arbitro = consultarArbitroActual();

    }

    public Arbitro consultarArbitroActual() {
        return arbitro.clonar();
    }

    public int consultarNumeroJugadasEnHistorico() {
        numeroJugadasEnHistorico = historico.size();
        return numeroJugadasEnHistorico;
    }

    public abstract void hacerJugada(Jugada jugada);

    public abstract void deshacerJugada();

    public Date obtenerFechaInicio() {
        return fechaInicio;
    }
}