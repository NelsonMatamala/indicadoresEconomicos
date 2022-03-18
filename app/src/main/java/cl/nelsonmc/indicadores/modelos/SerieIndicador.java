package cl.nelsonmc.indicadores.modelos;

import java.io.Serializable;

public class SerieIndicador implements Serializable {
    String valor;
    String fecha;

    public SerieIndicador(String valor, String fecha) {
        this.valor = valor;
        this.fecha = fecha;
    }

    public String getValor() {
        return valor;
    }

    public String getFecha() {
        return fecha;
    }
}
