package cl.nelsonmc.indicadores.indicadores.lista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import cl.nelsonmc.indicadores.R;
import cl.nelsonmc.indicadores.common.Utils;
import cl.nelsonmc.indicadores.model.SerieIndicador;

public class AdapterIndicadores extends RecyclerView.Adapter<AdapterIndicadores.ViewHolderIndicadores> {
    private final ArrayList<SerieIndicador> indicadores;
    private final String tipoData;
    private final Utils utils;

    public AdapterIndicadores(ArrayList<SerieIndicador> indicadores,String tipoData) {
        this.indicadores = indicadores;
        this.tipoData = tipoData;
        this.utils = new Utils();
    }

    @NonNull
    @Override
    public ViewHolderIndicadores onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_indicador,null,false);
        return new ViewHolderIndicadores(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderIndicadores holder, int position) {
        holder.setListIndicadores(indicadores.get(position));
    }

    @Override
    public int getItemCount() {
        return indicadores.size();
    }


    public class ViewHolderIndicadores extends RecyclerView.ViewHolder {

        TextView fechaText;
        TextView valorText;
        TextView tipoValor;

        public ViewHolderIndicadores(@NonNull View itemView) {
            super(itemView);
            fechaText = itemView.findViewById(R.id.fechaTextView);
            valorText = itemView.findViewById(R.id.valorTextView);
            tipoValor = itemView.findViewById(R.id.tipoValor);
        }

        public void setListIndicadores(SerieIndicador indicadorModel) {

            if(tipoData.equals("ipc") || tipoData.equals("imacec") || tipoData.equals("desempleo")){
                tipoValor.setText("%");
            }
            if(tipoData.equals("bitcoin") || tipoData.equals("cobre") ){
                tipoValor.setText("$US");
            }

            fechaText.setText(utils.dateUtcToString(indicadorModel.getFecha()));
            valorText.setText(utils.decimalFormat(indicadorModel.getValor()));
        }

    }
}
