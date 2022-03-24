package cl.nelsonmc.indicadores.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;
import cl.nelsonmc.indicadores.R;
import cl.nelsonmc.indicadores.modelos.SerieIndicador;

public class CalcularFragment extends Fragment {
    private SerieIndicador serieIndicador;
    private String tipoData;
    private float valor;
    private EditText editTextNumber, editTextValorIndicador, editTextValorCalculo;
    private TextView textViewUp,textViewDown,textViewUltimaFecha;
    private ImageButton btnChange;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            serieIndicador   = (SerieIndicador) getArguments().getSerializable("indicador");
            tipoData         = getArguments().getString("tipoData");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calcular,container,false);
        btnChange = view.findViewById(R.id.btnChange);
        textViewUp = view.findViewById(R.id.textViewUp);
        textViewDown = view.findViewById(R.id.textViewDown);
        editTextNumber = view.findViewById(R.id.editTextIndicador);
        textViewUltimaFecha = view.findViewById(R.id.ultimaFechaText);
        textViewUp.setText(tipoData.toUpperCase());
        textViewUp.setTag(tipoData.toUpperCase());
        textViewUltimaFecha.setText(dateUtcToString(serieIndicador.getFecha()));
        editTextValorCalculo = view.findViewById(R.id.editTextCalculo);

        if(tipoData.equals("bitcoin")){
            textViewDown.setText(R.string.usd);
        }

        editTextValorIndicador = view.findViewById(R.id.editTextCalculo);
        calculateAndSetValue();

        editTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editTextNumber.getText().toString().equals("")){
                    return;
                }
                calculateAndSetValue();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valor1 = (String) textViewUp.getText();
                String valor2 = (String) textViewDown.getText();
                textViewUp.setText(valor2);
                textViewDown.setText(valor1);
                calculateAndSetValue();
            }
        });
        return view;
    }

    private void calculateAndSetValue(){
        float indicadorValor = Float.parseFloat(serieIndicador.getValor());
        valor = Float.parseFloat(editTextNumber.getText().toString());
        float multiplicacion;
        if(textViewUp.getTag().equals(textViewUp.getText())){
             multiplicacion = valor * indicadorValor;
        }else{
             multiplicacion = valor / indicadorValor;
        }
        editTextValorIndicador.setText(decimalFormat(multiplicacion));
    }

    private String decimalFormat(float valor){
        Locale chileLocale = new Locale("es","CL");
        NumberFormat nf = NumberFormat.getNumberInstance(chileLocale);
        String valorFormateado = nf.format(valor);
        return  valorFormateado;
    }

    public String dateUtcToString(String fecha) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Instant instantFecha  = Instant.parse(fecha);
        return ZonedDateTime.ofInstant(instantFecha, ZoneId.of("America/Santiago")).format(dtf);
    }
}
