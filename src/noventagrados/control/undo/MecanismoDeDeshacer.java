package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import java.util.Date;

public interface MecanismoDeDeshacer {

    public Arbitro consultarArbitroActual();

    public int consultarNumeroJugadasEnHistorico();

    public void deshacerJugada();

    public void hacerJugada();

    public Date obtenerFechaInicio();
}