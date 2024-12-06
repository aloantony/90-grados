package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Tablero;

import java.util.Date;

/**
 * Clase que implementa el mecanismo de deshacer utilizando jugadas.
 * 
 * @since 1.0
 * @version 1.0
 * @autor <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 */
public class MaquinaDelTiempoConJugadas extends MecanismoDeDeshacerAbstracto<Jugada> {

    /**
     * Constructor de la clase MaquinaDelTiempoConJugadas.
     * 
     * @param fechaInicio La fecha de inicio del mecanismo de deshacer.
     */
    public MaquinaDelTiempoConJugadas(Date fechaInicio) {
        super(fechaInicio);
    }

    /**
     * Realiza una jugada, añadiéndola al historial.
     * 
     * @param jugada La jugada a realizar.
     */
    @Override
    public void hacerJugada(Jugada jugada) {
        historico.add(jugada);
    }

    /**
     * Deshace la última jugada realizada, volviendo al estado previo.
     */
    @Override
    public void deshacerJugada() {
        if (!historico.isEmpty()) {
            historico.remove(consultarNumeroJugadasEnHistorico() - 1);
            consultarArbitroActual();
        }
    }

    /**
     * Consulta el estado actual del árbitro.
     * 
     * @return El árbitro en su estado actual.
     */
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