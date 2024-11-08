package noventagrados.modelo;

import noventagrados.util.Coordenada;

/**
 * @author <a href="mailto:aab1027@alu.ubu.es">Antonio Alonso Briones</a>
 *         Clase que representa una jugada en el juego Noventa Grados.
 *         Contiene las celdas de origen y destino.
 */
/**
 * Clase que representa una jugada en el juego Noventa Grados.
 * Contiene las celdas de origen y destino.
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
        int resultado = 17;
        resultado = 31 * resultado + origen.hashCode();
        resultado = 31 * resultado + destino.hashCode();
        return resultado;
    }

    /**
     * Compara si dos jugadas son iguales.
     *
     * @param obj Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Jugada))
            return false;
        Jugada otra = (Jugada) obj;
        return this.origen.equals(otra.origen) && this.destino.equals(otra.destino);
    }

    /**
     * Devuelve la representación en texto de la jugada.
     *
     * @return Representación textual.
     */
    @Override
    public String toString() {
        return "Jugada{" +
                "origen=" + origen +
                ", destino=" + destino +
                '}';
    }
}
