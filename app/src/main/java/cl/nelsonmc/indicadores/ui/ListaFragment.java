package cl.nelsonmc.indicadores.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import cl.nelsonmc.indicadores.AdapterIndicadores;
import cl.nelsonmc.indicadores.R;
import cl.nelsonmc.indicadores.modelos.SerieIndicador;

public class ListaFragment extends Fragment {

    private RecyclerView recyclerLista;
    private ArrayList<SerieIndicador> serieIndicadorArrayList;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            serieIndicadorArrayList = (ArrayList<SerieIndicador>) getArguments().getSerializable("ArrayList");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista,container,false);
        recyclerLista = view.findViewById(R.id.recyclerLista);
        recyclerLista.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        AdapterIndicadores adapterIndicadores = new AdapterIndicadores(serieIndicadorArrayList, new AdapterIndicadores.OnItemClickListener() {
            @Override
            public void onItemClick(SerieIndicador indicador) {

            }
        });
        recyclerLista.setAdapter(adapterIndicadores);
        return view;
    }
}
