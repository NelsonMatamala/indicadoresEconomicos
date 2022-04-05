package cl.nelsonmc.indicadores.indicadores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import cl.nelsonmc.indicadores.indicadores.AdapterIndicadores;
import cl.nelsonmc.indicadores.R;
import cl.nelsonmc.indicadores.model.SerieIndicador;

public class ListaFragment extends Fragment {

    private ArrayList<SerieIndicador> serieIndicadorArrayList;
    private String tipoData;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            serieIndicadorArrayList = (ArrayList<SerieIndicador>) getArguments().getSerializable("arrayList");
            tipoData = getArguments().getString("tipoData");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista,container,false);

        AdapterIndicadores adapterIndicadores = new AdapterIndicadores(serieIndicadorArrayList,tipoData);

        RecyclerView recyclerLista = view.findViewById(R.id.recyclerLista);
        recyclerLista.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerLista.setAdapter(adapterIndicadores);

        return view;
    }
}
