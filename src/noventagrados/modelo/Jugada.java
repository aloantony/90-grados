package noventagrados.modelo;

import java.util.Objects;

import noventagrados.util.Coordenada;

/**
 * Clase que representa una jugada en el juego Noventa Grados.
 * Contiene las celdas de origen y destino.
 * 
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 * @param origen  Celda desde donde se inicia el movimiento
 * @param destino Celda hacia donde se realiza el movimiento
 */

public record Jugada(Celda origen, Celda destino) {

	/**
	 * Devuelve la representación en texto de la jugada (ej. "00-11").
	 *
	 * @return Representación textual de la jugada.
	 */
	public String aTexto() {
		Coordenada coordenadaOrigen = origen.consultarCoordenada();
		Coordenada coordenadaDestino = destino.consultarCoordenada();
		return String.format("%s-%s", coordenadaOrigen.aTexto(), coordenadaDestino.aTexto());
	}

	@Override
	public int hashCode() {
		return Objects.hash(destino, origen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugada other = (Jugada) obj;
		return Objects.equals(destino, other.destino) && Objects.equals(origen, other.origen);
	}

	@Override
	public String toString() {
		return "Jugada [origen=" + origen + ", destino=" + destino + "]";
	}
}
