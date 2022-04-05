package cl.nelsonmc.indicadores.model;

import java.util.ArrayList;

public class DataIndicador {
    private String nombre;
    private ArrayList<SerieIndicador> serie;

    public String getNombre() {
        return nombre;
    }
    
    public ArrayList<SerieIndicador> getSerie() {
        return serie;
    }
}
