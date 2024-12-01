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
        historico.add(arbitro);
        consultarNumeroJugadasEnHistorico();
    }

    @Override
    public void deshacerJugada() {
        if (!historico.isEmpty()) {
            consultarArbitroActual();
            historico.remove(consultarNumeroJugadasEnHistorico() - 1);
        }
    }

    @Override
    public Arbitro consultarArbitroActual() {
        if (consultarNumeroJugadasEnHistorico() == 0) {
            Arbitro arbitro = new Arbitro(new Tablero());
            arbitro.colocarPiezasConfiguracionInicial();
            return arbitro;
        } else
            return historico.get(consultarNumeroJugadasEnHistorico() - 1);
    }
}