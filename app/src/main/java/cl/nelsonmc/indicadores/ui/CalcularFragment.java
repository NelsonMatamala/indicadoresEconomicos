package cl.nelsonmc.indicadores.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import cl.nelsonmc.indicadores.R;
import cl.nelsonmc.indicadores.modelos.SerieIndicador;


public class CalcularFragment extends Fragment {
    private SerieIndicador serieIndicador;
    private String tipoData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            serieIndicador   = (SerieIndicador) getArguments().getSerializable("indicador");
            tipoData         = getArguments().getString("tipoData");
            Log.d("serieIndicador",serieIndicador.getValor());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calcular,container,false);
        return view;
    }
}
