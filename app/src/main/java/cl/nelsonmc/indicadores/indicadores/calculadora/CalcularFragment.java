package cl.nelsonmc.indicadores.indicadores.calculadora;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import cl.nelsonmc.indicadores.model.SerieIndicador;

public class CalcularFragment extends Fragment {
    private SerieIndicador serieIndicador;
    private String tipoData;
    private EditText editTextNumber, editTextValorCalculo;
    private TextView textViewUp;
    private TextView textViewDown;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            serieIndicador = (SerieIndicador) getArguments().getSerializable("indicador");
            tipoData = getArguments().getString("tipoData");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calcular, container, false);
        ImageButton btnChange = view.findViewById(R.id.btnChange);
        LinearLayout linearLayout = view.findViewById(R.id.ly_contenedor);
        TextView textUltimaFecha = view.findViewById(R.id.ultimaFechaText);
        textViewUp = view.findViewById(R.id.textViewUp);
        textViewDown = view.findViewById(R.id.textViewDown);
        editTextNumber = view.findViewById(R.id.editTextIndicador);
        textViewUp.setText(tipoData.toUpperCase());
        textViewUp.setTag(tipoData.toUpperCase());
        textUltimaFecha.setText(dateUtcToString(serieIndicador.getFecha()));
        editTextValorCalculo = view.findViewById(R.id.editTextCalculo);

        if (tipoData.equals("bitcoin")) {
            textViewDown.setText(R.string.usd);
        }

        if (tipoData.equals("ivp") || tipoData.equals("ipc") || tipoData.equals("imacec")
                || tipoData.equals("cobre") || tipoData.equals("desempleo")) {
            linearLayout.setVisibility(View.GONE);
        }

        calculateAndSetValue();

        editTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editTextNumber.getText().toString().equals("")) {
                    return;
                }
                calculateAndSetValue();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        btnChange.setOnClickListener(view1 -> {
            String valor1 = (String) textViewUp.getText();
            String valor2 = (String) textViewDown.getText();
            textViewUp.setText(valor2);
            textViewDown.setText(valor1);
            if(!editTextNumber.getText().toString().isEmpty()){
                calculateAndSetValue();
            }
        });
        return view;
    }

    private void calculateAndSetValue() {
        float indicadorValor = Float.parseFloat(serieIndicador.getValor());
        float valor = Float.parseFloat(editTextNumber.getText().toString());
        float multiplicacion;
        if (textViewUp.getTag().equals(textViewUp.getText())) {
            multiplicacion = valor * indicadorValor;
        } else {
            multiplicacion = valor / indicadorValor;
        }
        editTextValorCalculo.setText(decimalFormat(multiplicacion));
    }

    private String decimalFormat(float valor) {
        Locale chileLocale = new Locale("es", "CL");
        NumberFormat nf = NumberFormat.getNumberInstance(chileLocale);
        return nf.format(valor);
    }

    public String dateUtcToString(String fecha) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Instant instantFecha = Instant.parse(fecha);
        return ZonedDateTime.ofInstant(instantFecha, ZoneId.of("America/Santiago")).format(dtf);
    }
}
