package cl.nelsonmc.indicadores.model;

import java.io.Serializable;

public class IndicadorList implements Serializable {
    String valor;
    String fecha;

    public IndicadorList(String valor, String fecha) {
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
