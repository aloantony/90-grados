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
        historico.add(arbitro);
        numeroJugadasEnHistorico = historico.size();
    }

    @Override
    public void deshacerJugada() {
        if (!historico.isEmpty()) {
            fechaInicio = new Date();
            arbitro = consultarArbitroActual();
            historico.remove(numeroJugadasEnHistorico - 1);
            numeroJugadasEnHistorico = historico.size();
        } else {
            arbitro = new Arbitro(new Tablero());
            arbitro.colocarPiezasConfiguracionInicial();
        }
    }

    @Override
    public Arbitro consultarArbitroActual() {
        if (!historico.isEmpty()) {
            arbitro = historico.get(numeroJugadasEnHistorico - 1);
        }
        return arbitro;
    }
}