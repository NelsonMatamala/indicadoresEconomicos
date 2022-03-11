package cl.nelsonmc.indicadores.modelos;

import java.util.ArrayList;

public class DataIndicador {
    private String nombre;
    private String unidad_medida;
    private ArrayList<SerieIndicador> serie;

    public String getNombre() {
        return nombre;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public ArrayList<SerieIndicador> getSerie() {
        return serie;
    }
}
