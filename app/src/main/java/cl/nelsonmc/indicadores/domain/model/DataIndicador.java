package cl.nelsonmc.indicadores.domain.model;

import java.util.ArrayList;

public class DataIndicador {
    private String nombre;
    private ArrayList<IndicadorList> serie;

    public String getNombre() {
        return nombre;
    }
    
    public ArrayList<IndicadorList> getSerie() {
        return serie;
    }
}
