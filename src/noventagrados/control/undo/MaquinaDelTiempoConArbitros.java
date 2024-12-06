package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Tablero;

import java.util.Date;

/**
 * Clase que implementa el mecanismo de deshacer utilizando clones de árbitros.
 * 
 * @since 1.0
 * @version 1.0
 * @autor <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 */
public class MaquinaDelTiempoConArbitros extends MecanismoDeDeshacerAbstracto<Arbitro> {

    /**
     * Constructor de la clase MaquinaDelTiempoConArbitros.
     * 
     * @param fechaInicio La fecha de inicio del mecanismo de deshacer.
     */
    public MaquinaDelTiempoConArbitros(Date fechaInicio) {
        super(fechaInicio);
    }

    /**
     * Realiza una jugada, empujando las piezas y cambiando el turno.
     * 
     * @param jugada La jugada a realizar.
     */
    @Override
    public void hacerJugada(Jugada jugada) {
        Arbitro arbitro = consultarArbitroActual();
        arbitro.empujar(jugada);
        arbitro.cambiarTurno();
        historico.add(arbitro);
    }

    /**
     * Deshace la última jugada realizada, volviendo al estado previo.
     */
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

    /**
     * Consulta el estado actual del árbitro.
     * 
     * @return El árbitro en su estado actual.
     */
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
