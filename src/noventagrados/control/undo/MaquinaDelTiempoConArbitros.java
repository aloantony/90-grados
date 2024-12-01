package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Tablero;

import java.util.Date;

public class MaquinaDelTiempoConArbitros extends MecanismoDeDeshacerAbstracto<Arbitro> {
    public MaquinaDelTiempoConArbitros(Date fechaInicio) {
        super(fechaInicio);
    }

    @Override
    public void hacerJugada(Jugada jugada) {
        Arbitro arbitro = consultarArbitroActual();
        arbitro.empujar(jugada);
        arbitro.cambiarTurno();
        historico.add(arbitro);
    }

    @Override
    public void deshacerJugada() {
        if (!historico.isEmpty()) {
            historico.remove(consultarNumeroJugadasEnHistorico() - 1);
            consultarArbitroActual();
        } else {
            Arbitro arbitro = new Arbitro(new Tablero());
            arbitro.colocarPiezasConfiguracionInicial();
        }
    }

    @Override
    public Arbitro consultarArbitroActual() {
        if (historico.isEmpty()) {
            Arbitro arbitro = new Arbitro(new Tablero());
            arbitro.colocarPiezasConfiguracionInicial();
            return arbitro;
        }
        return historico.get(consultarNumeroJugadasEnHistorico() - 1).clonar();
    }
}
