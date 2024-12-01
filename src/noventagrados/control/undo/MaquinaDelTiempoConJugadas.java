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
    }

    @Override
    public void deshacerJugada() {
        if (!historico.isEmpty()) {
            historico.remove(consultarNumeroJugadasEnHistorico() - 1);
            consultarArbitroActual();
        }
    }

    @Override
    public Arbitro consultarArbitroActual() {
        Arbitro arbitro = new Arbitro(new Tablero());
        arbitro.colocarPiezasConfiguracionInicial();

        for (Jugada jugada : historico) {
            arbitro.empujar(jugada);
            arbitro.cambiarTurno();
        }
        return arbitro;
    }
}