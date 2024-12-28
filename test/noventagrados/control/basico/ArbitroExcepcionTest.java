package noventagrados.control.basico;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Tablero;

/**
 * Comprobación de lanzamiento de excepcion en un 
 * arbitro al consultar caja con valor incorrecto.
 * 
 * @author <a href="rmartico@ubu.es">Raúl Marticorena</a>
 * @since 2.1
 * @version 1.0
 */
@DisplayName("Tests del Arbitro sobre lanzamiento de excepciones al consultar caja.")
@Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD) 
// Time out global para todos los tests salvo los de ciclo de vida
public class ArbitroExcepcionTest {

	/** Árbitro de testing. */
	private Arbitro arbitro;

	/** Tablero de testing. */
	private Tablero tablero;

	/** Generación del árbitro para testing. */
	@BeforeEach
	void inicializar() {
		// Inyección de tablero para testing...
		tablero = new Tablero();
		arbitro = new Arbitro(tablero);
	}
	

	/** 
	 * Comprueba lanzamiento de excepción no comprobable.
	 */
	@Test
	@DisplayName("Comprueba el lanzamiento de excepción al consultar caja con valor nulo.")
	void comprobarLanzamientoExcepcion() {
		assertThrows(IllegalArgumentException.class,
				() ->  {
					arbitro.consultarCaja(null);
				});
	}

}
