package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Tablero;
import java.util.Date;

public class MaquinaDelTiempoConJugadas extends MecanismoDeDeshacerAbstracto<Jugada> {

    public MaquinaDelTiempoConJugadas(Date fechaInicio) {
        super(fechaInicio);
    }

    @Override
    public void hacerJugada(Jugada jugada) {
        historico.add(jugada);
        numeroJugadasEnHistorico = historico.size();
    }

    @Override
    public void deshacerJugada() {
        if (!historico.isEmpty()) {
            historico.remove(historico.size() - 1);
            numeroJugadasEnHistorico = historico.size();
            fechaInicio = new Date();
            arbitro = consultarArbitroActual();
        }
    }

    @Override
    public Arbitro consultarArbitroActual() {
        Tablero tablero = new Tablero();
        Arbitro arbitro = new Arbitro(tablero);
        arbitro.colocarPiezasConfiguracionInicial();

        for (Jugada jugada : historico) {
            arbitro.empujar(jugada);
            arbitro.cambiarTurno();
        }
        return arbitro;
    }
}