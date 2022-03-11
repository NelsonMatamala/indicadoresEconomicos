package cl.nelsonmc.indicadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import cl.nelsonmc.indicadores.modelos.SerieIndicador;

public class AdapterIndicadores extends RecyclerView.Adapter<AdapterIndicadores.ViewHolderIndicadores> {
    ArrayList<SerieIndicador> indicadores;
    final AdapterIndicadores.OnItemClickListener listener;

    public AdapterIndicadores(ArrayList<SerieIndicador> indicadores, OnItemClickListener listener) {
        this.indicadores = indicadores;
        this.listener = listener;
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

    public interface OnItemClickListener {
        void onItemClick(SerieIndicador indicador);
    }

    public class ViewHolderIndicadores extends RecyclerView.ViewHolder {

        TextView fechaText;
        TextView valorText;

        public ViewHolderIndicadores(@NonNull View itemView) {
            super(itemView);

            fechaText = itemView.findViewById(R.id.fechaTextView);
            valorText = itemView.findViewById(R.id.valorTextView);
        }

        public void setListIndicadores(SerieIndicador indicadorModel) {

            fechaText.setText(indicadorModel.getFecha());
            valorText.setText(indicadorModel.getValor());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(indicadorModel);
                }
            });
        }
    }
}
