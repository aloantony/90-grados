package noventagrados.control.undo;

import noventagrados.control.Arbitro;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public abstract class MecanismoDeDeshacerAbstracto<Tipo> implements MecanismoDeDeshacer {

    Arbitro arbitroActual;
    int numeroJugadasEnHistorico;
    Date fechaInicio;
    List<Tipo> historico;

    /*
     * Constructor de la Máquina del tiempo
     */
    public MecanismoDeDeshacerAbstracto(Arbitro arbitroActual) {
        this.historico = new ArrayList<>();
        this.arbitroActual = arbitroActual.clonar();
        this.numeroJugadasEnHistorico = 0; // A 0 al crear la instancia
        this.fechaInicio = new Date();

    }

    public Arbitro consultarArbitroActual() {
        return arbitroActual.clonar();
    }

    public int consultarNumeroJugadasEnHistorico() {
        return numeroJugadasEnHistorico;
    }

    public void hacerJugada() {
        numeroJugadasEnHistorico = historico.size();
    }

    public void deshacerJugada() {
        historico.remove(historico.size() - 1);
        numeroJugadasEnHistorico = historico.size();
        fechaInicio = new Date();
    }

    public Date obtenerFechaInicio() {
        return fechaInicio;
    }
}