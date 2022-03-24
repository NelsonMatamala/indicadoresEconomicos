package cl.nelsonmc.indicadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import cl.nelsonmc.indicadores.modelos.SerieIndicador;

public class AdapterIndicadores extends RecyclerView.Adapter<AdapterIndicadores.ViewHolderIndicadores> {
    private ArrayList<SerieIndicador> indicadores;
    private String tipoData;

    public AdapterIndicadores(ArrayList<SerieIndicador> indicadores,String tipoData) {
        this.indicadores = indicadores;
        this.tipoData = tipoData;
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

            fechaText.setText(dateUtcToString(indicadorModel.getFecha()));
            valorText.setText(decimalFormat(indicadorModel.getValor()));
        }

        private String decimalFormat(String valor){
            float numero = Float.parseFloat(valor);
            Locale chileLocale = new Locale("es","CL");
            NumberFormat nf = NumberFormat.getNumberInstance(chileLocale);
            String valorFormateado = nf.format(numero);
            return  valorFormateado;
        }

        private String dateUtcToString(String fecha) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            Instant instantFecha  = Instant.parse(fecha);
            return ZonedDateTime.ofInstant(instantFecha, ZoneId.of("America/Santiago")).format(dtf);
        }


    }
}
